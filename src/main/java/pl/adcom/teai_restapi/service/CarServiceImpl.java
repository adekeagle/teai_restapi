package pl.adcom.teai_restapi.service;

import org.springframework.stereotype.Service;
import pl.adcom.teai_restapi.model.Car;
import pl.adcom.teai_restapi.model.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private List<Car> carList;

    public CarServiceImpl() {
        carList = new ArrayList<>();
        carList.add(new Car(1L, "Fiat", "Uno", Color.CZERWONY));
        carList.add(new Car(2L, "VW", "Polo", Color.BIAŁY));
        carList.add(new Car(3L, "Ford", "Mondeo", Color.CZARNY));
    }

    @Override
    public List<Car> findAllCars() {
        return carList;
    }

    @Override
    public Optional<Car> findCarById(long id) {
        return carList.stream().filter(car -> car.getId() == id).findFirst();
    }

    @Override
    public List<Car> findCarByColor(Color color) {
        return carList.stream().filter(e -> e.getColor().equals(color)).collect(Collectors.toList());
    }

    @Override
    public boolean addCar(Car car) {
        return carList.add(car);
    }

    @Override
    public Optional<Car> modifyCar(Car newCar) {
        Optional<Car> modCar = carList.stream().filter(carId -> carId.getId() == newCar.getId()).findFirst();

        if (modCar.isPresent()) {
            carList.remove(modCar.get());
            carList.add(newCar);
        }
        return modCar;
    }

    @Override
    public Optional<Car> changeColorCarById(Color newColor, long id) {
        Optional<Car> modColor = carList.stream().filter(carId -> carId.getId() == id).findFirst();

        if (modColor.isPresent()) {
            modColor.get().setColor(newColor);
        }
        return modColor;
    }

    @Override
    public Optional<Car> removeCarById(long id) {
        Optional<Car> delCarId = carList.stream().filter(carId -> carId.getId() == id).findFirst();

        if (delCarId.isPresent()) {
            carList.remove(delCarId.get());
        }
        return delCarId;
    }
}
