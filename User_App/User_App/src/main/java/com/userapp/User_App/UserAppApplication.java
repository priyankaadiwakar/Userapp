package com.userapp.User_App;

import com.userapp.User_App.filter.JWTfilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserAppApplication {
	@Bean   //without login we can perform getAllUser method()
	public FilterRegistrationBean jwtFilter(){
		FilterRegistrationBean obj = new FilterRegistrationBean();
		obj.setFilter(new JWTfilter());
		obj.addUrlPatterns("/api/user/*");
		return obj;
	}

	public static void main(String[] args) {
		SpringApplication.run(UserAppApplication.class, args);
	}

}
