package com.example.cars.web;


import com.example.cars.Entity.Car;
import com.example.cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    CarService carService;

    @GetMapping("/")
    public String getHomepage(Model model){
        Car car = new Car();
        model.addAttribute("car", car);
        return "home";
    }

    @PostMapping("/saveCar")
    public String saveCar(@Valid Car car, BindingResult result){
        if (result.hasErrors()){
            return "home";
        }
        car.setFixed(false);
        carService.addCar(car);
        carService.saveToFile();
        return "redirect:/cars";
    }
}
