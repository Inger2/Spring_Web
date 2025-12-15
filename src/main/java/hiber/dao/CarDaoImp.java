package hiber.dao;

import hiber.model.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CarDaoImp implements CarDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Car> orderCars(String field, String order, Integer limit) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> query = builder.createQuery(Car.class);
        Root<Car> root = query.from(Car.class);
        if (order.equals("asc")) {
            query.orderBy(builder.asc(root.get(field)));
        } else {
            query.orderBy(builder.desc(root.get(field)));
        }
        TypedQuery<Car> typedQuery = entityManager.createQuery(query);
        typedQuery.setMaxResults(limit);
        return typedQuery.getResultList();
    }


}
