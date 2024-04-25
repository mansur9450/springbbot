package com.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.springboot.repository.RoleRepository;
import com.springboot.repository.UserRepository;

@Configuration// it help us to define config layer
@EnableWebSecurity// pop up
@EnableGlobalMethodSecurity(prePostEnabled = true)// @PreAuthorize("hasRole('ADMIN')") iske liye use
public class SecurityConfig extends WebSecurityConfigurerAdapter {// press ctrl+o
    @Autowired
    private CustomUserDetailsService userDetailsService;



	@Bean
	PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      //hcd4ah
    	http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api").permitAll()
                .antMatchers(HttpMethod.GET, "/api/auth/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override// for database security config
    
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(PasswordEncoder());
    }
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}
	

	//@Override
	//@Bean
	//protected UserDetailsService userDetailsService() {// source
		//UserDetails mansur =  User.builder().username("mansur").password(PasswordEncoder().encode("MANsur@123")).roles("USER").build();
		//UserDetails admin =  User.builder().username("admin").password(PasswordEncoder().encode("admin@123")).roles("ADMIN").build();
		//return new InMemoryUserDetailsManager(mansur,admin);
	

}
   
    
    
    
