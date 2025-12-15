package hiber.service;

import hiber.dao.CarDao;
import hiber.model.Car;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CarServiceImp implements CarService {

    private final CarDao carDao;
    @Value("${max.value.key}")
    private int maxValue;

    @Value("${allowedFields}")
    private List<String> allowedFields;
    @Value("${allowedOrder}")
    private List<String> allowedOrder;

    @Autowired
    public CarServiceImp(CarDao carDao) {
        this.carDao = carDao;
    }

    @Transactional
    @Override
    public List<Car> orderCars(String field, String order, Integer limit) {
        field = validateField(field);
        order = validateOrder(order);
        limit = validateLimit(limit);
        validateRequest(field, order);
        return carDao.orderCars(field, order, limit);
    }

    private Integer validateLimit(Integer limit) {
        if (limit == null || limit >= maxValue) {
            return Integer.MAX_VALUE;
        }
        return limit;
    }
    private String validateOrder(String order) {
        if (order == null) {
            return "asc";
        }
        return order;
    }

    private String validateField(String field) {
        if (field == null) {
            return "id";
        }
        return field;
    }

    private void validateRequest(String field, String order) {
        if (!allowedFields.contains(field)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid field");
        } else  if (!allowedOrder.contains(order)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid order");
        }
    }


}
