package team18.team18_be.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import team18.team18_be.auth.repository.AuthRepository;
import team18.team18_be.auth.service.AuthService;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthRepository authRepository;

    public WebConfig(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Bean
    public AuthService authService() {
        return new AuthService(authRepository);
    }
}
