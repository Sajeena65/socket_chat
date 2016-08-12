/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sajeena.socket_chat;

import com.sajeena.socket_chat.listener.Client_handler;
import com.sajeena.socket_chat.listener.client_listener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Admin
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       int port=9000;
       try{
           ServerSocket server=new ServerSocket(port);
           System.out.println("server is running at "+ port);
           Client_handler handler=new Client_handler();
           while(true){
               Socket client=server.accept();
               System.out.println("Connetion Request from "+ client.getInetAddress().getHostAddress());
               client_listener listener=new client_listener(client,handler);
               listener.start();
           }        
       }catch(IOException ioe){
           System.out.println(ioe.getMessage());
       }
    }
    
}
