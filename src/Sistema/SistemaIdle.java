/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import framework.Entidade;
import framework.Estado;
import framework.Evento;

/**
 *
 * @author lucas
 */
public class SistemaIdle extends Estado{
    Sistema s;
    
    public static final int recebePedidoDeEntrega = 3;    
    public static final int divulgaPedidoDeEntrega = 4;
    public static final int recebeConfirmacaoDePedidoDeEntrega = 5;
    public static final int recebeConfirmacaoDeEntrega = 6;
    public static final int enviaConfirmacaoDePedidoDeEntrega = 7;
    public static final int enviaConfirmacaoDeEntrega = 8;
    
    
    public SistemaIdle(Entidade e){
        super(e);
        s = (Sistema)e; //Pra facilitar na função transição
    }
    
    @Override
    public void transicao(Evento ev){
        switch(ev.codigo){
            case recebePedidoDeEntrega:
                
                break;
            case divulgaPedidoDeEntrega:
                
                break;
            case recebeConfirmacaoDePedidoDeEntrega:
                
                break;
            case recebeConfirmacaoDeEntrega:
                
                break;
            case enviaConfirmacaoDePedidoDeEntrega:
                
                break;
            case enviaConfirmacaoDeEntrega:
                
                break;
            default:
        }
    }
}
