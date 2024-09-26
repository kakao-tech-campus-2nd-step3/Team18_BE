package team18.team18_be.config.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import team18.team18_be.auth.repository.AuthRepository;

import java.util.Set;

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
            .addPathPatterns("/api/register")
            .addPathPatterns("/api/recruitments/user")
            .addPathPatterns("/api/application/**")
            .addPathPatterns("/api/resumes/**")
            .addPathPatterns("/api/sign/**")
            .addPathPatterns("/api/company/**")
            .addPathPatterns("/api/visa")
            .addPathPatterns("/api/contract/**")
            .excludePathPatterns("/api/contract/*/preview");

    registry.addInterceptor(postOnlyJwtValidationInterceptor())
            .addPathPatterns("/api/recruitments");
  }
}
