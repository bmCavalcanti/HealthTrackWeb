package br.com.healthtrack.entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;

/**
 * Classe de usuário
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
	 * Obtém o Id do usuário
	 * @return Id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Define o Id do usuário
	 * @param id Id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtém o Nome do usuário
	 * @return Nome
	 */
	public String getName() {
		return name;
	}

	/**
	 * Define o Nome do usuário
	 * @param name Nome
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obtém o Email do usuário
	 * @return Email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Define o Email do usuário
	 * @param email Email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtém a data de aniversário do usuário
	 * @return Data de aniversário
	 */
	public Calendar getBirthday() {
		return birthday;
	}

	/**
	 * Define a data de aniversário do usuário
	 * @param birthday Data de aniversário
	 */
	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	/**
	 * Obtém a altura do usuário
	 * @return Altura
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * Define a altura do usuário
	 * @param height Altura
	 */
	public void setHeight(float height) {
		this.height = height;
	}
	
	/**
	 * Obtém a senha do usuário
	 * @return Senha
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Define a senha do usuário
	 * @param password Senha
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Obtém o gênero do usuário
	 * @return Gênero
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * Define o gênero do usuário
	 * @param gender Gênero
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * Obtém o caminho da foto do usuário
	 * @return Caminho da foto
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * Define o caminho da foto do usuário
	 * @param photo Caminho da foto
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * Obtém a data de criação do usuário
	 * @return Data de criação
	 */
	public Calendar getCreatedAt() {
		return created_at;
	}

	/**
	 * Define a data de criação do usuário
	 * @param createdAt Data de criação
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
