package ru.leonov.config;

import io.sentry.Sentry;
import io.sentry.SentryLevel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigServer
@EnableDiscoveryClient
@EnableAutoConfiguration
@SpringBootApplication
public class ConfigServiceApplication {

	public static void main(String[] args) {
		Sentry.captureMessage("Config service started!", SentryLevel.INFO);
		SpringApplication.run(ConfigServiceApplication.class, args);
	}

}
