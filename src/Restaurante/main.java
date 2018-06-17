/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restaurante;

import framework.Entidade;
import framework.Evento;
import framework.EventoThread;
//import framework.SocketThread;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author lucas
 */
public class main {
    /// Thread de Tratamento de Eventos
    Thread thread1;  
    EventoThread sthread;
    
    /// Thread de Leitura do Socket
    Thread thread2;
    SocketThread xthread;
    
    Entidade r;
    
    public static final int cadastroRestaurante = 0;
    public static final int solicitaEntrega = 1;    
    public static final int desalocaPedido  = 2;
    
    public static void main(String [] args) throws IOException{
        main m = new main();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
        System.out.print("Informe a Porta do Restaurante (Ex.: 9000): ");
        String porta = in.readLine();
        
        System.out.println("Restaurante iniciado!");
        m.inicia(Integer.parseInt(porta));
    }
    
    public void inicia(int porta){
        r = new Restaurante(porta);
        
        // Inicia thread de tratamento de eventos
        sthread = new EventoThread(r);
        thread1 = new Thread(sthread);
        thread1.start();
        
        // Inicia threar de leitura do socket
        xthread = new SocketThread(r.msg, r);
        thread2 = new Thread(xthread);
        thread2.start();
        
        // Cadastro do restaurante
        Evento e = new Evento(cadastroRestaurante,String.valueOf(porta),"-1","-1"); // Apenas para cadastro
        r.msg.conecta("localhost", 9000);
        r.msg.envia(e.toString());
        r.msg.termina();
    }
}
