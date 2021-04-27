package it.objectmethod.db.models;

public class Country {

	private String code;
	private String name;
	private String continent;
	private Integer population;
	private Float surfaceArea;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public Integer getPopulation() {
		return population;
	}
	public void setPopulation(Integer population) {
		this.population = population;
	}
	public Float getSurfaceArea() {
		return surfaceArea;
	}
	public void setSurfaceArea(Float surfaceArea) {
		this.surfaceArea = surfaceArea;
	}
}
