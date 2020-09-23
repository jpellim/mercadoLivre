package com.meli.simian.service.impl;

import static com.meli.simian.model.DnaType.HUMAN;
import static com.meli.simian.model.DnaType.SIMIAN;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.simian.model.Dna;
import com.meli.simian.model.DnaStatistics;
import com.meli.simian.repository.DnaRepository;
import com.meli.simian.service.StatisticService;

@Service
public class StatisticServiceImpl implements StatisticService {

	private final static Integer CASAS_PRECISAO = 1;
	
	@Autowired
	private DnaRepository repository;

	@Override
	public DnaStatistics calculateStatistics() {

		DnaStatistics statistics = new DnaStatistics();

		Iterable<Dna> dna = repository.findAll();

		final Integer humansDna = (int) getStream(dna).filter(d -> d.getType() == HUMAN).count();

		final Integer simiansDna = (int) getStream(dna).filter(d -> d.getType() == SIMIAN).count();

		BigDecimal ratio = new BigDecimal(simiansDna).divide(new BigDecimal((humansDna == 0 ? 1 :humansDna)), CASAS_PRECISAO, RoundingMode.HALF_UP);

		statistics.setTotalOfSimians(simiansDna);
		statistics.setTotalOfHumans(humansDna);
		statistics.setRatio(ratio);

		return statistics;
	}

	private static Stream<Dna> getStream(Iterable<Dna> dna) {
		return StreamSupport.stream(dna.spliterator(), false);
	}
}
