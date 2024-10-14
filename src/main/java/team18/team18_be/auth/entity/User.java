package team18.team18_be.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private final Long id;

  @Column(nullable = false)
  private final String name;

  @Column(nullable = false, unique = true)
  private final String email;

  private final String phoneNumber;

  private final String foreignerNumber;

  private final LocalDateTime visaGenerateDate;

  private final String type;

  private final Long signId;

  private final Long resumeId;

  protected User() {
    this(null, null, null, null, null, null, null, null, null);
  }

  public User(String name, String email, String type) {
    this(null, name, email, null, null, null, type, null, null);
  }

  public User(Long id,
      String name,
      String email,
      String phoneNumber,
      String foreignerNumber,
      LocalDateTime visaGenerateDate,
      String type,
      Long signId,
      Long resumeId) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.foreignerNumber = foreignerNumber;
    this.visaGenerateDate = visaGenerateDate;
    this.type = type;
    this.signId = signId;
    this.resumeId = resumeId;
  }

  public Long getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public User updateUserType(String type) {
    return new User(this.id,
        this.name,
        this.email,
        this.phoneNumber,
        this.foreignerNumber,
        this.visaGenerateDate,
        type,
        signId,
        resumeId);
  }

}
