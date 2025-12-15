package hiber.controller;

import hiber.model.Car;
import hiber.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CarController {
    private final CarService carService;


    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String cars(Model model,
                       @RequestParam(value = "limit", required = false) Integer limit,
                       @RequestParam(value = "sortBy", required = false) String field,
                       @RequestParam(value = "order", required = false) String order) {

        List<Car> cars = carService.orderCars(field, order, limit);
        model.addAttribute("cars", cars);
        model.addAttribute("sortBy", field);
        model.addAttribute("order", order);
        return "cars";
    }

}
