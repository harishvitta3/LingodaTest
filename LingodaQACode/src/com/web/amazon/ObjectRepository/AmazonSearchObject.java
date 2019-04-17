//This package is created to store all the test object properties, declare variables and methods 
//for object operations
package com.web.amazon.ObjectRepository;

public enum AmazonSearchObject {
	//Defining Test Objects
	SearchBoxInput_XPATH("//input[@id='twotabsearchtextbox']"),
	SearchBtn_XPATH("(//*[@class='nav-input'])[1]"),
	SearchResults_XPATH("//*[contains(text(),'von mehr als')]"),
	SearchTitle_XPATH("(//*[@class='a-link-normal a-text-normal'])[1]"),
	CurrencySymbol_XPATH("(//*[@class='a-price-symbol'])[1]"),
	Price_XPATH("(//*[@class='a-price-whole'])[1]"),
	TitleAfterClick_XPATH("//*[@id='productTitle']"),
	PriceAfterClick_XPATH("//span[text()='Neu kaufen']//following::span[1]"),
	StarRating_XPATH("(//*[contains(text(),'Sternen')])[1]"),
	;
	
	//Variable Declaration
	private final String enumValue;
	
	//Methods for object operations
	private AmazonSearchObject (String s)
	{
		enumValue = s;
	}
	
	public String getValue()
	{
	   return enumValue;
	}
	
	public String toString()
	{
		return getValue();
	}
}
