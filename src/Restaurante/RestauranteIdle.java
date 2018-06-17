/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restaurante;

import framework.Entidade;
import framework.Estado;
import framework.Evento;

/**
 *
 * @author lucas
 */
public class RestauranteIdle extends Estado implements Runnable{
    Restaurante r;    
    
    public RestauranteIdle(Entidade e){
        super(e);
        r = (Restaurante)e; //Pra facilitar na função transição
    }
    
    @Override
    public void transicao(Evento ev){
        switch(ev.codigo){
            case main.cadastroRestaurante:
                System.out.println("Restaurante cadastrado com sucesso!");
                System.out.println("\nOpcoes:\n1 - Gerar Pedido\n");
                new Thread(this).start();
                break;
            case main.solicitaEntrega:
                
                break;
            case main.desalocaPedido:
                
                break;
            default:
        }
    }
    
    @Override
    public void run(){
        while(true){
            Pedido p = r.gerarPedido();
            
            /// Gera Evento
            Evento e = new Evento(3,String.valueOf(p.portaRestaurante),String.valueOf(p.idPedido),String.valueOf(p.portaEntregador));
                                  

            /// Envia a Menssagem
            r.msg.conecta("localhost", 9000); 
            r.msg.envia(e.toString());
            r.msg.termina();
        }
    }
}
