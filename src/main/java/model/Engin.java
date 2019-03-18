package model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Inheritance(strategy=InheritanceType.JOINED) 
public abstract class Engin {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="couleur",length = 50, nullable = false)
	private String couleur;
	
	@Column(name="vitesse_max",nullable = false)
	private Integer vitesseMax;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_id")
	private Avatar avatar;
	
	
	public Engin() {
		super();
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

	public Integer getVitesseMax() {
		return vitesseMax;
	}

	public void setVitesseMax(Integer vitesseMax) {
		this.vitesseMax = vitesseMax;
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

		

	@Override
	public String toString() {
		return "Engin [id=" + id + ", couleur=" + couleur + ", vitesse_max=" + vitesseMax + ", avatar="
				+ avatar + "]";
	}
			
}
