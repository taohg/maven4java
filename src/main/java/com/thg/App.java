package com.thg;

import java.util.UUID;


/**
 * Hello world!
 *
 */
public class App 
{
	public String generateUniqueKey(){
    	String id = UUID.randomUUID().toString();
    	return id;
    	
    }
	
    public static void main( String[] args )
    {
    	App obj = new App();
        System.out.println( "Hello World!" + obj.generateUniqueKey());
    }
}
