package com.example.gestortareas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
//import org.springframework.context.annotation.ComponentScan;
//import com.example.gestortareas.security.SecurityConfig;

import com.example.gestortareas.controller.TareaController; 
import com.example.gestortareas.service.TareaService;
import com.example.gestortareas.config.TokenFilter;

@SpringBootApplication
@Import({TareaController.class, TareaService.class, TokenFilter.class})
//@ComponentScan(basePackages = "com.example.gestortareas")
//@Import(SecurityConfig.class)
public class GestorTareasApplication {
	public static void main(String[] args) {
		SpringApplication.run(GestorTareasApplication.class, args);
	}

}
