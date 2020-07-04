package cache.framework.exceptions;

public class CacheException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public CacheException(String msg) {
		message = msg;
	}
	
	@Override
	public String toString() {
		return "CacheExpection: "+ message;
	}
}
