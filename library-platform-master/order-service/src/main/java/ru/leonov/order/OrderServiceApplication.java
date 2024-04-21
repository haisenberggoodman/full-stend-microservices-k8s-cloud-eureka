package ru.leonov.order;

import io.sentry.Sentry;
import io.sentry.SentryLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
@RequestMapping("/orders")
public class OrderServiceApplication {

	@Value("${properties.mark}")
	private String mark;
	@GetMapping("/")
	public String list() {
		return "Order list " + mark;
	}

	@GetMapping("/{id}")
	public String bookAuthor(@PathVariable("id") String id) {
		return "Order info " + id;
	}

	public static void main(String[] args) {
		Sentry.captureMessage("Order service started!", SentryLevel.INFO);
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
