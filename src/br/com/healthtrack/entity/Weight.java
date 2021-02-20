package br.com.healthtrack.entity;

import java.util.Calendar;

/**
 * Classe de peso
 * 
 * @author Bianca Cavalcanti
 * @version 1.0
 *
 */
public class Weight {

	private int id;
	private Calendar created_at;
	private float value;
	private User user;
	
	/**
	 * Obt�m o Id do peso
	 * @return Id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Define o Id do peso
	 * @param id Id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Obt�m a Data do peso
	 * @return Data
	 */
	public Calendar getCreatedAt() {
		return created_at;
	}
	
	/**
	 * Define a Data do peso
	 * @param date Data
	 */
	public void setCreatedAt(Calendar created_at) {
		this.created_at = created_at;
	}
	
	/**
	 * Obt�m o Valor do peso
	 * @return Valor
	 */
	public float getValue() {
		return value;
	}
	
	/**
	 * Define o Valor do peso
	 * @param value Valor
	 */
	public void setValue(float value) {
		this.value = value;
	}
	
	/**
	 * Obt�m o Usu�rio do peso
	 * @return Usu�rio
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Define o Usu�rio do peso
	 * @param user Usu�rio
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
