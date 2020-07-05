package cache.framework.rest;

public class CreateCacheResponse {
	private Integer CacheID;	
	private boolean success;

	public Integer getCacheID() {
		return CacheID;
	}

	public void setCacheID(Integer cacheID) {
		CacheID = cacheID;
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
		result = prime * result + ((CacheID == null) ? 0 : CacheID.hashCode());
		result = prime * result + (success ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CreateCacheResponse)) {
			return false;
		}
		CreateCacheResponse other = (CreateCacheResponse) obj;
		if (CacheID == null) {
			if (other.CacheID != null) {
				return false;
			}
		} else if (!CacheID.equals(other.CacheID)) {
			return false;
		}
		if (success != other.success) {
			return false;
		}
		return true;
	}

	
}