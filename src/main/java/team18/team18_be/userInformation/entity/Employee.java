package team18.team18_be.userInformation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import team18.team18_be.auth.entity.User;

@Table
@Entity
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String foreginerNumber;
  private String visaGenerateDate;
  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Employee() {
  }

  public Employee(Long id, String foreginerNumber, String visaGenerateDate, User user) {
    this.id = id;
    this.foreginerNumber = foreginerNumber;
    this.visaGenerateDate = visaGenerateDate;
    this.user = user;
  }

  public Employee(String foreginerNumber, String visaGenerateDate) {
    this.foreginerNumber = foreginerNumber;
    this.visaGenerateDate = visaGenerateDate;
  }
  public Long getId() {
    return id;
  }

  public String getForeginerNumber() {
    return foreginerNumber;
  }

  public String getVisaGenerateDate() {
    return visaGenerateDate;
  }

  public User getUser() {
    return user;
  }
}
