package hiber.dao;

import hiber.model.Car;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDao {
    List<Car> orderCars(String field, String order, Integer limit);
}
