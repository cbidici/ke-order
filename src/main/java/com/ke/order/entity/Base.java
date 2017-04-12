package com.ke.order.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Base class for all entities
 * @author cbidici
 * @since 0.0.1 (20141219)
 */
@MappedSuperclass
public abstract class Base implements Serializable {

	private static final long serialVersionUID = -2158804732696712622L;

	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Override default behavior of toString method for printing objects in json format 
	 * @author cbidici
	 * @since 0.0.1 (20141220)
	 * @return JSON formatted object string
	 */
	@Override
	public String toString()
	{
		String str = null;
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			str = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// No need to handle this exception in scope of this application
			str = "";
		}
		
		return str;
	}
	
}
