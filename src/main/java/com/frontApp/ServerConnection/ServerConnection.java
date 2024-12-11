package com.frontApp.ServerConnection;

import java.io.*;
import java.net.Socket;

public class ServerConnection {

	private Socket appSocket;
	private ObjectOutputStream appOutput;
	private ObjectInputStream appInput;
	public ServerConnection(){
		try{
			System.out.println("Connecting to server...");
			appSocket = new Socket("127.0.0.1",25555);
			System.out.println("Connected!");
			appOutput= new ObjectOutputStream(appSocket.getOutputStream());
			appInput = new ObjectInputStream(appSocket.getInputStream());
		}catch(IOException ex){
			ex.printStackTrace();
		}

	}


	public void sendMessage(Object message){
		try{
			appOutput.writeObject(message);
		}catch (IOException ex){
			ex.printStackTrace();
		}

	}


	public String getServerMessage(){
		String result=null;
		try{
			result = (String) appInput.readObject();
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return result;
	}




}
