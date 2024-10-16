package team18.team18_be.auth.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import team18.team18_be.auth.dto.request.UserTypeRequest;
import team18.team18_be.auth.entity.User;
import team18.team18_be.auth.entity.UserType;
import team18.team18_be.auth.repository.AuthRepository;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class AuthServiceTest {

  final String USER_NAME = "name";
  final String USER_EMAIL = "email";
  final Long USER_ID = 1L;

  @InjectMocks
  AuthService authService;

  @Mock
  AuthRepository authRepository;

  @Test
  void 유저_타입_등록() {
    // given
    UserTypeRequest userTypeRequest = new UserTypeRequest(UserType.EMPLOYER.getUserType());
    User user = new User(USER_ID, USER_NAME, USER_EMAIL, UserType.FIRST.getUserType());

    // when
    authService.registerUserType(userTypeRequest, user);

    // then
    then(authRepository).should().save(any());
  }

}
