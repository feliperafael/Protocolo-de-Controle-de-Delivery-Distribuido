/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entregador;

import framework.Entidade;
import framework.Estado;
import framework.Evento;

/**
 *
 * @author lucas
 */
public class EntregadorAtivo extends Estado implements Runnable{
    Entregador e;
    
    public EntregadorAtivo(Entidade e){
        super(e);
        this.e = (Entregador)e; //Pra facilitar na função transição
    }
    
    @Override
    public void transicao(Evento ev){
        switch(ev.codigo){
            case main.cadastraEntregador:
                System.out.println("Entregador cadastrado com sucesso");
                System.out.println("*********CADASTRO DE ENTREGADOR*********");
                new Thread(this).start();
                break;
            case main.aceitaPedidoDeEntrega:
                
                break;
            case main.notificaEntrega:
                
                break;
            case main.recebePedidoDeEntrega:
                
                break;
            default:
        }
    }
    
    @Override
    public void run(){
        
        System.out.println("run do Entregador");
    }
}
