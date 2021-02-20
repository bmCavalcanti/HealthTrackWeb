package br.com.healthtrack.entity;
import java.util.Calendar;
import java.util.List;

/**
 * Classe de alimento
 * 
 * @author Bianca Cavalcanti
 * @version 1.0
 *
 */
public class Food {

	private int id;
	private Calendar created_at;
	private String description;
	private User user;
	private FoodType foodType;
	private List<FoodItem> food_items;
	
	/**
	 * Obtém o Id do alimento
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
	 * Obtém a Data de consumo do alimento
	 * @return Data de consumo
	 */
	public Calendar getCreatedAt() {
		return created_at;
	}
	
	/**
	 * Define a Data de consumo do alimento
	 * @param created_at Data
	 */
	public void setCreatedAt(Calendar created_at) {
		this.created_at = created_at;
	}
	
	/**
	 * Obtém a descrição do alimento
	 * @return Descrição
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Define a descrição do alimento
	 * @param description Descrição
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Obtém o Usuário do alimento
	 * @return Usuário
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Define o Usuário do alimento
	 * @param user Usuário 
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Obtém o Tipo de alimento do alimento
	 * @return Tipo de alimento 
	 */
	public FoodType getFoodType() {
		return foodType;
	}
	
	/**
	 * Define o Tipo de alimento do alimento
	 * @param foodType Tipo de alimento
	 */
	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}

	public List<FoodItem> getFoodItems() {
		return food_items;
	}

	public void setFoodItems(List<FoodItem> food_items) {
		this.food_items = food_items;
	}
	
	public String getTotalCalories() {
		float value = 0; 
		for (FoodItem food_item : this.food_items) {
			value += food_item.getCalories();
		}
		
		return String.format("%.2f", value);
	}
}
