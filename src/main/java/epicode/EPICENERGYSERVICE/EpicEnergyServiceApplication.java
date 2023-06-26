package epicode.EPICENERGYSERVICE;

import java.io.FileNotFoundException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EpicEnergyServiceApplication {

	public static void main(String[] args) throws IllegalStateException, FileNotFoundException {
		SpringApplication.run(EpicEnergyServiceApplication.class, args);
	}

}
