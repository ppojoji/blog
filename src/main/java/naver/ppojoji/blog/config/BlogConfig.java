package naver.ppojoji.blog.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

@Configuration
public class BlogConfig {

	@PostConstruct
	public void printBlogEnv() {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> ok?");
	}
}
