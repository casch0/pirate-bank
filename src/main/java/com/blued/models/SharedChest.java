package com.blued.models;

import java.math.BigDecimal;

public class SharedChest {

	private int shared_number;
	private int pirate_number;
	private BigDecimal booty;
	private String chest_type;
	
	public SharedChest(int shared_number, int pirate_number, BigDecimal booty, String chest_type) {
		super();
		this.shared_number = shared_number;
		this.pirate_number = pirate_number;
		this.booty = booty;
		this.chest_type = chest_type;
	}

	@Override
	public String toString() {
		return "SharedChest [shared_number=" + shared_number + ", pirate_number=" + pirate_number + ", booty=" + booty
				+ ", chest_type=" + chest_type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((booty == null) ? 0 : booty.hashCode());
		result = prime * result + ((chest_type == null) ? 0 : chest_type.hashCode());
		result = prime * result + pirate_number;
		result = prime * result + shared_number;
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
		SharedChest other = (SharedChest) obj;
		if (booty == null) {
			if (other.booty != null)
				return false;
		} else if (!booty.equals(other.booty))
			return false;
		if (chest_type == null) {
			if (other.chest_type != null)
				return false;
		} else if (!chest_type.equals(other.chest_type))
			return false;
		if (pirate_number != other.pirate_number)
			return false;
		if (shared_number != other.shared_number)
			return false;
		return true;
	}

	public int getShared_number() {
		return shared_number;
	}

	public void setShared_number(int shared_number) {
		this.shared_number = shared_number;
	}

	public int getPirate_number() {
		return pirate_number;
	}

	public void setPirate_number(int pirate_number) {
		this.pirate_number = pirate_number;
	}

	public BigDecimal getBooty() {
		return booty;
	}

	public void setBooty(BigDecimal booty) {
		this.booty = booty;
	}

	public String getChest_type() {
		return chest_type;
	}

	public void setChest_type(String chest_type) {
		this.chest_type = chest_type;
	}
	
	
	
}
