package br.ufrn.imd.bioinfo.projetos.config;

import static br.ufrn.imd.bioinfo.projetos.security.SecurityConstants.SIGN_IN_URL;
import static br.ufrn.imd.bioinfo.projetos.security.SecurityConstants.SIGN_UP_URL;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.ufrn.imd.bioinfo.projetos.security.JwtTokenFilterConfigurer;
import br.ufrn.imd.bioinfo.projetos.security.JwtTokenProvider;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable();
		 
		 http.cors();

		 http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		 /*http.authorizeRequests()
			 .antMatchers(HttpMethod.POST, SIGN_IN_URL).permitAll()
			 .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
			 .antMatchers("/v2/**").permitAll()
			 .antMatchers("/swagger-ui.html").permitAll()
			 .antMatchers("/swagger-resources/**").permitAll()
			 .antMatchers("/webjars/**").permitAll()
			 .antMatchers("/configuration/ui/**").permitAll()
			 .antMatchers("/configuration/security/**").permitAll()
			 .antMatchers("/css/**", "/js/**", "/fonts/**").permitAll()
			 .antMatchers("/api/validation/**").permitAll()
			 .antMatchers("/api/reset/**").permitAll();*/
		 
		 http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.applyPermitDefaultValues();
		corsConfig.addAllowedMethod(HttpMethod.PUT);
		corsConfig.addAllowedMethod(HttpMethod.DELETE);
		corsConfig.setAllowedOrigins(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		return source;
	}

}
