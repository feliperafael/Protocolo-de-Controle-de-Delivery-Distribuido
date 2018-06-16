/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import framework.Entidade;
import framework.EventoThread;
//import framework.SocketThread;

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
    
    Entidade s;
    
    public static void main(String [] args){
        main m = new main();
        int porta = m.inicia();
        System.out.println("Sistema rodando na porta -> " + porta);
    }
    
    public int inicia(){
        int porta = 9000; //FIXADA
        s = new Sistema(porta);
        
        // Inicia thread de tratamento de eventos
        sthread = new EventoThread(s);
        thread1 = new Thread(sthread);
        thread1.start();
        
        // Inicia threar de leitura do socket
        xthread = new SocketThread(s.msg, s);
        thread2 = new Thread(xthread);
        thread2.start();
        
        return porta;
    }
}
