package com.meli.simian.model;

import java.io.Serializable;

public class DnaDto implements Serializable {

	private static final long serialVersionUID = 5375945487167804330L;

	String[] dna;
 
	public DnaDto() {
		super();
		 
	}

	public DnaDto(String[] dna) {
		super();
		this.dna = dna;
	}

	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
	}
	
}
