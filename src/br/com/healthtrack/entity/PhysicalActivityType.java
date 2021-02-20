package br.com.healthtrack.entity;

/**
 * Classe de tipo atividade física
 * 
 * @author Bianca Cavalcanti
 * @version 1.0
 *
 */
public class PhysicalActivityType {

	private int id;
	private String name;
	
	/**
	 * Obtém o Id do tipo atividade física
	 * @return Id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Define o Id do tipo atividade física
	 * @param id Id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Obtém o Nome do tipo atividade física
	 * @return Nome
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Define o Nome do tipo atividade física
	 * @param name Nome
	 */
	public void setName(String name) {
		this.name = name;
	}
}
