package team18.team18_be.userInformation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import team18.team18_be.auth.entity.User;

@Table
@Entity
public class ForeignerInformation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String foreignerIdNumber;
  private LocalDate visaGenerateDate;
  private LocalDate visaExpiryDate;
  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  public ForeignerInformation() {
  }

  public ForeignerInformation(Long id, String foreignerIdNumber, LocalDate visaGenerateDate,
      LocalDate visaExpiryDate, User user) {
    this.id = id;
    this.foreignerIdNumber = foreignerIdNumber;
    this.visaGenerateDate = visaGenerateDate;
    this.visaExpiryDate = visaExpiryDate;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public String getForeignerIdNumber() {
    return foreignerIdNumber;
  }

  public LocalDate getVisaGenerateDate() {
    return visaGenerateDate;
  }

  public LocalDate getVisaExpiryDate() {
    return visaExpiryDate;
  }

  public User getUser() {
    return user;
  }
}
