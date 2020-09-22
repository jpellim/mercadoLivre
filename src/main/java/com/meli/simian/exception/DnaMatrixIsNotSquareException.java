package com.meli.simian.exception;

public class DnaMatrixIsNotSquareException extends RuntimeException {
	 
	private static final long serialVersionUID = -2601103018844264015L;
 
    public DnaMatrixIsNotSquareException() {
		super();
		
	}
  
	public DnaMatrixIsNotSquareException(final String message) {
        super(message);
    }

    public DnaMatrixIsNotSquareException(final Throwable cause) {
        super(cause);
    }

    public DnaMatrixIsNotSquareException(final String message, final Throwable cause) {
        super(message, cause);
    }

}