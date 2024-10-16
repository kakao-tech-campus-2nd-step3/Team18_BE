package team18.team18_be.exception;

public enum ErrorMessage {
  NOT_FOUND_USER("회원 정보가 존재하지 않습니다."),
  NOT_FOUND_USER_ID_IN_HEADER("요청에 회원 정보가 존재하지 않습니다."),
  GOOGLE_OAUTH_TOKEN_ERROR_MESSAGE("구글 토큰 발급 관련 에러가 발생하였습니다."),
  GOOGLE_OAUTH_USER_INFO_ERROR_MESSAGE("구글 유저 정보 조회 중 에러가 발생하였습니다."),
  OBJECT_MAPPER_ERROR_MESSAGE("JSON 파싱 오류가 발생하였습니다.");

  private final String errorMessage;

  ErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
