package com.example.cars;

import com.example.cars.Entity.Car;
import com.example.cars.repository.CarRepository;
import com.example.cars.service.CarService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class CarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarsApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CarService carService, CarRepository carRepository){
		return args -> {
			ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
			TypeReference<List<Car>> reference = new TypeReference<List<Car>>(){};
			InputStream is = TypeReference.class.getResourceAsStream("/data/cars.json");
			try{
				List<Car> cars = mapper.readValue(is, reference);
				cars.forEach(car -> carService.addCar(car));
			} catch (IOException e){
				System.out.println(e.getMessage());
			}
		};
	}

}
