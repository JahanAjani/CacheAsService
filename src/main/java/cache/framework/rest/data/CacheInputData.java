package cache.framework.rest.data;

public class CacheInputData {
	private Integer custID;
	private Integer cacheID;
	private String key;
	private String value;
	private String token;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getCustID() {
		return custID;
	}
	public void setCustID(Integer custID) {
		this.custID = custID;
	}
	public Integer getCacheID() {
		return cacheID;
	}
	public void setCacheID(Integer cacheID) {
		this.cacheID = cacheID;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
