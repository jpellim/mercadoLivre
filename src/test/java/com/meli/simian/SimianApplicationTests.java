package com.meli.simian;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.meli.simian.exception.DnaMatrixIsNotSquareException;
import com.meli.simian.service.SearchRegistrationService;
import com.meli.simian.service.impl.SimianServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest 
public class SimianApplicationTests {

	@Test
	public void contextLoads() {
	}

	@InjectMocks
	private final SimianServiceImpl simianService = new SimianServiceImpl();
	
	@Mock
	private SearchRegistrationService searchRegistrationService;
 
	@Test
	public void testDnaIsNotRelatedToSimius() {

		String[] dna = { "ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "CGCTCA", "TCACTG" };

		final boolean isSimian = simianService.isSimian(dna);
	 
		Assert.assertFalse(isSimian);
 
	}
	
	
	@Test
	public void testDnaIsRelatedToSimiusInHorizontally() {

		String[] dna = { "CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "CCCCTA", "TCACTG" };

		final boolean isSimian = simianService.isSimian(dna);
	 
		Assert.assertTrue(isSimian);
 
	}
 
	@Test
	public void testDnaIsRelatedToSimiusInHorizontallyStartingAtSecondPosition() {

		String[] dna = { "CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "TCCCCA", "TCACTG" };

		final boolean isSimian = simianService.isSimian(dna);
	 
		Assert.assertTrue(isSimian);
 
	}
 	
	@Test
	public void testDnaIsRelatedToSimiusInHorizontallyEndingAtLastPosition() {

		String[] dna = { "CTGAGA", "CTGAGC", "TATTGT", "AGAGGG", "TACCCC", "TCACTG" };

		final boolean isSimian = simianService.isSimian(dna);
	 
		Assert.assertTrue(isSimian);
 
	}
 
	@Test
	public void testDnaIsRelatedToSimiusInVertically() {

		String[] dna = { "CTGAGA", "CTGAGC", "ATTTGT", "ATAGCG", "TACCAC", "TCACTG" };

		final boolean isSimian = simianService.isSimian(dna);
	 
		Assert.assertTrue(isSimian);
 
	}

	@Test
	public void testDnaIsRelatedToSimiusInVerticallyStartingAtSecondPosition() {

		String[] dna = { "CCGAGA", "CTGAGC", "ATTTGT", "ATAGCG", "TTCCAC", "TCACTG" };

		final boolean isSimian = simianService.isSimian(dna);
	 
		Assert.assertTrue(isSimian);
 
	}
	
	@Test
	public void testDnaIsRelatedToSimiusInVerticallyEndingAtLastPosition() {

		String[] dna = { "CCGAGA", "CTGAGC", "ATAATT", "ATAGTG", "TTCCTC", "TCACTG" };

		final boolean isSimian = simianService.isSimian(dna);
	 
		Assert.assertTrue(isSimian);
 
	}
	
	
	@Test
	public void testDnaIsRelatedToSimiusInRightDiagonally() {

		String[] dna = { "TCGAGA", "CTGAGC", "ATTATT", "ATATTG", "TGCCTC", "TCACAG" };

		final boolean isSimian = simianService.isSimian(dna);
	 
		Assert.assertTrue(isSimian);
 
	}
	
	
	@Test
	public void testDnaIsRelatedToSimiusInRightDiagonallyStartingAtSecondPosition() {

		String[] dna = { "GCGAGA", "CTGAGC", "ATTATT", "ATATTG", "TGCCTC", "TCACAG" };

		final boolean isSimian = simianService.isSimian(dna);
	 
		Assert.assertTrue(isSimian);
 
	}
	
	@Test
	public void testDnaIsRelatedToSimiusInRightDiagonallyEndingLastPosition() {

		String[] dna = { "GCGAGA", "CAGAGC", "AGTATT", "ATATTG", "TGCCTC", "TCACAT" };

		final boolean isSimian = simianService.isSimian(dna);
	 
		Assert.assertTrue(isSimian);
 
	}
	
	@Test
	public void testDnaIsRelatedToSimiusInLeftDiagonally() {

		String[] dna = { "TCACAG", "GCGAGG", "CAAAGC", "AATGTT", "ATGGTG", "TGCCTC" };

		final boolean isSimian = simianService.isSimian(dna);
	 
		Assert.assertTrue(isSimian);
 
	}
  
	@Test
	public void testDnaIsRelatedToSimiusInLeftDiagonallyStartingAtTopOfMatrix() {

		String[] dna = { "GCGAGG", "CAAAGC", "AATGTT", "ATGGTG", "TGCCTC", "TCACAG" };

		final boolean isSimian = simianService.isSimian(dna);
	 
		Assert.assertTrue(isSimian);
 
	}
	
    @Test(expected = DnaMatrixIsNotSquareException.class)
    public void testDnaMatrixIsNotSquare() {
    	
    	String[] dna = { "GCGAGG", "CAAAGC", "AATGTT", "ATGGTG", "TGCCTC", "TCACAGA" };

		simianService.isSimian(dna);
    	
    }
    
    @Test(expected = DnaMatrixIsNotSquareException.class)
    public void testDnaMatrixIsNotSquareBecauseHasMoreLinesThanColumns() {
    	
    	String[] dna = { "GCGAGG", "CAAAGC", "AATGTT", "ATGGTG", "TGCCTC", "TCACAG", "TTTTAG" };

		simianService.isSimian(dna);
    	
    }

    @Test(expected = DnaMatrixIsNotSquareException.class)
    public void testDnaMatrixIsNotSquareBecauseHasMoreColumnsThanLines() {
    	
    	String[] dna = { "GCGAG", "GCAGC", "GACTT", "GTGCG" };

		simianService.isSimian(dna);
    	
    }
     
	
}

