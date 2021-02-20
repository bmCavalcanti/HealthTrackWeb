package br.com.healthtrack.entity;

/**
 * Classe de tipo de alimento
 * 
 * @author Bianca Cavalcanti
 * @version 1.0
 *
 */
public class FoodType {

	private int id;
	private String name;
	
	/**
	 * Obt�m o Id do tipo de alimento
	 * @return Id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Define o Id do tipo de alimento
	 * @param id Id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Obt�m o Nome do tipo de alimento
	 * @return Nome
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Define o Nome do tipo de alimento
	 * @param name Nome
	 */
	public void setName(String name) {
		this.name = name;
	}
}
