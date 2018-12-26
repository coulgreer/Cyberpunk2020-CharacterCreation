package rpg.general.commerce;

import rpg.Describable;

public abstract class Product implements Buyable, Weighable, Describable {
	private String name;
	private String description;
	private double cost;
	private double weight;

	public Product(String name, String description, double cost, double weight) {
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getCost() {
		return cost;
	}

	public double getWeight() {
		return weight;
	}
}
