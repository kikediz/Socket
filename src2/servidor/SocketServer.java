package servidor;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class SocketServer {
    
    //SocketServer socketServer;
    static ArrayList<Socket> clientes;
    
    public SocketServer(Gui_Servidor gui_Servidor){
        clientes = new ArrayList<>();
            try {
                ServerSocket servidor = new ServerSocket(3000, 65000);
                int i=0;
                do{
                    System.out.println("Esperando cliente");							
                    clientes.add(servidor.accept());
                    Runnable nuevoSocket = new SocketServerHilo(clientes.get(i), gui_Servidor,clientes);
                    Thread hiloSocket = new Thread(nuevoSocket);
                    hiloSocket.start();
                    
                    
                    /*Runnable socket_escritura = new SocketServerHilo(clientes, gui_Servidor);
                    Thread hiloescritura = new Thread(socket_escritura);
                    hiloescritura.start();*/
                    
                    
                    System.out.println("Cliente recibido");
                    System.out.println(i++);
                    System.out.println("tamaño lista "+clientes.size());
                }while(true);
            }
            catch (IOException excepcion) {			
                System.out.println(excepcion);
            }
        
    }
}