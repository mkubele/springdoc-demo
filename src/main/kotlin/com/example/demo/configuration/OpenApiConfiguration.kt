package com.example.demo.configuration

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.media.IntegerSchema
import io.swagger.v3.oas.models.media.StringSchema
import io.swagger.v3.oas.models.parameters.HeaderParameter
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.GroupedOpenApi
import org.springdoc.core.customizers.OpenApiCustomiser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfiguration {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .components(
                Components()
                    .addSecuritySchemes("basicScheme", SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic"))
            )
    }

    @Bean
    fun internalOpenApi(): GroupedOpenApi {
        val paths = "/v1/**"
        return GroupedOpenApi.builder().group("v1").pathsToMatch(paths)
            .build()
    }

    @Bean
    fun authOpenApi(): GroupedOpenApi {
        val paths = "/v2/**"
        return GroupedOpenApi.builder().group("v2").pathsToMatch(paths)
            .build()
    }

    @Bean
    fun customerGlobalHeaderOpenApiCustomiser(): OpenApiCustomiser {
        return OpenApiCustomiser { openApi ->
            openApi.paths.values.forEach { operation ->
                operation.parameters(
                    listOf(
                        HeaderParameter().name("Version").required(true).schema(IntegerSchema()),
                        HeaderParameter().name("Platform").required(true).schema(StringSchema()),
                        HeaderParameter().name("Id").required(true).schema(StringSchema()),
                    )
                )
            }
        }
    }
}
