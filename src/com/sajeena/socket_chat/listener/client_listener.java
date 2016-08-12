/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sajeena.socket_chat.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author Admin
 */
public class client_listener extends Thread {

    private Socket socket;
     private Client client;
     private Client_handler handler;

    public client_listener(Socket socket,Client_handler handler) {
        this.socket = socket;
        this.handler=handler;
       
    }

    @Override
    public void run() {
        try {
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.println("Welcome");
            ps.println("Enter your name:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String name = reader.readLine();
            System.out.println("Hello " + name);
            client=new Client(name, socket);
            handler.addClient(client);
            handler.broadcastMessage(client.getUserName() +" has joined");
            while(!isInterrupted()){
                String msg=reader.readLine();
                String[] token=msg.split(";;");
                System.out.println(token.length);
                if(token [0].equalsIgnoreCase("pm")){
                    if(token.length>2){
                        handler.privateMessage(token[1],"PM from " +client.getUserName() + ":" + token[2]);
                    }
                }else
               handler.broadcastMessage(client.getUserName() + " says:" + msg);
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

}
