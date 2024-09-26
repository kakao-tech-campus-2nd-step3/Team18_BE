package team18.team18_be.config.resolver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import team18.team18_be.auth.repository.AuthRepository;

import java.util.List;

@Configuration
public class ResolverConfig implements WebMvcConfigurer {

  private final AuthRepository authRepository;

  public ResolverConfig(AuthRepository authRepository) {
    this.authRepository = authRepository;
  }

  @Bean
  public LoginUserMethodArgumentResolver loginUserMethodArgumentResolver() {
    return new LoginUserMethodArgumentResolver(authRepository);
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(loginUserMethodArgumentResolver());
  }
}
