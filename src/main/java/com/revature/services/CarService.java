package com.revature.services;

import java.util.List;

import com.revature.models.Car;

public interface CarService {
	
	public List<Car> getCars();
	public Car getCarById(int id);
	public Car getCarByUserId(int userId);
	public Car addCar(Car car);
	public Car updateCar(Car car);
	public String deleteCarById(int id);
}
