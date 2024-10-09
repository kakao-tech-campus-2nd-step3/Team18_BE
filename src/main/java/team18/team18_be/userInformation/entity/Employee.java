package team18.team18_be.userInformation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import team18.team18_be.auth.entity.User;

@Table
@Entity
public class Employee {
  @Id
  private final Long id;
  private String foreginerNumber;
  private LocalDateTime visaGenerateDate;
  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Employee(Long id, String foreginerNumber, LocalDateTime visaGenerateDate, User user) {
    this.id = id;
    this.foreginerNumber = foreginerNumber;
    this.visaGenerateDate = visaGenerateDate;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public String getForeginerNumber() {
    return foreginerNumber;
  }

  public LocalDateTime getVisaGenerateDate() {
    return visaGenerateDate;
  }

  public User getUser() {
    return user;
  }
}
