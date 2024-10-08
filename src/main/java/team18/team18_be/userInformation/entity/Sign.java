package team18.team18_be.userInformation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import team18.team18_be.auth.entity.User;

@Table
@Entity
public class Sign {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private final Long id;
  @NotBlank
  private String imageName;

  private String imageUrl;

  @OneToOne
  @JoinColumn(name="user_id")
  private User user;

  public Sign(Long id, String imageName, User user) {
    this.id = id;
    this.imageName = imageName;
    this.user=user;
  }
}
