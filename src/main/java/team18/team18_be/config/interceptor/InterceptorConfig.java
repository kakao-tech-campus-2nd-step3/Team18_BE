package team18.team18_be.config.interceptor;

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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtValidationInterceptor())
            .addPathPatterns("/api/register");
    }
}
