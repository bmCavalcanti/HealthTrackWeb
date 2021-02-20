package br.com.healthtrack.entity;
import java.util.Calendar;

/**
 * Classe de pressão arterial
 * 
 * @author Bianca Cavalcanti
 * @version 1.0
 *
 */
public class BloodPressure {

	private int id;
	private Calendar created_at;
	private String value;
	private User user;
	
	/**
	 * Obtém o Id da pressão arterial
	 * @return Id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Define o Id da pressão arterial
	 * @param id Id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Obtém a Data da pressão arterial
	 * @return Data
	 */
	public Calendar getCreatedAt() {
		return created_at;
	}
	
	/**
	 * Define a Data da pressão arterial
	 * @param created_at Data
	 */
	public void setCreatedAt(Calendar created_at) {
		this.created_at = created_at;
	}
	
	/**
	 * Obtém o Valor da pressão arterial
	 * @return Valor
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Define o Valor da pressão arterial
	 * @param value Valor
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Obtém o Usuário da pressão arterial
	 * @return Usuário
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Define o Usuário da pressão arterial
	 * @param user Usuário
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getNivel() {
		String nivel = "";
		
		if (this.getValue() != null) {
			String[] values = this.getValue().split("/");
			
			int sistolica = Integer.parseInt(values[0]);
			int diastolica = Integer.parseInt(values[1]);
			
			if (sistolica < 120 && diastolica < 80) {
				nivel = "Normal";
			} else if (sistolica >= 120 && sistolica <= 139 || diastolica >= 80 && diastolica <= 89) {
				nivel = "Pré-hipertensão";
			} else if (sistolica >= 140 && sistolica <= 159 || diastolica >= 90 && diastolica <= 99) {
				nivel = "Hipertensão estágio 1";
			} else if (sistolica >= 160 && sistolica <= 179 || diastolica >= 100 && diastolica <= 109) {
				nivel = "Hipertensão estágio 2";
			} else if (sistolica >= 180 || diastolica >= 110) {
				nivel = "Crise Hipertensiva";
			}
		}
		
		return nivel;
	}
}
