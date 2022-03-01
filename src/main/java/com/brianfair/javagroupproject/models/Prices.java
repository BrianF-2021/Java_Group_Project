package com.brianfair.javagroupproject.models;

import java.util.HashMap;
import java.util.List;

public class Prices {
	
//	private static String[] toppings;
//	private static String size;
//	private static String quantity;
//	private static HashMap<String, Double> pricing; 
//	
//	public Prices(String[] toppings, String size, String quantity)
//	{
//		Prices.toppings = toppings;
//		Prices.size = size;
//		Prices.quantity = quantity;
//		Prices.pricing.put("toppings", .50);
//		Prices.pricing.put("small", 9.99);
//		Prices.pricing.put("medium", 11.99);
//		Prices.pricing.put("large", 13.99);
//		Prices.pricing.put("xlarge", 15.99);
//
//	}
//	private static String[] toppings;
//	private static String size;
//	private static String quantity;
	private static HashMap<String, Double> pricing = Prices.setPricing();

	
	public static String calculatePrice(String[] toppings, String size, String quantity)
	{
		Double toppings_price = toppings.length*Prices.getPricing().get("toppings");
		Double size_price = Prices.getPricing().get(size);
		Double price = (toppings_price+size_price)*Double.parseDouble(quantity);
		
		return price.toString();
	}

	
	private static HashMap<String, Double> getPricing() {
		return pricing;
	}

	private static HashMap<String, Double> setPricing()
	{
		HashMap<String, Double> pricing = new HashMap<String, Double>();
		pricing.put("toppings", .50);
		pricing.put("small", 9.99);
		pricing.put("medium", 11.99);
		pricing.put("large", 13.99);
		pricing.put("xlarge", 15.99);
		return pricing;
	}

	

	
	
}
