package co.id.tmli.illustration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"co.id.tmli.illustration"})
public class IllustrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(IllustrationApplication.class, args);
	}

}
