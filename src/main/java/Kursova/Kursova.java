package Kursova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("Kursova.entity")
public class Kursova {

	public static void main(String[] args) {
		SpringApplication.run(Kursova.class, args);
	}

}
