package com.meli.simian.service.impl;

import static com.meli.simian.model.DnaType.HUMAN;
import static com.meli.simian.model.DnaType.SIMIAN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.simian.model.Dna;
import com.meli.simian.repository.DnaRepository;
import com.meli.simian.service.SearchRegistrationService;

@Service
public class SearchRegistrationServiceImpl implements SearchRegistrationService {

	@Autowired
	private DnaRepository repository;

	@Override
	public void registerSearch(String[] dna, boolean isSimian) {

		final String dnaComplete = String.join("", dna);

		Dna entity = new Dna();
		entity.setSequences(dna);
		entity.setDna(dnaComplete);

		if (isSimian == true) {
			entity.setType(SIMIAN);
		} else {
			entity.setType(HUMAN);
		}

		repository.save(entity);
	}

}
