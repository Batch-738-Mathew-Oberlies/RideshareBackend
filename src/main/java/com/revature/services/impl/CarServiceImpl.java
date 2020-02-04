package com.revature.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Car;
import com.revature.repositories.CarRepository;
import com.revature.services.CarService;

/**
 * CarServiceImpl handles any additional services that need to be made before calling the
 * repository methods.
 * 
 * @author Adonis Cabreja
 *
 */

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	private CarRepository cr;
	
	/**
	 * Calls CarRepository's findAll method found in the JpaRepository.
	 * 
	 * @return A list of all the cars.
	 */
	
	@Override
	public List<Car> getCars() {
		return cr.findAll();
	}

	/**
	 * Calls CarRepository's getOne method found in the JpaRepository.
	 * 
	 * @param id represents the car's id.
	 * @return A car that matches the car's id.
	 */
	
	@Override
	public Car getCarById(int id) {
		return cr.findById(id).get();
	}

	/**
	 * Calls CarRepository's custom query method getCarByUserId.
	 * 
	 * @param userId represents the user's id.
	 * @return A car that matches the user's id.
	 */
	
	@Override
	public Car getCarByUserId(int userId) {
		return cr.getCarByUserId(userId);
	}
	
	/**
	 * Calls CarRepository's save method found in the JpaRepository.
	 * 
	 * @param car represents the new Car object being sent.
	 * @return The newly created object.
	 */
	
	@Override
	public Car addCar(Car car) {
		return cr.save(car);
	}

	/**
	 * Calls CarRepository's save method found in the JpaRepository.
	 * 
	 * @param car represents the updated Car object being sent.
	 * @return The newly updated object.
	 */
	
	@Override
	public Car updateCar(Car car) {
		return cr.save(car);
	}

	/**
	 * Calls CarRepository's deleteById method found in the JpaRepository.
	 * 
	 * @param id represents the car's id.
	 * @return A string that says which car was deleted.
	 */
	
	@Override
	public String deleteCarById(int id) {
		cr.deleteById(id);
		return "Car with id: " + id + " was deleted.";
	}

}
