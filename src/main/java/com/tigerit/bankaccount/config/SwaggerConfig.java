package com.tigerit.bankaccount.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.DefaultPathProvider;
import springfox.documentation.spring.web.paths.Paths;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {

	@Value("${server.servlet.context-path}")
	private String contextPath;

	@Bean
	public Docket greetingApi() {
		return new Docket( DocumentationType.SWAGGER_2)
				.pathProvider( new CustomPathProvider() )
				.select()
				.apis( RequestHandlerSelectors.basePackage("com.tigerit.bankaccount"))
				.paths( PathSelectors.any())
				.build()
				.apiInfo(metaData());

	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder()
				.title("Bank Account API")
				.description("Project created for bank operations")
				.version("1.0.0")
				.build();
	}

	private class CustomPathProvider extends DefaultPathProvider {
		@Override
		public String getOperationPath(String operationPath) {
			if (operationPath.startsWith(contextPath)) {
				operationPath = operationPath.substring(contextPath.length());
			}
			return Paths
					.removeAdjacentForwardSlashes( UriComponentsBuilder.newInstance().replacePath(operationPath)
					.build().toString());
		}
	}

}
