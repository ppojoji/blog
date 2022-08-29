package naver.ppojoji.blog.web;

import java.io.File;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class BlogConfig extends WebMvcConfigurerAdapter{

	@Autowired
    ApplicationContext ctx;
	
	@Value("${blog.app.mode}")
	String appMode;
	
	@Value("${blog.upfile.root}")
	String filePath;
	
	public BlogConfig() {
	}
	
	@PostConstruct
	public void printApp() {		
		System.out.println("[BLOG CONFIG] !!!!!!!!");
		Environment environment = ctx.getEnvironment();
		System.out.println(" * [MODE   ] " + appMode);
		System.out.println(" * [Path   ] " + filePath);
		System.out.println(" * [PROFILE] " + Arrays.toString(environment.getActiveProfiles()));
		
		File rootDir = new File(filePath, ".");
		System.out.println("[path exist] " + rootDir.exists());
		System.out.println("[path read ] " + rootDir.canRead());
		System.out.println("[path write] " + rootDir.canWrite());
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
