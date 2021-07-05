package com.quoctoanphamtoan.person_post;

import com.quoctoanphamtoan.person_post.common.SpringSecurityAuditorAware;
import com.quoctoanphamtoan.person_post.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PersonPostApplication {
    @Bean
    public AuditorAware<User> auditorAware() {
        return new SpringSecurityAuditorAware();
    }
    public static void main(String[] args) {
        SpringApplication.run(PersonPostApplication.class, args);
    }

}
