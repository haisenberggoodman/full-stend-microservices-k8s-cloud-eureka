package ru.leonov.profile;

import io.sentry.Sentry;
import io.sentry.SentryLevel;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@EnableDiscoveryClient
@SpringBootApplication
public class ProfileServiceApplication {

	@Value("${properties.mark}")
	private String mark;

	public static void main(String[] args) {
		Sentry.captureMessage("Profile service started!", SentryLevel.INFO);
		SpringApplication.run(ProfileServiceApplication.class, args);
	}

}
