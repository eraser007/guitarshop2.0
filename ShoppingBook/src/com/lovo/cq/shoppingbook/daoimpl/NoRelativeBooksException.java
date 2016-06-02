package com.lovo.cq.shoppingbook.daoimpl;

public class NoRelativeBooksException extends RuntimeException{
	public NoRelativeBooksException(String message){
		super(message);
	}
}
