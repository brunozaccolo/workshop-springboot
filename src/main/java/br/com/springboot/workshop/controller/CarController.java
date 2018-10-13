package br.com.springboot.workshop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.workshop.domain.Car;
import br.com.springboot.workshop.service.CarService;

@RestController // sinaliza ao spring-boot que essa classe irá prover serviços
@RequestMapping("/cars") // sinaliza ao spring-boot o caminho correspondente à essa
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@PostMapping("/create") // declara que este método é POST (para criar um carro)
	public ResponseEntity<Car> create(@RequestBody @Valid final Car car) {
		car.setId(null);
		return new ResponseEntity<Car>(this.carService.create(car), HttpStatus.CREATED);
	}

	
	@GetMapping // declara que este método é GET (para buscar carros)
	public ResponseEntity<List<Car>> getAll() {
		return new ResponseEntity<List<Car>>(this.carService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{car_id}") // declara que este método é GET (para buscar um carro) quando passado o id pelo path ex: /cars/5bbe6edff8a7e33394b154b5
	public ResponseEntity<Car> getOne(@PathVariable(name = "car_id", required = true) String id) {
		final Car car = this.carService.findOne(id);
		
		if (car == null) {
			return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Car>(car, HttpStatus.OK);
	}
	
	@PutMapping("/{car_id}") // declara que este método é PUT (alterar um carro) quando passado o id pelo path ex: /cars/5bbe6edff8a7e33394b154b5
	public ResponseEntity<Car> update(@PathVariable(name = "car_id", required = true) String id, @RequestBody final Car car) {
		car.setId(id);
		return new ResponseEntity<Car>(this.carService.update(car), HttpStatus.OK);
	}
	
	@DeleteMapping("/{car_id}") // declara que este método é DELETE (deletar um carro) quando passado o id pelo path ex: /cars/5bbe6edff8a7e33394b154b5
	public ResponseEntity<Void> delete(@PathVariable(name = "car_id", required = true) String id) {
		this.carService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/name/{car_name}") // declara que este método é GET by Name(para buscar carros)
	public ResponseEntity<Car> getByName(@PathVariable(name = "car_name", required = true) String name) {
		return new ResponseEntity<Car>(this.carService.findByName(name), HttpStatus.OK);
	}
	
	@GetMapping("/allname/{car_name}") // declara que este método é GET by Name(para buscar carros)
	public ResponseEntity<List<Car>> getAllByName(@PathVariable(name = "car_name", required = true) String name) {
		return new ResponseEntity<List<Car>>(this.carService.findAllByName(name), HttpStatus.OK);
	}
	


}