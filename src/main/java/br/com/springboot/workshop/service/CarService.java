package br.com.springboot.workshop.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.workshop.domain.Car;
import br.com.springboot.workshop.exception.BusinessException;
import br.com.springboot.workshop.repository.CarRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private CpfRemoteService cpfRemoteService;

	public Car create(final Car car) {
		
		final Car carExists = this.carRepository.findOneByNameIgnoreCase(car.getName());
		
		cpfRemoteService.findCpfByName(car.getName()).ifPresent(cpf -> car.setOwner(cpf.get("cpf")));
		
		if (carExists != null) {
			throw new BusinessException("Carro "+car.getName() + " já existe");
			
		}

		final LocalDateTime now = LocalDateTime.now();

		car.setCreatedAt(now);
		car.setUpdatedAt(now);

		return this.carRepository.insert(car);
	}

	public Car update(final Car car) {

		car.setUpdatedAt(LocalDateTime.now());

		return this.carRepository.save(car);
	}

	public void delete(final String id) {

		final Car car = this.findOne(id);

		car.setUpdatedAt(LocalDateTime.now());

		this.carRepository.delete(car);
	}

	public List<Car> findAll() {
		return this.carRepository.findAll();
	}

	public Car findOne(final String id) {
		return this.carRepository.findOne(id);
	}

	public Car findByName(final String name) {
		return this.carRepository.findOneByNameIgnoreCase(name);
	}
	
	public List<Car> findAllByName(final String name) {
		return this.carRepository.findAllByName(name);
	}

}
