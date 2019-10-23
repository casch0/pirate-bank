package com.blued.models;

import java.math.BigDecimal;

public class PirateChest {
	
	private int chestID;
	private int pirate_number;
	private BigDecimal booty;
	private String type;
	
	public PirateChest(int chestID, int pirate_number, BigDecimal booty, String type) {
		super();
		this.chestID = chestID;
		this.pirate_number = pirate_number;
		this.booty = booty;
		this.type = type;
	}

	@Override
	public String toString() {
		return "pirateChest [chestID=" + chestID + ", pirate_number=" + pirate_number + ", type=" + type + ", booty="
				+ booty + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((booty == null) ? 0 : booty.hashCode());
		result = prime * result + chestID;
		result = prime * result + pirate_number;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		PirateChest other = (PirateChest) obj;
		if (booty == null) {
			if (other.booty != null)
				return false;
		} else if (!booty.equals(other.booty))
			return false;
		if (chestID != other.chestID)
			return false;
		if (pirate_number != other.pirate_number)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public int getChestID() {
		return chestID;
	}

	public void setChestID(int chestID) {
		this.chestID = chestID;
	}

	public int getPirate_number() {
		return pirate_number;
	}

	public void setPirate_number(int pirate_number) {
		this.pirate_number = pirate_number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getBooty() {
		return booty;
	}

	public void setBooty(BigDecimal booty) {
		this.booty = booty;
	}
	
	

}
