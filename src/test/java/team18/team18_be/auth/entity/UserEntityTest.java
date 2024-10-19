package team18.team18_be.auth.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class UserEntityTest {

  final String USER_NAME = "name";
  final String USER_EMAIL = "email";
  final Long USER_ID = 1L;

  @Test
  void 유저_타입_업데이트() {
    // given
    User user = new User(USER_ID, USER_NAME, USER_EMAIL, UserType.FIRST.getUserType());

    // when
    User actualUser = user.updateUserType(UserType.EMPLOYEE.getUserType());

    // then
    Assertions.assertThat(actualUser.getType()).isEqualTo(UserType.EMPLOYEE.getUserType());
  }
}
