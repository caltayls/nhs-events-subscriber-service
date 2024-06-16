package com.caltayls.nhs_events_subscribers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.caltayls")
public class NhsEventsSubscribersApplication {

	public static void main(String[] args) {
		SpringApplication.run(NhsEventsSubscribersApplication.class, args);
	}

}
