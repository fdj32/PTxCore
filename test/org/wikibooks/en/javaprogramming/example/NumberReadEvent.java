package org.wikibooks.en.javaprogramming.example;

import java.util.EventObject;

public class NumberReadEvent extends EventObject {

	private static final long serialVersionUID = 5008439863024286855L;

	private Double number;

	public NumberReadEvent(Object source, Double number) {
		super(source);
		this.number = number;
	}

	public Double getNumber() {
		return number;
	}

}
