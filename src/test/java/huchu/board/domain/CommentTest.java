package huchu.board.domain;

import static huchu.board.common.Fixture.post;
import static huchu.board.common.Fixture.user;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CommentTest {

    @Nested
    class 인가_여부를_검증할_때 {

        @Test
        void 권한이_있는_사용자는_예외를_던지지_않는다() {
            // given
            Member member = new Member("user");
            Comment comment = new Comment(member, "content", post());

            // expect
            assertThatNoException().isThrownBy(() -> comment.validateAuthorization(member));
        }

        @Test
        void 권한이_없는_사용자는_예외를_던진다() {
            // given
            Member member = new Member("user");
            Member unauthroizedMember = new Member("unauthorizedUser");
            Comment comment = new Comment(member, "content", post());

            // expect
            assertThatThrownBy(() -> comment.validateAuthorization(unauthroizedMember))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void 내용을_변경한다() {
        // given
        Comment comment = new Comment(user(), "content", post());

        // when
        comment.change("new content");

        // then
        assertThat(comment.content()).isEqualTo("new content");
    }

    @Test
    void 삭제한다() {
        // given
        Comment comment = new Comment(user(), "content", post());

        // when
        comment.delete();

        // then
        assertThat(comment.isDeleted()).isTrue();
    }

    @Test
    void 삭제하면_게시글에서_삭제된다() {
        // given
        Comment comment = new Comment(user(), "content", post());

        // when
        comment.delete();

        // then
        assertThat(comment.post().comments()).doesNotContain(comment);
    }
}
