package esprit.exam.examenboutique;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ExamenBoutiqueApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamenBoutiqueApplication.class, args);
    }

}
