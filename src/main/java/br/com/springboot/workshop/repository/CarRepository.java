package br.com.springboot.workshop.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.springboot.workshop.domain.Car;

public interface CarRepository extends MongoRepository<Car, String>{
	
	public Car findOneByNameIgnoreCase(String name);
	//public Car findOneByNameIgnoreCaseAndByBrand(String name, String brand)
	
	public List<Car> findAllByName(String name);
	
	//public List<Car> findBy
	//public Car find


}
