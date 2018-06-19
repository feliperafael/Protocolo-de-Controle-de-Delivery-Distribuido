/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restaurante;

import framework.Entidade;
import framework.Estado;
import framework.Evento;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                new Thread(this).start();
                break;
            case main.solicitaEntrega:
                Evento e = new Evento(3,String.valueOf(ev.portaRestaurante),String.valueOf(ev.idPedido),String.valueOf(ev.portaEntregador));
                r.msg.conecta("localhost", 9000); 
                r.msg.envia(e.toString());
                r.msg.termina();                
                break;
            case main.entregaNotificada:
                System.out.println("Entrega do pedido com id " + ev.idPedido + " confirmada.");
                r.fecharPedidoEntregue(Integer.valueOf(ev.idPedido),Integer.valueOf(ev.portaEntregador));
                break;
            default:
        }
    }
    
    @Override
    public void run(){
        while(true){
            /*
            "- 1  Gerar Pedido     -"
            "- 2  Listar pedidos a ser entregue.   -"
            "- 3  Listar pedidos entregues.                                   -"                                   -"
            */
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
            r.printMenu();
            
            String aux;
            try{
                aux = in.readLine();               
            }catch(IOException | NumberFormatException ex){
                aux = "-1";
            }
            int opcao = Integer.valueOf(aux);
            switch(opcao){
                case 1: 
                    System.out.print("Informe o ID do pedido: ");
                    try{
                        aux = in.readLine(); 

                    }catch(IOException | NumberFormatException ex){
                        aux = "-1";
                    }
                    int idPedido = Integer.valueOf(aux);
                    System.out.println(idPedido);
                    Pedido p = r.gerarPedido(idPedido);


                    //transição 1 - solicita entrega
                    //gera evento que solicita entrega
                    r.transicao(new Evento(main.solicitaEntrega,String.valueOf(p.portaRestaurante),String.valueOf(p.idPedido),String.valueOf(p.portaEntregador)));
                    break;
                case 2:
                    r.listarPedidoProntos();
                    break;
                case 3:
                    r.listarPedidoEntregues();
                    break;
                default:
            }
           
        }
    }
}