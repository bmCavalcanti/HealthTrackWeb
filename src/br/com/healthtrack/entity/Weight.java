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
	 * Obtém o Id do peso
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
	 * Obtém a Data do peso
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
	 * Obtém o Valor do peso
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
	 * Obtém o Usuário do peso
	 * @return Usuário
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Define o Usuário do peso
	 * @param user Usuário
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
