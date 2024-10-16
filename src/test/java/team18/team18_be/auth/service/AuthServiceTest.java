package team18.team18_be.auth.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Stream;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import team18.team18_be.auth.dto.OAuthUserInfo;
import team18.team18_be.auth.dto.request.CodeRequest;
import team18.team18_be.auth.dto.request.UserTypeRequest;
import team18.team18_be.auth.dto.response.LoginResponse;
import team18.team18_be.auth.dto.response.OAuthJwtResponse;
import team18.team18_be.auth.entity.User;
import team18.team18_be.auth.entity.UserType;
import team18.team18_be.auth.repository.AuthRepository;
import team18.team18_be.config.property.GoogleProperty;
import team18.team18_be.exception.OAuthLoginFailedException;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class AuthServiceTest {

  static final String NAME = "name";
  static final String EMAIL = "email";
  static final String PICTURE = "profile_image";
  static final String USER_INFO_URI = "/userinfo/v2/me";
  static final String APPLICATION_TEST_PROPERTIES = "application-test.properties";
  static final String OAUTH_GOOGLE_CLIENT_ID = "oauth.google.client-id";
  static final String OAUTH_GOOGLE_CLIENT_SECRET = "oauth.google.client-secret";
  static final String OAUTH_GOOGLE_REDIRECT_URI = "oauth.google.redirect-uri";
  static final String OAUTH_GOOGLE_GRANT_TYPE = "oauth.google.grant-type";
  static final String JWT_SECRET = "jwt.secret";
  static final String SECRET_KEY = "SECRET_KEY";
  static final String GOOGLE_PROPERTY = "googleProperty";
  static final String TOKEN_URI = "/token";
  static final String USER_NAME = "name";
  static final String USER_EMAIL = "email";
  static final Long USER_ID = 1L;
  static final String AUTHORIZATION_CODE = "authorization_code";
  static final String OAUTH_ACCESS_TOKEN = "access_token";

  @InjectMocks
  AuthService authService;

  @Mock
  AuthRepository authRepository;

  MockWebServer mockWebServer;

  ObjectMapper objectMapper;

  static Stream<Arguments> 파라미터_구글_토큰_기반_회원가입_및_로그인() {
    return Stream.of(
        Arguments.of("신규 유저일 때", new User(NAME, EMAIL, UserType.FIRST.getUserType()), true),
        Arguments.of("기존 유저일 때", new User(NAME, EMAIL, UserType.EMPLOYER.getUserType()), false)
    );
  }

  @BeforeEach
  void object_mapper_초기화() {
    this.objectMapper = new ObjectMapper();
  }

  @BeforeEach
  void property_리플렉션_초기화() throws IOException {
    Properties properties = new Properties();
    properties.load(getClass()
        .getClassLoader()
        .getResourceAsStream(APPLICATION_TEST_PROPERTIES));

    String clientId = properties.getProperty(OAUTH_GOOGLE_CLIENT_ID);
    String clientSecert = properties.getProperty(OAUTH_GOOGLE_CLIENT_SECRET);
    String redirectUri = properties.getProperty(OAUTH_GOOGLE_REDIRECT_URI);
    String grantType = properties.getProperty(OAUTH_GOOGLE_GRANT_TYPE);
    String secretKey = properties.getProperty(JWT_SECRET);

    ReflectionTestUtils.setField(authService, SECRET_KEY, secretKey);

    ReflectionTestUtils.setField(authService, GOOGLE_PROPERTY,
        new GoogleProperty(clientId, clientSecert, redirectUri, grantType));
  }

  @BeforeEach
  void 모킹_API_서버_구동() throws IOException {
    mockWebServer = new MockWebServer();
    mockWebServer.start();
  }

  @AfterEach
  void 모킹_API_서버_종료() throws IOException {
    mockWebServer.shutdown();
  }

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

  @Test
  void 구글_OAuth_토큰_발급() throws JsonProcessingException {
    // given
    OAuthJwtResponse responseBody = new OAuthJwtResponse(OAUTH_ACCESS_TOKEN);
    mockWebServer.enqueue(
        new MockResponse().setBody(objectMapper.writeValueAsString(responseBody)));

    String mockServerUri = mockWebServer.url(TOKEN_URI).toString();
    CodeRequest codeRequest = new CodeRequest(AUTHORIZATION_CODE);

    // when
    OAuthJwtResponse response = authService.getOAuthToken(codeRequest, mockServerUri);

    // then
    assertThat(response.accessToken()).isEqualTo(OAUTH_ACCESS_TOKEN);
  }

  @Test
  void 실패_구글_OAuth_토큰_발급_API_에러() {
    // given
    mockWebServer.enqueue(new MockResponse().setResponseCode(400));
    String mockUri = mockWebServer.url(TOKEN_URI).toString();
    CodeRequest codeRequest = new CodeRequest(AUTHORIZATION_CODE);

    // when, then
    assertThatThrownBy(() -> authService.getOAuthToken(codeRequest, mockUri)).isInstanceOf(
        OAuthLoginFailedException.class);
  }

  @ParameterizedTest(name = "[{index}] {0}")
  @MethodSource("파라미터_구글_토큰_기반_회원가입_및_로그인")
  void 구글_토큰_기반_회원가입_및_로그인(String testName, User user, boolean isNewUser)
      throws JsonProcessingException {
    // given
    OAuthUserInfo oAuthUserInfo = new OAuthUserInfo(NAME, EMAIL, PICTURE);
    mockWebServer.enqueue(
        new MockResponse().setBody(objectMapper.writeValueAsString(oAuthUserInfo)));
    given(authRepository.existsByEmail(EMAIL)).willReturn(!isNewUser);
    given(authRepository.findByEmail(EMAIL)).willReturn(Optional.of(user));

    String mockUri = mockWebServer.url(USER_INFO_URI).toString();
    OAuthJwtResponse oAuthJwtResponse = new OAuthJwtResponse(OAUTH_ACCESS_TOKEN);

    // when
    LoginResponse loginResponse = authService.registerOAuth(oAuthJwtResponse, mockUri);

    // then
    if (isNewUser) {
      then(authRepository).should().save(any(User.class));
    }
    SoftAssertions.assertSoftly(softly -> {
      assertThat(loginResponse.type()).isEqualTo(user.getType());
      assertThat(loginResponse.profileImage()).isEqualTo(PICTURE);
      assertThat(loginResponse.accessToken()).isNotNull();
    });
  }

  @Test
  void 실패_구글_토큰_기반_회원가입_및_로그인_중_유저_정보_조회() {
    // given
    mockWebServer.enqueue(new MockResponse().setResponseCode(400));
    String mockUri = mockWebServer.url(USER_INFO_URI).toString();
    OAuthJwtResponse oAuthJwtResponse = new OAuthJwtResponse(OAUTH_ACCESS_TOKEN);

    // when, then
    assertThatThrownBy(() -> authService.registerOAuth(oAuthJwtResponse, mockUri)).isInstanceOf(
        OAuthLoginFailedException.class);
  }

}
