package unsm.archivo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    WebMvcConfigurer corsConfigurer() 
    {
        return new WebMvcConfigurer() 
        {
            @SuppressWarnings("null")
			@Override
            public void addCorsMappings(CorsRegistry registry) 
            {
            	registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173/", "http://localhost:3000/")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("Authorization")
                .allowCredentials(true);
            }
        };
    }
}