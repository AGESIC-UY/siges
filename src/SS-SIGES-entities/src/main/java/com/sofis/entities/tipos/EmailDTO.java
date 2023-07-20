package com.sofis.entities.tipos;

import java.io.Serializable;

public class EmailDTO implements Serializable {

	private String address;
	private Boolean verified;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	@Override
	public String toString() {
		return "EmailDTO{" + "address=" + address + ", verified=" + verified + '}';
	}

}
