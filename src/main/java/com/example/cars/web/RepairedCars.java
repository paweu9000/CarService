package com.example.cars.web;

import com.example.cars.Entity.Car;
import com.example.cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/repaired")
public class RepairedCars {

    @Autowired
    CarService carService;

    @GetMapping
    public String repairedCars(Model model){
        List<Car> cars = carService.getRepairedCars();
        model.addAttribute("cars", cars);
        return "repaired";
    }
}
