package com.meli.simian.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dna")
public class Dna {

	@Id
	private String id;

	@Indexed(unique = true)
	private String dna;

	private String[] sequences;
	
	private DnaType type;
	 

	public Dna() {
		super();
		 
	}
 
	public Dna(String id, String dna, String[] sequences, DnaType type) {
		super();
		this.id = id;
		this.dna = dna;
		this.sequences = sequences;
		this.type = type;
	}
 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDna() {
		return dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
	}

	public String[] getSequences() {
		return sequences;
	}

	public void setSequences(String[] sequences) {
		this.sequences = sequences;
	}

	public DnaType getType() {
		return type;
	}

	public void setType(DnaType type) {
		this.type = type;
	}

	
}
