package com.aldemoralinator.lemonade.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExceptionMessageBuilder {
	
	private List<String> messages = new ArrayList<>();
	
	public ExceptionMessageBuilder() {
		
	}
	
	public ExceptionMessageBuilder(String message) {
		this.append(message);
	}
	
	public Integer getLength() {
		return messages.size();
	}
	
	public void append(String message) {
		this.messages.add(message);
	}
	
	public void throwExceptionIfNotEmpty() {
		if (this.getLength() > 0) 
			throw new IllegalStateException(
					messages.stream()
							.map(Object::toString)
							.collect(Collectors.joining("\r\n")));
	}

}
