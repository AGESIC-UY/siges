/**
 * 
 */
package uy.gub.agesic.pge.beans;

/**
 * @author Guzman Llambias
 *
 */
public class StoreBean {
	
	String alias;
	String storeFilePath;
	String storePwd;
	
	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}
	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	/**
	 * @return the storeFilePath
	 */
	public String getStoreFilePath() {
		return storeFilePath;
	}
	/**
	 * @param storeFilePath the storeFilePath to set
	 */
	public void setStoreFilePath(String storeFilePath) {
		this.storeFilePath = storeFilePath;
	}
	/**
	 * @return the storePwd
	 */
	public String getStorePwd() {
		return storePwd;
	}
	/**
	 * @param storePwd the storePwd to set
	 */
	public void setStorePwd(String storePwd) {
		this.storePwd = storePwd;
	}

}
