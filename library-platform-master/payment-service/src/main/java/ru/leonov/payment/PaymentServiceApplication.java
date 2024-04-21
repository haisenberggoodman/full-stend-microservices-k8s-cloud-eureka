package ru.leonov.payment;

import io.sentry.Sentry;
import io.sentry.SentryLevel;
import org.apache.commons.lang.NotImplementedException;
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
@RequestMapping("/payments")
public class PaymentServiceApplication {

	@Value("${properties.mark}")
	private String mark;
	@GetMapping("/")
	public String list() {
		return "Payment list " + mark;
	}

	@GetMapping("/{paymentId}")
	public String payment(@PathVariable("paymentId") Long id) {
		try {
			throw new NotImplementedException(String.format("Not implemented method! Input param %d", id));
		} catch (Exception e) {
			Sentry.captureException(e);
			throw e;
		}
	}

	public static void main(String[] args) {
		Sentry.captureMessage("Payment service started!", SentryLevel.INFO);
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

}
