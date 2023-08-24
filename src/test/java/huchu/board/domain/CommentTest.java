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
            User user = new User("user");
            Comment comment = new Comment(user, "content", post());

            // expect
            assertThatNoException().isThrownBy(() -> comment.validateAuthorization(user));
        }

        @Test
        void 권한이_없는_사용자는_예외를_던진다() {
            // given
            User user = new User("user");
            User unauthroizedUser = new User("unauthorizedUser");
            Comment comment = new Comment(user, "content", post());

            // expect
            assertThatThrownBy(() -> comment.validateAuthorization(unauthroizedUser))
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
}
