package team18.team18_be.config.infrastructure.dto;

public class Message {
  private String role;
  private String content;

  public Message() {}

  public Message(String role, String content) {
    this.role = role;
    this.content = content;
  }

  public String getRole() {
    return role;
  }

  public String getContent() {
    return content;
  }
}
