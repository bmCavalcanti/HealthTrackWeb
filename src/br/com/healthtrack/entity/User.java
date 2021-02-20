package br.com.healthtrack.entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;

/**
 * Classe de usu�rio
 * 
 * @author Bianca Cavalcanti
 * @version 1.0
 *
 */
public class User {
	
	private int id;
	private String name;
	private String email;
	private Calendar birthday;
	private float height;
	private String password;
	private Gender gender;
	private String photo;
	private Calendar created_at;
		
	/**
	 * Obt�m o Id do usu�rio
	 * @return Id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Define o Id do usu�rio
	 * @param id Id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obt�m o Nome do usu�rio
	 * @return Nome
	 */
	public String getName() {
		return name;
	}

	/**
	 * Define o Nome do usu�rio
	 * @param name Nome
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obt�m o Email do usu�rio
	 * @return Email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Define o Email do usu�rio
	 * @param email Email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obt�m a data de anivers�rio do usu�rio
	 * @return Data de anivers�rio
	 */
	public Calendar getBirthday() {
		return birthday;
	}

	/**
	 * Define a data de anivers�rio do usu�rio
	 * @param birthday Data de anivers�rio
	 */
	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	/**
	 * Obt�m a altura do usu�rio
	 * @return Altura
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * Define a altura do usu�rio
	 * @param height Altura
	 */
	public void setHeight(float height) {
		this.height = height;
	}
	
	/**
	 * Obt�m a senha do usu�rio
	 * @return Senha
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Define a senha do usu�rio
	 * @param password Senha
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Obt�m o g�nero do usu�rio
	 * @return G�nero
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * Define o g�nero do usu�rio
	 * @param gender G�nero
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * Obt�m o caminho da foto do usu�rio
	 * @return Caminho da foto
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * Define o caminho da foto do usu�rio
	 * @param photo Caminho da foto
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * Obt�m a data de cria��o do usu�rio
	 * @return Data de cria��o
	 */
	public Calendar getCreatedAt() {
		return created_at;
	}

	/**
	 * Define a data de cria��o do usu�rio
	 * @param createdAt Data de cria��o
	 */
	public void setCreatedAt(Calendar created_at) {
		this.created_at = created_at;
	}
	
	public int getAge() {
		Calendar calendar = Calendar.getInstance();
		
		LocalDate ldbirthday = LocalDateTime.ofInstant(this.getBirthday().toInstant(), this.getBirthday().getTimeZone().toZoneId()).toLocalDate();
		LocalDate ldnow = LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalDate();
		return Period.between(ldbirthday, ldnow).getYears();
	}
}
