package ru.leonov.product;

import io.sentry.Sentry;
import io.sentry.SentryLevel;
import io.sentry.spring.tracing.SentrySpan;
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
@RequestMapping("/products")
public class ProductServiceApplication {

	@Value("${properties.mark}")
	private String mark;

	@SentrySpan
	@GetMapping("/")
	public String list() {
		return "Product list " + mark;
	}

	@SentrySpan
	@GetMapping("/{id}")
	public String bookAuthor(@PathVariable("id") String id) {
		return "Product info " + id;
	}

	public static void main(String[] args) {
		Sentry.captureMessage("Product service started!", SentryLevel.INFO);
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
