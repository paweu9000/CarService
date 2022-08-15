package com.example.cars.service;

import com.example.cars.Entity.Car;
import com.example.cars.repository.CarRepository;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    CarRepository carRepository;

    public void addCar(Car car){
        carRepository.addCar(car);
    }

    public List<Car> getCars(){
        return carRepository.getCars();
    }

    public void saveToFile(){
        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/data/cars.json"), getCars());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public Car findById(String id){
        return carRepository.findById(id);
    }

    public List<Car> getRepairedCars(){
        return carRepository.getRepairedCars();
    }

    public void fixCarById(String id){
        Car car = carRepository.findById(id);
        car.setFixed(true);
    }

    public List<Car> getCarByPlates(String plates){
        List<Car> cars = carRepository.getCars().stream()
                        .filter(car -> plates.equals(car.getRegistrationNumber()))
                        .collect(Collectors.toList());
        return cars;
    }

    public void addFixedCarToFile(){
        List<Car> cars = carRepository.getRepairedCars();
        List<Car> allCars = carRepository.getCars();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/data/fixed.json"),
                                                                cars);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/data/cars.json")
                                                                , allCars);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Car> getBrokenCars(){
        return carRepository.getCars().stream().filter(car -> !car.isFixed()).collect(Collectors.toList());
    }

}
