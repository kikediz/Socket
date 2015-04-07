package servidor;

import java.awt.event.KeyListener;
import java.net.*;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class SocketServerHilo implements Runnable {
    final ArrayList<Socket> clientes;
    String recibido;
    OutputStream osalida;
    DataOutputStream dsalida;

    InputStream ientrada;
    DataInputStream dentrada;
    
    private final Gui_Servidor gui_Servidor;
	
    Socket socket;

    public SocketServerHilo(Socket lsocket,Gui_Servidor gui_Servidor1,ArrayList<Socket> clientes){
        this.gui_Servidor=gui_Servidor1;
        this.clientes=clientes;
        this.gui_Servidor.Aescribir.addKeyListener(listener());
            try{
                this.socket = lsocket;
                this.gui_Servidor.jTextArea.append("\n"+new Date().toString()+"  "+socket.getInetAddress().getCanonicalHostName()+" Se conectó!");
            }
            catch (Exception excepcion) {
		System.out.println(excepcion);
            }
        System.out.println("crea socket");
    }
    
    
    
    @Override
    public void run() {
        try {
             ientrada = socket.getInputStream();
             dentrada = new DataInputStream(ientrada);
            //System.out.printf("sock "+socket.isBound());
            while(true){
               
                System.out.println(!socket.isClosed());
                recibido = dentrada.readUTF();
                if(recibido.equals("exit()")){
                    socket.close();
                    Thread hilo = new Thread(
                        new Runnable() {

                        @Override
                        public void run() {
                            for(int i=0; i<clientes.size();i++){
                                if(socket.equals(clientes.get(i))){
                                    System.out.println(socket.toString()+" == "+ clientes.get(i));
                                    clientes.remove(i);

                                }
                            }
                        }
                    });
                    hilo.start();
                    break;
                }
                else{
                    System.out.println(this);
                    for(int i=0; i<clientes.size();i++){
                        escribir(clientes.get(i), recibido);
                    }
                }


                //System.out.println("recibido desde el cliente: " + recibido);
                gui_Servidor.jTextArea.append("\n"+new Date().toString()+"  "+socket.getInetAddress().getCanonicalHostName()+" Escribió");
                gui_Servidor.Achat.append("\n"+recibido);

            }
            gui_Servidor.jTextArea.append("\n"+new Date().toString()+"  "+socket.getInetAddress().getCanonicalHostName()+" Desconectado!");
        } catch (IOException ex) {
            ex.printStackTrace();
            gui_Servidor.jTextArea.append("\n"+new Date().toString()+"  "+socket.getInetAddress().getCanonicalHostName()+" Desconectado!");
            //clientes.get(i);
        }
            
    }
    
    public void escribir(Socket cliente,String msm){
        Thread escritura = new Thread(escribirHilo(cliente, msm));
        escritura.start();
    }
    public void close(){
        try{
            dsalida.close();
            dentrada.close();
            socket.close();
        }
        catch (IOException excepcion) {
            System.out.println(excepcion);
        }
    }
    
    private KeyListener listener(){
        return new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e){
                if(e.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER){
                    try {
                        dsalida.writeUTF(gui_Servidor.Aescribir.getText());
                        gui_Servidor.Achat.append("\n"+gui_Servidor.Aescribir.getText());
                        gui_Servidor.Aescribir.setText("");
                    } catch (IOException ex) {
                        Logger.getLogger(SocketServerHilo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            @Override
            public void keyReleased(java.awt.event.KeyEvent e){
                if(e.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER){
                    gui_Servidor.Aescribir.setText("");
                }
            }
        };
    }
    private Runnable escribirHilo(Socket cliente,String msm){
        return () -> {
            try {
                if(!cliente.isClosed()){
                    try (DataOutputStream dataOutputStream = new DataOutputStream(cliente.getOutputStream())) {
                        dataOutputStream.writeUTF(msm);
                        //dataOutputStream.flush();
                    }
                }
                else{
                    //this.finalize();
                }
                
            }catch (IOException ex) {
                Logger.getLogger(SocketServerHilo.class.getName()).log(Level.SEVERE, null, ex);
                
                
            } catch (Throwable ex) {
                Logger.getLogger(SocketServerHilo.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        };
    }
}