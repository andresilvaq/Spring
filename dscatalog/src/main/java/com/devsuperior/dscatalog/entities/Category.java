package com.devsuperior.dscatalog.entities;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

public class Category implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter @Setter private Long id;
	private String name;
	
	public Category () {
		
	}
	public Category(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
