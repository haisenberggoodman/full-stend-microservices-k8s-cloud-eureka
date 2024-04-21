package ru.leonov.profile.config;

import io.sentry.Sentry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SentryConfig {

//    @Bean
//    public boolean sentryInit(@Value("${sentry.dsn}") String dsn) {
//        Sentry.init(options -> options.setDsn(dsn));
//        return Sentry.isEnabled();
//    }

}
