package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="engin")
public class Engin {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="couleur",length = 50, nullable = false)
	private String couleur;
	
	@Column(name="vitesse_max",nullable = false)
	private Integer vitesse_max;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personnage_id")
	private Personnage personnage;

	public Engin() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public Integer getVitesse_max() {
		return vitesse_max;
	}

	public void setVitesse_max(Integer vitesse_max) {
		this.vitesse_max = vitesse_max;
	}

	public Personnage getPersonnage() {
		return personnage;
	}

	public void setPersonnage(Personnage personnage) {
		this.personnage = personnage;
	}

	@Override
	public String toString() {
		return "Engin [id=" + id + ", couleur=" + couleur + ", vitesse_max=" + vitesse_max + ", personnage="
				+ personnage + "]";
	}
			
}
