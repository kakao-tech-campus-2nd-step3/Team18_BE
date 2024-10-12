package team18.team18_be.userInformation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import team18.team18_be.auth.entity.User;

@Table
@Entity
public class Sign {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String imageUrl;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Sign() {
  }

  public Sign(String imageUrl, User user) {
    this.imageUrl = imageUrl;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public String getImageUrl() {
    return imageUrl;
  }
}
