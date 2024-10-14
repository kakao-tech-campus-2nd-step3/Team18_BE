package team18.team18_be.config.interceptor;

import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import team18.team18_be.auth.repository.AuthRepository;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

  private final AuthRepository authRepository;

  public InterceptorConfig(AuthRepository authRepository) {
    this.authRepository = authRepository;
  }

  @Bean
  public JwtValidationInterceptor jwtValidationInterceptor() {
    return new JwtValidationInterceptor(authRepository);
  }

  @Bean
  public JwtValidationInterceptor postOnlyJwtValidationInterceptor() {
    return new JwtValidationInterceptor(authRepository, Set.of("POST"));
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(jwtValidationInterceptor())
        .addPathPatterns(InterceptorPath.REGISTER.getPath())
        .addPathPatterns(InterceptorPath.RECRUITMENT_USER.getPath())
        .addPathPatterns(InterceptorPath.APPLICATION_ALL.getPath())
        .addPathPatterns(InterceptorPath.RESUME_ALL.getPath())
        .addPathPatterns(InterceptorPath.SIGN_ALL.getPath())
        .addPathPatterns(InterceptorPath.COMPANY_ALL.getPath())
        .addPathPatterns(InterceptorPath.VISA.getPath())
        .addPathPatterns(InterceptorPath.CONTRACT_ALL.getPath())
        .excludePathPatterns(InterceptorPath.CONTRACT_PREVIEW.getPath());

    registry.addInterceptor(postOnlyJwtValidationInterceptor())
        .addPathPatterns(InterceptorPath.RECRUITMENT.getPath());
  }
}
