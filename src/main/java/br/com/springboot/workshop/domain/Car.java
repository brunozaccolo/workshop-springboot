package br.com.springboot.workshop.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import br.com.springboot.workshop.config.LocalDateTimeParseDeserializer;
import br.com.springboot.workshop.enumerated.BrandType;
import br.com.springboot.workshop.enumerated.CarType;

public class Car implements Serializable{ 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@NotEmpty
	private String name;
	@NotNull
	private BrandType brand;
	@NotNull
	private Integer year;
	@NotNull
	private CarType type;
	@NotNull
	private Double pricePerDay;
	
	private String owner;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = LocalDateTimeParseDeserializer.class)
	private LocalDateTime createdAt;

	@JsonSerialize(using = ToStringSerializer.class)
	@JsonDeserialize(using = LocalDateTimeParseDeserializer.class)
	private LocalDateTime updatedAt;

	public Car() {
		
	}

	public BrandType getBrand() {
		return brand;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getOwner() {
		return owner;
	}

	public Double getPricePerDay() {
		return pricePerDay;
	}

	public CarType getType() {
		return type;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public Integer getYear() {
		return year;
	}

	public void setBrand(BrandType brand) {
		this.brand = brand;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public void setType(CarType type) {
		this.type = type;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setYear(Integer year) {
		this.year = year;
	}


}
