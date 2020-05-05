package pl.adcom.teai_restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.adcom.teai_restapi.model.Car;
import pl.adcom.teai_restapi.model.Color;
import pl.adcom.teai_restapi.service.CarService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cars", produces = {
        MediaType.APPLICATION_XML_VALUE,
        MediaType.APPLICATION_JSON_VALUE })
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping()
    public ResponseEntity<List<Car>> getAllCars(){
        return new ResponseEntity<>(carService.findAllCars(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getAllCars(@PathVariable long id){

        Optional<Car> car = carService.findCarById(id);

        if (car.isPresent()){
            return new ResponseEntity<>(car.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Car>> getCarsByColor(@PathVariable String color){

        try{
            List<Car> carList = carService.findCarByColor(Color.valueOf(color.toUpperCase()));
            if (!carList.isEmpty()) {
                return new ResponseEntity<>(carList, HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping()
    public ResponseEntity addNewCar(@Validated @RequestBody Car car){

        boolean isAdded = carService.addCar(car);

        if (isAdded) {
            return new ResponseEntity<>("Nowy pojazd został dodany ", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping()
    public ResponseEntity modCar(@Validated @RequestBody Car car) {
        Optional<Car> modCar = carService.modifyCar(car);

        if (modCar.isPresent()) {
            return new ResponseEntity<>("Zmodyfikowano pojazd o id " + modCar.get().getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity modColorCarById(@Validated @RequestParam Color color,
                                          @PathVariable long id) {
        Optional<Car> modColorCar = carService.changeColorCarById(color, id);

        if(modColorCar.isPresent()) {
            return new ResponseEntity<>("Zmodyfikowano kolor pojazdu o id " + modColorCar.get().getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeCar(@PathVariable long id) {
        Optional<Car> delCar = carService.removeCarById(id);

        if (delCar.isPresent()) {
            return new ResponseEntity("Pojazd o id " + delCar.get().getId() + " został usunięty", HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
