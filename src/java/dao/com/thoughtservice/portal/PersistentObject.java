package com.thoughtservice.portal;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class PersistentObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5050341803250433415L;

	private Long id;
	private Date lastUpdate;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PersistentObject)) {
			return false;
		}
		final PersistentObject other = (PersistentObject) obj;
		if (this.id != null && other.id != null) {
			if (this.id != other.id) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode() {	
		if (this.id != null) {
			return id.hashCode();
		} else {
			return super.hashCode();
		}		
	}
	
	@Override
	public String toString() {	
		if(id != null) {
			return "Object id : "+id.toString();
		} else {
			return "";
		}
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
