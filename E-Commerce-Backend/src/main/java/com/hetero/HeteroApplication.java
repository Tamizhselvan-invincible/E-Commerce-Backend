package com.hetero;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;


@SpringBootApplication
public class HeteroApplication {


	@Bean
	FirebaseMessaging firebaseNotification() throws IOException {

		GoogleCredentials googleCredentials = GoogleCredentials.fromStream(
				new ClassPathResource("firebase-service-account.json").getInputStream());

		FirebaseOptions firebaseOptions = FirebaseOptions.builder()
				.setCredentials(googleCredentials).build();
		FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions,"ecommerce-app");
		return FirebaseMessaging.getInstance(app);
	};

	public static void main(String[] args) {
		SpringApplication.run(HeteroApplication.class, args);
	}
	
	@Bean
	public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
	   return new OpenAPI()
	    .components(new Components().addSecuritySchemes("basicScheme",
	            new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
	    .info(new Info().title("E-Commerce Application REST API").version(appVersion)
	            .license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

}
