package az.edu.ada.wm2.resfuldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ResfulDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResfulDemoApplication.class, args);
	}

}
