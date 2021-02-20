package br.com.healthtrack.entity;
import java.util.Calendar;

/**
 * Classe de press�o arterial
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
	 * Obt�m o Id da press�o arterial
	 * @return Id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Define o Id da press�o arterial
	 * @param id Id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Obt�m a Data da press�o arterial
	 * @return Data
	 */
	public Calendar getCreatedAt() {
		return created_at;
	}
	
	/**
	 * Define a Data da press�o arterial
	 * @param created_at Data
	 */
	public void setCreatedAt(Calendar created_at) {
		this.created_at = created_at;
	}
	
	/**
	 * Obt�m o Valor da press�o arterial
	 * @return Valor
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Define o Valor da press�o arterial
	 * @param value Valor
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Obt�m o Usu�rio da press�o arterial
	 * @return Usu�rio
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Define o Usu�rio da press�o arterial
	 * @param user Usu�rio
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
				nivel = "Pr�-hipertens�o";
			} else if (sistolica >= 140 && sistolica <= 159 || diastolica >= 90 && diastolica <= 99) {
				nivel = "Hipertens�o est�gio 1";
			} else if (sistolica >= 160 && sistolica <= 179 || diastolica >= 100 && diastolica <= 109) {
				nivel = "Hipertens�o est�gio 2";
			} else if (sistolica >= 180 || diastolica >= 110) {
				nivel = "Crise Hipertensiva";
			}
		}
		
		return nivel;
	}
}
