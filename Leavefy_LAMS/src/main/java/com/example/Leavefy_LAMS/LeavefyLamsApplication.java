package com.example.Leavefy_LAMS;

import com.example.Leavefy_LAMS.Model.Department;
import com.example.Leavefy_LAMS.Model.Role;
import com.example.Leavefy_LAMS.Model.User;
import com.example.Leavefy_LAMS.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication

public class LeavefyLamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeavefyLamsApplication.class, args);
	}

}

