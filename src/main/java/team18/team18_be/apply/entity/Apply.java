package team18.team18_be.apply.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Table
@Entity
public class Apply {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String status;

  private Long userId;
  private Long recruitmentId;

  public Long getUserId() {
    return this.getUserId();
  }
}
