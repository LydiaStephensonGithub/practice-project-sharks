package com.example.sharks.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Shark {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true, nullable = false)
	private String species;
	
	@Column(unique = true)
	private String latinName;
	
	@Column
	private String habitat;
	
	@Column(nullable = false)
	private String conservationStatus;
	
	//default constructor
	public Shark() {}

	//constructor without id
	public Shark(String species, String latinName, String habitat, String conservationStatus) {
		super();
		this.species = species;
		this.latinName = latinName;
		this.habitat = habitat;
		this.conservationStatus = conservationStatus;
	}

	//constructor with id
	public Shark(long id, String species, String latinName, String habitat, String conservationStatus) {
		this.id = id;
		this.species = species;
		this.latinName = latinName;
		this.habitat = habitat;
		this.conservationStatus = conservationStatus;
	}

	//getters and setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getLatinName() {
		return latinName;
	}

	public void setLatinName(String latinName) {
		this.latinName = latinName;
	}

	public String getHabitat() {
		return habitat;
	}

	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}

	public String getConservationStatus() {
		return conservationStatus;
	}

	public void setConservationStatus(String conservationStatus) {
		this.conservationStatus = conservationStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(conservationStatus, habitat, id, latinName, species);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shark other = (Shark) obj;
		return Objects.equals(conservationStatus, other.conservationStatus) && Objects.equals(habitat, other.habitat)
				&& id == other.id && Objects.equals(latinName, other.latinName)
				&& Objects.equals(species, other.species);
	}

	@Override
	public String toString() {
		return "Shark [id=" + id + ", species=" + species + ", latinName=" + latinName + ", habitat=" + habitat
				+ ", conservationStatus=" + conservationStatus + "]";
	}
}
