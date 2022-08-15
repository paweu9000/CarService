package com.example.cars.repository;

import com.example.cars.Entity.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CarRepository {

    private List<Car> cars = new ArrayList<>();


    public List<Car> getCars() {
        return cars;
    }

    public void addCar(Car car){
        cars.add(car);
    }

    public Car findById(String id){
        return cars.stream().filter(car -> id.equals(car.getId())).findFirst().get();
    }

    public List<Car> getRepairedCars(){
        List<Car> repairedCars = cars.stream().filter(car -> car.isFixed() == true).collect(Collectors.toList());
        return repairedCars;
    }
}
