package com.example.cars.Entity;

import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.UUID;


@Getter
@Setter
public class Car {

    @NonNull
    @NotBlank(message = "Please put valid data")
    @NotEmpty
    private String registrationNumber;

    @NonNull
    @NotBlank(message = "Please put valid data")
    @NotEmpty
    private String name;

    private boolean fixed;

    @NonNull
    @NotBlank(message = "Please put valid data")
    @NotEmpty
    private String color;

    @Min(value = 1960, message = "The year must be between 1960-2022")
    @Max(value = 2022, message = "The year must be between 1960-2022")
    private int production;

    private String id;

    public Car(){
        this.fixed = false;
        this.id = UUID.randomUUID().toString();
    }

    public Car(String registrationNumber, String name, String color, int production){
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.color = color;
        this.fixed = false;
        this.production = production;
        this.id = UUID.randomUUID().toString();
    }
}
