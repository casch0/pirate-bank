package com.blued.models;

public class Pirate {
	
	private int pirate_number;
	private String pirate_name;
	private String secret_code;
	
	public Pirate(int pirate_number, String pirate_name, String secret_code) {
		super();
		this.pirate_number = pirate_number;
		this.pirate_name = pirate_name;
		this.secret_code = secret_code;
	}

	@Override
	public String toString() {
		return "pirates [pirate_number=" + pirate_number + ", pirate_name=" + pirate_name + ", secret_code="
				+ secret_code + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pirate_name == null) ? 0 : pirate_name.hashCode());
		result = prime * result + pirate_number;
		result = prime * result + ((secret_code == null) ? 0 : secret_code.hashCode());
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
		Pirate other = (Pirate) obj;
		if (pirate_name == null) {
			if (other.pirate_name != null)
				return false;
		} else if (!pirate_name.equals(other.pirate_name))
			return false;
		if (pirate_number != other.pirate_number)
			return false;
		if (secret_code == null) {
			if (other.secret_code != null)
				return false;
		} else if (!secret_code.equals(other.secret_code))
			return false;
		return true;
	}

	public int getPirate_number() {
		return pirate_number;
	}

	public void setPirate_number(int pirate_number) {
		this.pirate_number = pirate_number;
	}

	public String getPirate_name() {
		return pirate_name;
	}

	public void setPirate_name(String pirate_name) {
		this.pirate_name = pirate_name;
	}

	public String getSecret_code() {
		return secret_code;
	}

	public void setSecret_code(String secret_code) {
		this.secret_code = secret_code;
	}
	
	

}
