package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
public class Bolide extends Engin {
	
	
	@Column(name="nb_roues")
	private Integer nbRoues;
	
	@Column(name="autonomie_heure")
	private Integer autonomieHeure;


	public Bolide() {
		super();
	}

	public Integer getNbRoues() {
		return nbRoues;
	}

	public void setNb_roues(Integer nbRoues) {
		this.nbRoues = nbRoues;
	}

	public Integer getAutonomieHeure() {
		return autonomieHeure;
	}

	public void setAutonomieHeure(Integer autonomieHeure) {
		this.autonomieHeure = autonomieHeure;
	}

	@Override
	public String toString() {
		return "Bolide [nb roues=" + nbRoues + ", autonomie heure=" + autonomieHeure + "]";
	}
		
}
