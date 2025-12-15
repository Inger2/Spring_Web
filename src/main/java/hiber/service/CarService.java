package hiber.service;

import hiber.model.Car;
import jakarta.persistence.TypedQuery;

import java.util.List;

public interface CarService {
    List<Car> orderCars(String field, String order, Integer limit);


}
