package com.frontApp;

import com.frontApp.Adapters.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;

public class GSONBuilder {
	private static Gson gson;

	public static Gson getGson(){
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(LocalDate.class,new LocalDateAdapter());
		builder.setPrettyPrinting();
		gson = builder.create();
		return gson;
	}



}
