package huchu.board.domain;

import static huchu.board.common.Fixture.comment;
import static huchu.board.common.Fixture.user;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PostTest {

    @Nested
    class 인가_여부를_검증할_때 {

        @Test
        void 권한이_있는_사용자는_예외를_던지지_않는다() {
            // given
            Member member = new Member("user");
            Post post = new Post(member, "title", "content");

            // expect
            assertThatNoException().isThrownBy(() -> post.validateAuthorization(member));
        }

        @Test
        void 권한이_없는_사용자는_예외를_던진다() {
            // given
            Member member = new Member("user");
            Member unauthroizedMember = new Member("unauthorizedUser");
            Post post = new Post(member, "title", "content");

            // expect
            assertThatThrownBy(() -> post.validateAuthorization(unauthroizedMember))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void 제목과_내용을_변경한다() {
        // given
        Post post = new Post(user(), "title", "content");

        // when
        post.change("new title", "new content");

        // then
        assertSoftly(softly -> {
            softly.assertThat(post.title()).isEqualTo("new title");
            softly.assertThat(post.content()).isEqualTo("new content");
        });
    }

    @Test
    void 댓글을_추가한다() {
        // given
        Post post = new Post(user(), "title", "content");
        Comment comment = comment();

        // when
        post.addComment(comment);

        // then
        assertThat(post.comments()).contains(comment);
    }

    @Test
    void 댓글을_삭제한다() {
        // given
        Post post = new Post(user(), "title", "content");
        Comment comment = comment();
        post.addComment(comment);

        // when
        post.removeComment(comment);

        // then
        assertThat(post.comments()).doesNotContain(comment);
    }

    @Test
    void 삭제한다() {
        // given
        Post post = new Post(user(), "title", "content");

        // when
        post.delete();

        // then
        assertThat(post.isDeleted()).isTrue();
    }

    @Test
    void 삭제하면_댓글이_삭제된다() {
        // given
        Post post = new Post(user(), "title", "content");
        post.addComment(comment());

        // when
        post.delete();

        // then
        assertThat(post.comments().stream().allMatch(Comment::isDeleted)).isTrue();
    }
}
