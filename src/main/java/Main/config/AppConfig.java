package Main.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    ModelMapper modelMapper() {
		return new ModelMapper();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) { return authenticationConfiguration.getAuthenticationManager(); }

//    @Bean
//    UserDetailsService userDetailService() {
//    	UserDetails user1 = User.withUsername("admin")
//    			.password(passwordEncoder().encode("123"))
//    			.roles("ADMIN")
//    			.build();
//
//    	UserDetails user2 = User.withUsername("patient")
//    			.password(passwordEncoder().encode("456"))
//    			.roles("PATIENT")
//    			.build();
//
//    	return new InMemoryUserDetailsManager(user1, user2); // It handles loadUserByUsername functionality
//    }
}
