package team18.team18_be.apply.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import team18.team18_be.auth.entity.User;
import team18.team18_be.recruitment.entity.Recruitment;

@Table
@Entity
public class  Apply {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String status;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  @ManyToOne
  @JoinColumn(name = "recruitment_id")
  private Recruitment recruitment;

  public Apply() {
  }

  public Apply(String status, User user, Recruitment recruitment) {
    this.status = status;
    this.user = user;
    this.recruitment = recruitment;
  }

  public Long getId() {
    return id;
  }

  public String getStatus() {
    return status;
  }

  public User getUser() {
    return user;
  }

  public Recruitment getRecruitment() {
    return recruitment;
  }
}
