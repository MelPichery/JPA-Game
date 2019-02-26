package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class Aeronef extends Engin {
	
		
	@Column(name="type")
	private String type;
	
	@Column(name="autonomie_km")
	private Integer autonomieKm;

	
	public Aeronef() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getAutonomieKm() {
		return autonomieKm;
	}

	public void setAutonomieKm(Integer autonomieKm) {
		this.autonomieKm = autonomieKm;
	}

	@Override
	public String toString() {
		return "Aeronef [type=" + type + ", autonomieKm=" + autonomieKm + "]";
	}
	
	

}
