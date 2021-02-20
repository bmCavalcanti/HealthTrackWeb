package br.com.healthtrack.entity;

/**
 * Classe de itens da refei��o
 * 
 * @author Bianca Cavalcanti
 * @version 1.0
 *
 */
public class FoodItem {

	private int id;
	private float calories;
	private String name;
	private Food food;
	
	/**
	 * Obt�m o Id do alimento
	 * @return Id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Define o Id do alimento
	 * @param id Id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Define as calorias do alimento
	 * @return calories Calorias
	 */
	public float getCalories() {
		return calories;
	}

	/**
	 * Obt�m os calorias do alimento
	 * @return Calorias
	 */
	public void setCalories(float calories) {
		this.calories = calories;
	}
	
	/**
	 * Obt�m o nome do alimento
	 * @return Nome
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Define o nome do alimento
	 * @param name Nome
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Obt�m a Refei��o do alimento
	 * @return Refei��o
	 */
	public Food getFood() {
		return food;
	}
	
	/**
	 * Define a Refei��o do alimento
	 * @param food Refei��o 
	 */
	public void setFood(Food food) {
		this.food = food;
	}
}
