package br.com.healthtrack.entity;

/**
 * Classe de gênero
 * 
 * @author Bianca Cavalcanti
 * @version 1.0
 *
 */
public class Gender {
	
	private int id;
	private String name;
	
	/**
	 * Obtém o Id do gênero
	 * @return Id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Define o Id do gênero
	 * @param id Id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Obtém o Nome do gênero
	 * @return Nome
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Define o Nome do gênero
	 * @param name Nome
	 */
	public void setName(String name) {
		this.name = name;
	}
}
