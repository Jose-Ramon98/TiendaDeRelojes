package com.relojes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Configuration//Cuando es de seguridad hay que poner lo de configuration
public class SecurityAdapter  extends WebSecurityConfigurerAdapter{
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
	
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
	    .authorizeRequests()
	        .antMatchers("/","/crear/**","/js/**","/src/main/resouces/**","/src/main/resources/templates/**", "/resources/**",
	        		"/inicio", "/reloj/ordenar/**", "/reloj/filtrar/**", "/reloj/filtrar/**",("/descripcion/**"),
	            "/folder/**").permitAll()
	        .antMatchers("/reloj/{id}/editar/**").hasAnyRole("ADMIN")
	        .antMatchers("/reloj/{id}/eliminar/**").hasAnyRole("ADMIN")
	        .and()
	    .formLogin()
	        .loginPage("/login")
	        .permitAll()
	        .and()
	    .logout()
	        .permitAll()
	        .and()
	    .authorizeRequests()
	        .anyRequest()
	        .authenticated();
	}

	@Autowired//inyectamos una interfaz que ya te da springboot
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception{
		
		PasswordEncoder encoder= passwordEncoder();// PasswordEncoder funcion con ese nombre dada ya por Spring
		////UserBuilder que permite crear usuarios con contraseñas encriptadas usando el objeto BCryptPasswordEncoder	
		UserBuilder users = User.builder().passwordEncoder(encoder::encode);
		
		builder.inMemoryAuthentication()//Este es el ususario y contraseña literal, para el usuario admin, aqui esta sin ecriptar
		.withUser(users.username("admin").password("12345").roles("ADMIN","USER")).//voy a tener 2 roles
		 withUser(users.username("andres").password("12345").roles("USER"));

		}
	
	
	
	}
