package com.grainger.opa.demo.config;

import com.grainger.opa.demo.model.Salary;
import com.grainger.opa.demo.repository.SalaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(SalaryRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Salary("bob", 100000)));
      log.info("Preloading " + repository.save(new Salary("john", 50000)));
      log.info("Preloading " + repository.save(new Salary("dave", 70000)));
      log.info("Preloading " + repository.save(new Salary("alice", 120000)));
      log.info("Preloading " + repository.save(new Salary("carol", 100000)));
    };
  }
}
