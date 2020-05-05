package pl.adcom.teai_restapi.service;

import pl.adcom.teai_restapi.model.Car;
import pl.adcom.teai_restapi.model.Color;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> findAllCars();
    Optional<Car> findCarById(long id);
    List<Car> findCarByColor(Color color);
    boolean addCar(Car car);
    Optional<Car> modifyCar(Car car);
    Optional<Car> changeColorCarById(Color color, long id);
    Optional<Car> removeCarById(long id);
}
