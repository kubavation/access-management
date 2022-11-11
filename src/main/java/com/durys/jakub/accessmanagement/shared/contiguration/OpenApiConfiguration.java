package com.durys.jakub.accessmanagement.shared.contiguration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.APIKEY,
        scheme = "basic"
)
@OpenAPIDefinition(
        info = @Info(title = "Sample API", version = "v1")
)
public class OpenApiConfiguration {
}
