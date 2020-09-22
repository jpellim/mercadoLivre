package com.meli.simian.service.impl;

import static com.meli.simian.constants.SimianConstants.A_SIMIAN;
import static com.meli.simian.constants.SimianConstants.C_SIMIAN;
import static com.meli.simian.constants.SimianConstants.G_SIMIAN;
import static com.meli.simian.constants.SimianConstants.T_SIMIAN;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.simian.exception.DnaMatrixIsNotSquareException;
import com.meli.simian.service.SearchRegistrationService;
import com.meli.simian.service.SimianService;

@Service
public class SimianServiceImpl implements SimianService {

	@Autowired
	private SearchRegistrationService searchRegistrationService;
	
	@Override
	public boolean isSimian(String[] dna) {

		verifyMatrixIsSquare(dna);
		
		final boolean isSimian = isSimianExistsHorizontally(dna) || isSimianExistsVertically(dna) || isSimianExistsRightDiagonally(dna) || isSimianExistsLeftDiagonally(dna);
	
		searchRegistrationService.registerSearch(dna, isSimian);
		
		return isSimian;
	}

	private boolean isSimianExistsHorizontally(String[] dna) {
		for (String elem : dna) {
			if (isSequenceOfSimian(elem)) {
				return true;
			}
		}
		return false;
	}

	private boolean isSimianExistsVertically(String[] dna) {
		final String[] arrayVerticalToHorizontal = new String[dna.length];

		for (int i = 0; i < dna.length; i++) {

			char[] charArray = dna[i].toCharArray();

			for (int j = 0; j < charArray.length; j++) {
				arrayVerticalToHorizontal[j] = (arrayVerticalToHorizontal[j] != null ? arrayVerticalToHorizontal[j]
						: "").concat(Character.toString(charArray[j]));
				if (isSequenceOfSimian(arrayVerticalToHorizontal[j])) {
					return true;
				}
			}

		}

		return isSimianExistsHorizontally(arrayVerticalToHorizontal);
	}

	private boolean isSimianExistsRightDiagonally(String[] dna) {

		final Map<ImmutablePair<Integer, Integer>, String> mapa = new HashMap<>();

		for (int i = 0; i < dna.length; i++) {

			distributefirstSequenceInMap(mapa, dna, i);
			
			int anterior = 1;

			for (int j = i + 1; j < dna.length; j++) {

				char[] charArray = dna[j].toCharArray();

				for (int elem = 0; elem < charArray.length; elem++) {

					ImmutablePair<Integer, Integer> pairSearchRight = new ImmutablePair<>(i, (elem - anterior));

					if (isAlreadyFoundSimian(mapa, pairSearchRight, charArray[elem])) {
						return true;
					}			
				}
				anterior++;
			}
		}

		final Optional<String> someDna = mapa.values().stream()
				.filter(s -> s.equals(A_SIMIAN) || s.equals(C_SIMIAN) || s.equals(T_SIMIAN) || s.equals(G_SIMIAN))
				.findAny();

		return someDna.isPresent();
	}

	
	private boolean isSimianExistsLeftDiagonally(String[] dna) {

		final Map<ImmutablePair<Integer, Integer>, String> mapa = new HashMap<>();

		for (int i = 0; i < dna.length; i++) {

			distributefirstSequenceInMap(mapa, dna, i);
		 
			int anterior = 1;

			for (int j = i + 1; j < dna.length; j++) {

				char[] charArray = dna[j].toCharArray();

				for (int elem = 0; elem < charArray.length; elem++) {

					ImmutablePair<Integer, Integer> pairSearchLeft = new ImmutablePair<>(i, (elem + anterior));

					if (isAlreadyFoundSimian(mapa, pairSearchLeft, charArray[elem])) {
						return true;
					}
				}
				anterior++;
			}
		}

		final Optional<String> someDna = mapa.values().stream()
				.filter(s -> s.equals(A_SIMIAN) || s.equals(C_SIMIAN) || s.equals(T_SIMIAN) || s.equals(G_SIMIAN))
				.findAny();

		return someDna.isPresent();
	}
	
	private void distributefirstSequenceInMap(Map<ImmutablePair<Integer, Integer>, String> mapa, String[] dna, int i) {
	 
		char[] charArray = dna[i].toCharArray();

		for (int elem = 0; elem < charArray.length; elem++) {
			ImmutablePair<Integer, Integer> pair = new ImmutablePair<>(i, elem);
			mapa.put(pair, Character.toString(charArray[elem]));
		}
	}

	private boolean isAlreadyFoundSimian(Map<ImmutablePair<Integer, Integer>, String> mapa,
			ImmutablePair<Integer, Integer> pairSearch, char charElement) {

		if (mapa.containsKey(pairSearch)) {
			String diagonally = mapa.get(pairSearch);
			diagonally = diagonally.concat(Character.toString(charElement));
			if (isSequenceOfSimian(diagonally)) {
				return true;
			}
			mapa.put(pairSearch, diagonally);
		}
		return false;
	}


	private boolean isSequenceOfSimian(String elem) {
		return elem.contains(A_SIMIAN) || elem.contains(C_SIMIAN) || elem.contains(T_SIMIAN) || elem.contains(G_SIMIAN);
	}
 
	private void verifyMatrixIsSquare(String[] dna) {
		final int dnaLenght = dna.length;
		for (String sequence : dna) {
			if (sequence.length() != dnaLenght) {
				throw new DnaMatrixIsNotSquareException();
			}
		}
	}

}
