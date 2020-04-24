package com.revature.services;

import com.revature.models.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

	List<Car> getCars();

	Optional<Car> getCarById(int id);

	Car getCarByUserId(int userId);

	Car addCar(Car car);

	Car updateCar(Car car);

	String deleteCarById(int id);
}
