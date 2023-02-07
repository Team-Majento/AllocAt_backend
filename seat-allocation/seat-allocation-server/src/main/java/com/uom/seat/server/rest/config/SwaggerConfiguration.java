package com.uom.seat.server.rest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration Url - http://host:port/swagger-ui.html
 * Reference : https://dzone.com/articles/spring-boot-2-restful-api-documentation-with-swagg
 * https://github.com/swagger-api/swagger-core/wiki/Annotations
 * 
 * @author rangalal
 */
@Configuration
@EnableSwagger2
@ConfigurationProperties(prefix = "api.swagger")
public class SwaggerConfiguration {

	private String swaggerHost;

	@Bean
	public Docket productApi() {

		Docket docket = new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.uom.seat.server.rest.controller")).build().apiInfo(metaData());
		docket.host(swaggerHost);
		//docket.useDefaultResponseMessages(false);
		return docket;
	}

	private ApiInfo metaData() {

		String title = "Seat Server";
		String description = "API Documentation";
		String version = "1.0.0";
		String termsOfServiceUrl = "";
		Contact contactPerson = new Contact("UOM Team", "", "info@gmail.com");
		String license = "";
		String licenseUrl = "";

		return new ApiInfo(title, description, version, termsOfServiceUrl, contactPerson, license, licenseUrl);
	}

	/**
	 * @return the swaggerHost
	 */
	public String getSwaggerHost() {
		return swaggerHost;
	}

	/**
	 * @param swaggerHost the swaggerHost to set
	 */
	public void setSwaggerHost(String swaggerHost) {
		this.swaggerHost = swaggerHost;
	}

}
