package team18.team18_be.config.interceptor;

public enum InterceptorPath {
  REGISTER("/api/register"),
  RECRUITMENT("/api/recruitments"),
  RECRUITMENT_USER("/api/recruitments/user"),
  APPLICATION_ALL("/api/application/**"),
  RESUME_ALL("/api/resumes/**"),
  SIGN_ALL("/api/sign/**"),
  COMPANY_ALL("/api/company/**"),
  VISA("/api/visa"),
  CONTRACT_ALL("/api/contract/**"),
  CONTRACT_PREVIEW("/api/contract/*/preview");

  private final String path;

  InterceptorPath(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}
