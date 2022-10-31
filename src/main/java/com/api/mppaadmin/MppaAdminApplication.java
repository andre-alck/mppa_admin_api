package com.api.mppaadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MppaAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(MppaAdminApplication.class, args);
	}
}
