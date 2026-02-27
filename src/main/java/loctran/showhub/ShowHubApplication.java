package loctran.showhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ShowHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShowHubApplication.class, args);
	}

}
