package com.github.fa2bio.core.springfox;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SpringFoxConfig {

	@Bean
	public Docket apiDocket() {
			
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.github.fa2bio.api"))
					.paths(PathSelectors.any())
					.build()
					.useDefaultResponseMessages(false)
					.globalResponses(HttpMethod.GET, globalGetResponseMessages())
					.globalResponses(HttpMethod.POST, globalPostPutResponseMessages())
					.globalResponses(HttpMethod.PUT, globalPostPutResponseMessages())
					.globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
					.apiInfo(apiInfo())
					.tags(new Tag("Clients", "Manipulate the Clients entity."),
							new Tag("Employees", "Manipulate the Employees entity."),
							new Tag("Clients - Books", "Handles Relationships Between Clients and Books."),
							new Tag("Employees - Clients", "Handles Relationships Between Employees and Clients."),
							new Tag("Books", "Manipulate the Books entity"));							
		
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("LIBRARY-API")
				.description("A Simple Library")
				.version("1")
				.contact(new Contact("Fa2bio", "https://www.github.com/Fa2bio", "fabio.s0@hotmail.com"))
				.build();
	}
	
	private List<Response> globalGetResponseMessages(){
		return Arrays.asList(
				new ResponseBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.toString())
					.description("Internal server error")
					.build(),
				new ResponseBuilder()
					.code(HttpStatus.NOT_ACCEPTABLE.toString())
					.description("Resource has no representation that could be accepted by the consumer")
					.build()
				);
	}
	
	private List<Response> globalPostPutResponseMessages(){
		return Arrays.asList(
			new ResponseBuilder()
				.code(HttpStatus.BAD_REQUEST.toString())
				.description("Invalid request (client error)")
				.build(),
			new ResponseBuilder()
				.code(HttpStatus.INTERNAL_SERVER_ERROR.toString())
				.description("Internal server error")
				.build(),
			new ResponseBuilder()
				.code(HttpStatus.NOT_ACCEPTABLE.toString())
				.description("Resource has no representation that could be accepted by the consumer")
				.build(),
			new ResponseBuilder()
				.code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.toString())
				.description("Request refused because the body is in an unsupported format")
				.build()
			);
	}
	
	private List<Response> globalDeleteResponseMessages(){
		return Arrays.asList(
				new ResponseBuilder()
					.code(HttpStatus.BAD_REQUEST.toString())
					.description("Invalid request (client error)")
					.build(),
					new ResponseBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.toString())
					.description("Internal server error")
					.build()
				
				
				);
	}

}
