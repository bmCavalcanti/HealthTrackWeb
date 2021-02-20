package br.com.healthtrack.entity;
import java.util.Calendar;

/**
 * Classe de atividade física
 * 
 * @author Bianca Cavalcanti
 * @version 1.0
 *
 */
public class PhysicalActivity {

	private int id;
	private float calories;
	private Calendar created_at;
	private String description;
	private String duration;
	private User user;
	private PhysicalActivityType physicalActivityType;
	
	/**
	 * Obtém o Id da atividade física
	 * @return Id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Define o Id da atividade física
	 * @param id Id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Obtém as Calorias da atividade física
	 * @return Calorias
	 */
	public float getCalories() {
		return calories;
	}
	
	/**
	 * Define as Calorias da atividade física
	 * @param calories Calorias
	 */
	public void setCalories(float calories) {
		this.calories = calories;
	}
	
	/**
	 * Obtém a Data da atividade física
	 * @return Data
	 */
	public Calendar getCreatedAt() {
		return created_at;
	}
	
	/**
	 * Define a Data da atividade física
	 * @param created_at Data
	 */
	public void setCreatedAt(Calendar created_at) {
		this.created_at = created_at;
	}
	
	/**
	 * Obtém a Descrição da atividade física
	 * @return Descrição
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Define a Descrição da atividade física
	 * @param description Descrição 
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Obtém a Duração da atividade física
	 * @return Duração
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * Define a Duração da atividade física
	 * @param duration Duração
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	/**
	 * Obtém o Usuário da atividade física
	 * @return Usuário 
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Define o Usuário da atividade física
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Obtém o Tipo de atividade física da atividade física
	 * @return Tipo de atividade física
	 */
	public PhysicalActivityType getPhysicalActivityType() {
		return physicalActivityType;
	}
	
	/**
	 * Define o Tipo de atividade física da atividade física
	 * @param physicalActivityType Tipo de atividade física
	 */
	public void setPhysicalActivityType(PhysicalActivityType physicalActivityType) {
		this.physicalActivityType = physicalActivityType;
	}
}
