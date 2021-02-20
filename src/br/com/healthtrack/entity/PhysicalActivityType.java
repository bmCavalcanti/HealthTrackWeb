package br.com.healthtrack.entity;

/**
 * Classe de tipo atividade f�sica
 * 
 * @author Bianca Cavalcanti
 * @version 1.0
 *
 */
public class PhysicalActivityType {

	private int id;
	private String name;
	
	/**
	 * Obt�m o Id do tipo atividade f�sica
	 * @return Id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Define o Id do tipo atividade f�sica
	 * @param id Id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Obt�m o Nome do tipo atividade f�sica
	 * @return Nome
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Define o Nome do tipo atividade f�sica
	 * @param name Nome
	 */
	public void setName(String name) {
		this.name = name;
	}
}
