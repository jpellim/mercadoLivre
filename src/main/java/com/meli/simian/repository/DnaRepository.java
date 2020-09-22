package com.meli.simian.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.meli.simian.model.Dna;
import com.meli.simian.model.DnaType;

public interface DnaRepository extends CrudRepository<Dna, String> {
	
	@Query(value = "{ 'dna.type': ?0  }", count = true)
	public Integer countDnaByType(DnaType type);

}
