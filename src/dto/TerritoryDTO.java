package dto;

public class TerritoryDTO {

	private String name;
	private String area;
	private String population;

	public TerritoryDTO(String name, String area, String population) {
		super();
		this.name = name;
		this.area = area;
		this.population = population;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	@Override
	public String toString(){
		return "TerritoryDTO [name=" + name + ",area=" + area + ",population=" + population  +  "]";
	}
}
