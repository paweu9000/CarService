package com.example.cars.web;

import com.example.cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping
    public String cars(Model model){
        model.addAttribute("cars", carService.getBrokenCars());
        return "cars";
    }

    @GetMapping("/fix")
    public String fixCar(@RequestParam String id){
        carService.fixCarById(id);
        carService.addFixedCarToFile();
        return "redirect:/cars";
    }
}
