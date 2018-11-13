package nl.jtim.spring.kafka.consumer;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import io.micrometer.core.instrument.logging.LoggingRegistryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

import java.time.Duration;

@SpringBootApplication
@EnableKafka
public class SpringKafkaConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringKafkaConsumerApplication.class, args);
  }

  @Bean
  public LoggingMeterRegistry loggingMeterRegistry() {
    LoggingMeterRegistry loggingMeterRegistry = new LoggingMeterRegistry(new LoggingRegistryConfig() {
      @Override
      public String get(String key) {
        return null;
      }

      @Override
      public Duration step() {
        return Duration.ofSeconds(10);
      }
    }, Clock.SYSTEM);

//    loggingMeterRegistry.config()
//      .meterFilter(MeterFilter.accept(id -> id.getName().startsWith("kafka")))
//      .meterFilter(MeterFilter.deny());

    return loggingMeterRegistry;
  }
}
