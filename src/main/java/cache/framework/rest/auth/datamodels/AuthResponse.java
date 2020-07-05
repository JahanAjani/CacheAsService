package cache.framework.rest.auth.datamodels;

public class AuthResponse {
	private boolean success;
	private String authToken;
	
	
	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authToken == null) ? 0 : authToken.hashCode());
		result = prime * result + (success ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthResponse other = (AuthResponse) obj;
		if (authToken == null) {
			if (other.authToken != null)
				return false;
		} else if (!authToken.equals(other.authToken))
			return false;
		if (success != other.success)
			return false;
		return true;
	}
}
