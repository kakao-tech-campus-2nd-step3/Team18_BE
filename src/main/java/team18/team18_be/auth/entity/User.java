package team18.team18_be.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

  @Column(nullable = false)
  private final String type;

  protected User() {
    this(null, null, null, null, null);
  }

  public User(Long id, String name, String email, String type) {
    this(id, name, email, null, type);
  }

  public User(String name, String email, String type) {
    this(null, name, email, null, type);
  }

  public User(Long id,
      String name,
      String email,
      String phoneNumber,
      String type) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.type = type;
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
        type);
  }

}
