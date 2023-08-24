package huchu.board.domain;

import static huchu.board.common.Fixture.user;
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
            User user = new User("user");
            Post post = new Post(user, "title", "content");

            // expect
            assertThatNoException().isThrownBy(() -> post.validateAuthorization(user));
        }

        @Test
        void 권한이_없는_사용자는_예외를_던진다() {
            // given
            User user = new User("user");
            User unauthroizedUser = new User("unauthorizedUser");
            Post post = new Post(user, "title", "content");

            // expect
            assertThatThrownBy(() -> post.validateAuthorization(unauthroizedUser))
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
}
