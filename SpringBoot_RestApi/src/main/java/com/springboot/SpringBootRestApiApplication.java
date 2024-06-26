package com.springboot;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springboot.entity.Role;
import com.springboot.repository.RoleRepository;

@SpringBootApplication
public class SpringBootRestApiApplication implements CommandLineRunner{
@Autowired
private RoleRepository roleRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role userRole = new Role();
		userRole.setName("ROLE_USER");
		roleRepository.save(userRole);
		
		Role adminRole = new Role();
		adminRole.setName("ROLE_ADMIN");
		roleRepository.save(adminRole);
	}

}
