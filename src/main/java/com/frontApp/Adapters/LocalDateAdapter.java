package com.frontApp.Adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {


	@Override
	public void write(JsonWriter out, LocalDate date) throws IOException {
		out.beginObject();
		out.name("regYear");
		out.value(date.getYear());
		out.name("regMonth");
		out.value(date.getMonthValue());
		out.name("regDay");
		out.value(date.getDayOfMonth());
		out.endObject();
	}



	@Override
	public LocalDate read(JsonReader reader) throws IOException{
		reader.beginObject();
		String fieldName=null;
		LocalDate date = LocalDate.EPOCH;

		while(reader.hasNext()){
			try{
				JsonToken token = reader.peek();

				if(token.equals(JsonToken.NAME)){
					fieldName=reader.nextName();
				}

				if("regYear".equals(fieldName)){
					token = reader.peek();
					date = date.withYear(reader.nextInt());
				}
				if("regMonth".equals(fieldName)){
					token=reader.peek();
					date = date.withMonth(reader.nextInt());
				}
				if("regDay".equals(fieldName)){
					token=reader.peek();
					date = date.withDayOfMonth(reader.nextInt());
				}
			}catch (DateTimeException ex){
				ex.printStackTrace();
			}


		}
		reader.endObject();
		return date;

	}
}
