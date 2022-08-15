package com.example.cars.web;

import com.example.cars.Entity.Car;
import com.example.cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    CarService carService;

    @GetMapping
    public String getSearch(Model model){
        model.addAttribute("car", new Car());
        return "search";
    }

    @PostMapping("/query")
    public String getCars(@ModelAttribute Car car, Model model){
        List<Car> cars = carService.getCarByPlates(car.getRegistrationNumber());
        model.addAttribute("cars", cars);
        return "search";
    }


}
