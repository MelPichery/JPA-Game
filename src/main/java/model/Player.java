package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="player")
public class Player {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name",length = 50, nullable = false, unique = true)
	private String name;
	
	@Column(name="mail",length = 50, nullable = false, unique = true)
	private String mail;
	
	@Column(name="pseudo",length = 50, nullable = false, unique = true)
	private String pseudo;
	
	@Column (name="date_naissance")
	private LocalDate dateNaissance;
	
	
	public Player() {
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}


	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", mail=" + mail + ", pseudo=" + pseudo + ", dateNaissance="
				+ dateNaissance + "]";
	}

	
}
