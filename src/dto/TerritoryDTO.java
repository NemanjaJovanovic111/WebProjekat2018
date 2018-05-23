package dto;

public class TerritoryDTO {

	private String name;
	private Integer area;
	private Integer population;

	public TerritoryDTO() {
		super();
	}

	public TerritoryDTO(String name, Integer area, Integer population) {
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

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	@Override
	public String toString(){
		return "TerritoryDTO [name=" + name + ",area=" + area + ",population=" + population  +  "]";
	}
}
