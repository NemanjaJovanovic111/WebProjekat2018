package model;

import java.util.UUID;

import dto.TerritoryDTO;

public class Territory {
	private String id;
	private String name;
	private int area;
	private int population;
	
	public Territory(TerritoryDTO dto) {
		id = UUID.randomUUID().toString();
		name = dto.getName();
		area = Integer.parseInt(dto.getArea());
		population = Integer.parseInt(dto.getPopulation());
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	@Override
	public String toString() {
		return "Territory [name=" + name + ", area=" + area + ", population=" + population + "]";
	}
	public Territory(String id, String name, int area, int population) {
		super();
		this.id = id;
		this.name = name;
		this.area = area;
		this.population = population;
	}
	
	public Territory () {
		
	}
}
