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
public class EntregadorAtivo extends Estado {
    Entregador e;
    
    public static final int aceitaPedidoDeEntrega = 1;    
    public static final int notificaEntrega = 2;
    
    public EntregadorAtivo(Entidade e){
        super(e);
        this.e = (Entregador)e; //Pra facilitar na função transição
    }
    
    @Override
    public void transicao(Evento ev){
        switch(ev.codigo){
            case aceitaPedidoDeEntrega:
                
                break;
            case notificaEntrega:
                
                break;
            default:
        }
    }
}
