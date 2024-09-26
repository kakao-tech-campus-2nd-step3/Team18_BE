package team18.team18_be.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import team18.team18_be.JwtValidationInterceptor;
import team18.team18_be.auth.repository.AuthRepository;

public class WebConfig implements WebMvcConfigurer {

    private final AuthRepository authRepository;

    public WebConfig(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Bean
    public JwtValidationInterceptor jwtValidationInterceptor() {
        return new JwtValidationInterceptor(authRepository);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtValidationInterceptor())
            .addPathPatterns("/api/register");
    }
}
