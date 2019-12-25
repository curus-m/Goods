package com.myServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyServerApplication.class, args);
		
		String accessKeyId = System.getProperty("aws.accessKeyId");
		String secretKey = System.getProperty("aws.secretKey");
		
		
		String a = System.getProperty("accessKeyId");
		System.out.println("accessKey: " + accessKeyId + " , secretKey: "+ secretKey );
		System.out.println("accessKey2: " + a);

	}

}
