package br.com.healthtrack.entity;
import java.util.Calendar;

/**
 * Classe de atividade f�sica
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
	 * Obt�m o Id da atividade f�sica
	 * @return Id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Define o Id da atividade f�sica
	 * @param id Id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Obt�m as Calorias da atividade f�sica
	 * @return Calorias
	 */
	public float getCalories() {
		return calories;
	}
	
	/**
	 * Define as Calorias da atividade f�sica
	 * @param calories Calorias
	 */
	public void setCalories(float calories) {
		this.calories = calories;
	}
	
	/**
	 * Obt�m a Data da atividade f�sica
	 * @return Data
	 */
	public Calendar getCreatedAt() {
		return created_at;
	}
	
	/**
	 * Define a Data da atividade f�sica
	 * @param created_at Data
	 */
	public void setCreatedAt(Calendar created_at) {
		this.created_at = created_at;
	}
	
	/**
	 * Obt�m a Descri��o da atividade f�sica
	 * @return Descri��o
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Define a Descri��o da atividade f�sica
	 * @param description Descri��o 
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Obt�m a Dura��o da atividade f�sica
	 * @return Dura��o
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * Define a Dura��o da atividade f�sica
	 * @param duration Dura��o
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	/**
	 * Obt�m o Usu�rio da atividade f�sica
	 * @return Usu�rio 
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Define o Usu�rio da atividade f�sica
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Obt�m o Tipo de atividade f�sica da atividade f�sica
	 * @return Tipo de atividade f�sica
	 */
	public PhysicalActivityType getPhysicalActivityType() {
		return physicalActivityType;
	}
	
	/**
	 * Define o Tipo de atividade f�sica da atividade f�sica
	 * @param physicalActivityType Tipo de atividade f�sica
	 */
	public void setPhysicalActivityType(PhysicalActivityType physicalActivityType) {
		this.physicalActivityType = physicalActivityType;
	}
}
