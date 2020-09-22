package com.meli.simian.model;

import java.math.BigDecimal;

public class DnaStatistics {

	private Integer totalOfSimians;
	
	private Integer totalOfHumans;
	
	private BigDecimal ratio;
	
	

	public DnaStatistics() {
		super();
		
	}

	public DnaStatistics(Integer totalOfSimians, Integer totalOfHumans, BigDecimal ratio) {
		super();
		this.totalOfSimians = totalOfSimians;
		this.totalOfHumans = totalOfHumans;
		this.ratio = ratio;
	}

	public Integer getTotalOfSimians() {
		return totalOfSimians;
	}

	public void setTotalOfSimians(Integer totalOfSimians) {
		this.totalOfSimians = totalOfSimians;
	}

	public Integer getTotalOfHumans() {
		return totalOfHumans;
	}

	public void setTotalOfHumans(Integer totalOfHumans) {
		this.totalOfHumans = totalOfHumans;
	}

	public BigDecimal getRatio() {
		return ratio;
	}

	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}

	
}
