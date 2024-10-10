package team18.team18_be.auth.entity;

public enum UserType {
  FIRST("first"),
  EMPLOYEE("employee"),
  EMPLOYER("employer");

  private final String userType;

  UserType(String userType) {
    this.userType = userType;
  }

  public String getUserType() {
    return this.userType;
  }
}
