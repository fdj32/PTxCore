package org.wikibooks.en.javaprogramming.example;

public class NumberReadListenerImpl implements NumberReadListener {
	
	private Double totalSoFar = 0D;

	@Override
	public void numberRead(NumberReadEvent numberReadEvent) {
		totalSoFar += numberReadEvent.getNumber();
	}

	@Override
	public void numberStreamTerminated(NumberReadEvent numberReadEvent) {
		System.out.println("Sum of the number stream: " + totalSoFar);
	}

}
