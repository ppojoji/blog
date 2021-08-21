package naver.ppojoji.blog.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class BlogConfig extends WebMvcConfigurerAdapter{

	public BlogConfig() {
		System.out.println("[BLOG CONFIG] !!!!!!!!");
	}
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		registry.addMapping("/**")
			.allowedMethods("*")
			.allowCredentials(true) 
			.allowedOrigins("*")
			.allowedHeaders("*");
		
	}
	
}
