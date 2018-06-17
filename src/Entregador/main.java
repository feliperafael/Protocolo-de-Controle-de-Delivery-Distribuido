/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entregador;

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
    
    Entidade e;
    
    public static final int cadastraEntregador = 9;
    public static final int aceitaPedidoDeEntrega = 1;    
    public static final int notificaEntrega = 2;
    public static final int recebePedidoDeEntrega = 3;
    
    public static void main(String [] args) throws IOException{
        main m = new main();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
        System.out.print("Informe a Porta do Entregador (Ex.: 7000): ");
        String porta = in.readLine();
        
        m.inicia(Integer.parseInt(porta));
        System.out.println("Entregador iniciado!");
        System.out.println("\nOpções:\n 1 - Aceitar entrega de pedido (Ex.: idPedido,idRestaurante)\n 2 - Notificar entrega de pedido(Ex.: idPedido,idRestaurante)\n");
    }
    
    public void inicia(int porta){
        e = new Entregador(porta);
        
        // Inicia thread de tratamento de eventos
        sthread = new EventoThread(e);
        thread1 = new Thread(sthread);
        thread1.start();
        
        // Inicia threar de leitura do socket
        xthread = new SocketThread(e.msg, e);
        thread2 = new Thread(xthread);
        thread2.start();
        
        
        // Cadastro do Entregador
        Evento ev = new Evento(cadastraEntregador,"-1","-1",String.valueOf(porta)); // Apenas para cadastro
        
        //e.transicao(new Evento(cadastraEntregador,"-1","-1",String.valueOf(porta)));
        e.msg.conecta("localhost", 9000);
        e.msg.envia(ev.toString());
        e.msg.termina();
        
    }
}
