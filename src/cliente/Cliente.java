
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

/**
 *
 * @author maximiliano
 */
public class Cliente extends javax.swing.JFrame {
    Thread leecturaThread;
    /**
     * Creates cliente gui
     */
    public Cliente() {
        conectar();
        initComponents();
        
        Runnable lecturaRunnable = new Runnable() {
            public void run() {
                while(!cliente.status())
                    try {
                        if(!cliente.status())
                        recibidosTextArea.append(cliente.leer()+"\n");
                    } catch (IOException ex) {
                    try {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                        if(cliente.serverIsLive()){
                        }
                        leecturaThread.sleep(2000);
                        
                        } catch (InterruptedException ex1) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex1);
                        leecturaThread.interrupt();
                        leecturaThread=null;
                    
                    }
                    catch (java.net.SocketException exc) {   
                        } catch (IOException exc) {
                            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("se ha perdido la coneccion con el server, intente a conectarse más tarde");
                            break;
                        }
                    }finally{
                    
                    }
            }
        };
         
        leecturaThread=new Thread(lecturaRunnable);

        leecturaThread.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private SocketClient conectar(){
        try{
            
            //String dir []=JOptionPane.showInputDialog("Ingrese la dirección a la que desea conectarse", direccion+":"+puerto).split(":");
            //direccion=dir[0];
            //puerto=Integer.parseInt(dir[1]);
            cliente=new SocketClient(direccion,puerto);
            
        }catch (NumberFormatException | NullPointerException |IOException e){
            if(reintentar()==0){
                conectar();
            }
            else {
                System.exit(0);
            }
        }
        return cliente;
    }
    private int reintentar(){
        return JOptionPane.showConfirmDialog(rootPane, "Un error se ha presentado \n Desea volver a intentarlo", "REINTENTAR", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
    }
    private void initComponents() {

        scrollpanel = new javax.swing.JScrollPane();
        generalPane = new javax.swing.JPanel();
        userPane = new javax.swing.JPanel();
        nickLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        recibirPane = new javax.swing.JScrollPane();
        recibidosTextArea = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        enviarButton = new javax.swing.JButton();
        enviarPane = new javax.swing.JScrollPane();
        enviarTextArea = new javax.swing.JTextArea();
        barramenu = new javax.swing.JMenuBar();
        opciones = new javax.swing.JMenu();
        conectar = new javax.swing.JMenuItem();
        desconectar = new javax.swing.JMenuItem();
        usuario = new javax.swing.JMenu();
        cambiarnick = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        scrollpanel.setBackground(javax.swing.UIManager.getDefaults().getColor("Menu.disabledForeground"));

        nick=JOptionPane.showInputDialog("introdusca su nombre de usuario");
        nickLabel.setText(nick);

        logoutButton.setText("Salir");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout userPaneLayout = new javax.swing.GroupLayout(userPane);
        userPane.setLayout(userPaneLayout);
        userPaneLayout.setHorizontalGroup(
            userPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nickLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logoutButton)
                .addContainerGap())
        );
        userPaneLayout.setVerticalGroup(
            userPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPaneLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(userPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nickLabel)
                    .addComponent(logoutButton))
                .addGap(5, 5, 5))
        );

        recibidosTextArea.setColumns(20);
        recibidosTextArea.setRows(5);
        recibidosTextArea.setEditable(false);
        //recibidosTextArea.setEnabled(false);
        recibirPane.setViewportView(recibidosTextArea);

        enviarButton.setText("Enviar");
        enviarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarButtonActionPerformed(evt);
            }
        });
        
        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(enviarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(enviarButton)
                .addGap(27, 27, 27))
        );

        enviarTextArea.setColumns(20);
        enviarTextArea.setRows(5);
        enviarTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    enviarButtonActionPerformed(new ActionEvent(enviarTextArea, ActionEvent.ACTION_PERFORMED, null));
                    enviarTextArea.setText("");
                }
            }
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    enviarTextArea.setText("");
                }
            }
            
        });
        enviarPane.setViewportView(enviarTextArea);

        javax.swing.GroupLayout generalPaneLayout = new javax.swing.GroupLayout(generalPane);
        generalPane.setLayout(generalPaneLayout);
        generalPaneLayout.setHorizontalGroup(
            generalPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(generalPaneLayout.createSequentialGroup()
                        .addComponent(enviarPane)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addComponent(recibirPane))
                .addContainerGap())
        );
        generalPaneLayout.setVerticalGroup(
            generalPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPaneLayout.createSequentialGroup()
                .addComponent(userPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(recibirPane)
                .addGap(18, 18, 18)
                .addGroup(generalPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enviarPane, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        scrollpanel.setViewportView(generalPane);

        barramenu.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        opciones.setText("Opciones");
        
        
        conectar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        conectar.setText("Conectar");
        conectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conectarActionPerformed(evt);
            }
        });
        opciones.add(conectar);
        desconectar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        desconectar.setText("Desconectar");
        desconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });
        opciones.add(desconectar);

        barramenu.add(opciones);

        usuario.setText("Usuario");

        cambiarnick.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        cambiarnick.setText("Cambiar Nick");
        usuario.add(cambiarnick);

        barramenu.add(usuario);

        setJMenuBar(barramenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollpanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollpanel)
        );

        setSize(new java.awt.Dimension(402, 280));
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void enviarButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if(!enviarTextArea.getText().equals("")){
            cliente.escribir(nick+" ->  "+enviarTextArea.getText());
            this.enviarTextArea.setText("");
        }
    }                                            

    private void conectarActionPerformed(java.awt.event.ActionEvent evt) {                                         
       cliente=this.conectar();
    }                                        

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        cliente.cerrar();
    }                                            

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
        javax.swing.UIManager.setLookAndFeel(info.getClassName());
        break;
        }
        }
        } catch (ClassNotFoundException ex) {
        java.util.logging.Logger.getLogger(prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JMenuBar barramenu;
    private javax.swing.JMenuItem cambiarnick;
    private javax.swing.JMenuItem conectar;
    private javax.swing.JMenuItem desconectar;
    private javax.swing.JButton enviarButton;
    private javax.swing.JScrollPane enviarPane;
    private javax.swing.JTextArea enviarTextArea;
    private javax.swing.JPanel generalPane;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton logoutButton;
    private javax.swing.JLabel nickLabel;
    private javax.swing.JMenu opciones;
    private javax.swing.JTextArea recibidosTextArea;
    private javax.swing.JScrollPane recibirPane;
    private javax.swing.JScrollPane scrollpanel;
    private javax.swing.JPanel userPane;
    private javax.swing.JMenu usuario;
    private SocketClient cliente;
    private String direccion="127.0.0.1";
    private int puerto=3000;
    private String nick="nick";
    // End of variables declaration                   
}
