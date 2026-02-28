package Main;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
//import jakarta.annotation.PreDestroy;

@SpringBootApplication
public class EnterSpringBootAppliction {
	@PostConstruct
	public void appStarted() {
		System.out.println("The application has been started!");
	}
	
//	@PreDestroy
//	public void appClosed() {
//		System.out.println("The application has been closed!");
//	}
}
