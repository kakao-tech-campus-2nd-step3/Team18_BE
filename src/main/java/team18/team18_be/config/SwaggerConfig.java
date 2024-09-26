package team18.team18_be.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;

import java.util.Set;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "hire higher API",
        description = "hire higher 서비스 플랫폼 API 제공",
        version = "1.0.0"
))
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        SecurityScheme apiKey = new SecurityScheme()
                .type(Type.APIKEY)
                .in(In.HEADER)
                .name("Authorization");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("Bearer Token");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Bearer Token", apiKey))
                .addSecurityItem(securityRequirement);
    }

    @Bean
    public OpenApiCustomizer customAuthParameter() {
        Set<String> targetPaths = Set.of(
                "/api/register"
        );

        return openApi -> openApi
                .getPaths()
                .forEach((path, pathItem) -> {
                    boolean isTargetPath = false;
                    for (String targetPath : targetPaths) {
                        if (path.startsWith(targetPath)) {
                            isTargetPath = true;
                            break;
                        }
                    }

                    if (isTargetPath) {
                        pathItem.readOperations().forEach(
                                this::addAuthParam
                        );
                    }
                });
    }

    private void addAuthParam(Operation operation) {
        operation.addParametersItem(new HeaderParameter()
                .name("Authorization")
                .description("액세스 토큰")
                .required(true)
                .schema(new StringSchema()));
    }
}
