package cn.joylau.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
@EnableScheduling
public class AwtrixRestApiApplication {

	private final RestTemplateBuilder restTemplateBuilder;

	public AwtrixRestApiApplication(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplateBuilder = restTemplateBuilder;
	}

	public static void main(String[] args) {
		SpringApplication.run(AwtrixRestApiApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		return restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(10))
				.setReadTimeout(Duration.ofSeconds(10))
				.build();
	}

}
