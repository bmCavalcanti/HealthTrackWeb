package br.com.healthtrack.entity;

/**
 * Classe de g�nero
 * 
 * @author Bianca Cavalcanti
 * @version 1.0
 *
 */
public class Gender {
	
	private int id;
	private String name;
	
	/**
	 * Obt�m o Id do g�nero
	 * @return Id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Define o Id do g�nero
	 * @param id Id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Obt�m o Nome do g�nero
	 * @return Nome
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Define o Nome do g�nero
	 * @param name Nome
	 */
	public void setName(String name) {
		this.name = name;
	}
}
