package jp.moskau.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;





@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
		for (String arg : args) {
			System.out.println(arg);
		}
		System.out.println("Server is running on https://localhost (192.168.1.11)");


	}

}
