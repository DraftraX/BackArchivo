package unsm.archivo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import unsm.archivo.jwt.JwtAuthenticationFilter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authProvider;

	public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authProvider) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.authProvider = authProvider;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable())
				.cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.authorizeHttpRequests(authRequest -> authRequest
						.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Preflight CORS
						.requestMatchers("/auth/**", "/change-password/**", "/usuario/nuevousuario").permitAll()
						.requestMatchers("/resolucion/**", "/gradotitulos/**", "/usuario/**", "/visita/**")
						.hasAnyAuthority("ADMINISTRADOR", "JEFE ARCHIVO", "SECRETARIA", "USUARIO")
						.anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
				.authenticationProvider(authProvider)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		// Origen permitido (frontend en Render)
		configuration.setAllowedOrigins(Collections.singletonList("https://archivo-frontend.onrender.com"));

		// Métodos HTTP permitidos
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

		// Headers que puede recibir el backend desde el cliente
		// Incluye TODOS los que usarás (¨Content-Type¨ y cualquier otro header
		// personalizado)
		configuration.setAllowedHeaders(Arrays.asList(
				"Authorization",
				"Content-Type",
				"X-Requested-With"));

		// Headers que el navegador puede exponer al frontend
		configuration.setExposedHeaders(Collections.singletonList("Authorization"));

		// Permitir enviar credenciales (cookies, auth headers, etc.)
		configuration.setAllowCredentials(true);

		// Cuánto tiempo cachear el resultado del preflight
		configuration.setMaxAge(3600L);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		// Aplica esta política de CORS a todos los endpoints
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}
}