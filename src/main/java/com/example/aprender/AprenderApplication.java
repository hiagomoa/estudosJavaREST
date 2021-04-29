package com.example.aprender;

import com.example.aprender.gateway.UserGateway;
import com.example.aprender.usecases.CreateUser;
import com.example.aprender.usecases.DeleteUser;
import com.example.aprender.usecases.SearchById;
import com.example.aprender.usecases.UpdateUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AprenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(AprenderApplication.class, args);
	}

	@Bean
	public CreateUser createUser(UserGateway userGateway) {
		return new CreateUser(userGateway);
	}

	@Bean
	public SearchById searchById(UserGateway userGateway) {
		return new SearchById(userGateway);
	}

	@Bean
	public UpdateUser updateUser(UserGateway userGateway) {
		return new UpdateUser(userGateway);
	}

	@Bean
	public DeleteUser deleteUser(UserGateway userGateway) { return new DeleteUser(userGateway); }

}
