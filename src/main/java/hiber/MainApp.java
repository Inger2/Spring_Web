package hiber;

import hiber.service.CarService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class MainApp {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = SpringApplication.run(MainApp.class, args);
        CarService carService = context.getBean(CarService.class);


    }
}
