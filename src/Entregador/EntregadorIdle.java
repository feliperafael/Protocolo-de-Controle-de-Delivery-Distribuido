/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entregador;

import Restaurante.Pedido;
import static Sistema.SistemaIdle.recebeConfirmacaoDePedidoDeEntrega;
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
public class EntregadorIdle extends Estado implements Runnable{
    Entregador e;
    public static final int cadastroEntregador = 0;
    public static final int aceitaPedidoDeEntrega = 1;    
    public static final int recebePedidoDeEntrega = 3;
    public static final int confirmacaoDeAceite = 4;
    
    public EntregadorIdle(Entidade e){
        super(e);
        this.e = (Entregador)e; //Pra facilitar na função transição
    }
    
    @Override
    public void transicao(Evento ev){
        System.out.println("Estado atual: EntregadorIdle");
        switch(ev.codigo){
            case cadastroEntregador:
                System.out.println("Cadastrou entregador");
                new Thread(this).start();

                break;
            case aceitaPedidoDeEntrega:
                System.out.println("Aceitando pedido de entrega...");
                Evento ev_2 = new Evento(Sistema.SistemaIdle.recebeConfirmacaoDePedidoDeEntrega,String.valueOf(ev.portaRestaurante),String.valueOf(ev.idPedido),String.valueOf(ev.portaEntregador));
                e.msg.conecta("localhost", 9000); 
                e.msg.envia(ev_2.toString());
                e.msg.termina();  
                break;
            case recebePedidoDeEntrega:
                System.out.println("recebePedidoDeEntrega");
                e.RecebePedidoDeEntrega(ev);
                break;
            case confirmacaoDeAceite:
                System.out.println("ACK de aceite");
                if(Integer.valueOf(ev.portaEntregador) == (e.portaEntregador)){
                 // coloca na mochila
                 System.out.println("Colocando na mochila");
                 e.adicionaNaMochila(new Pedido(Integer.valueOf(ev.portaRestaurante),Integer.valueOf(ev.idPedido)));
                }
                int indice = e.getIndicePedidoEntrega(Integer.valueOf(ev.portaRestaurante), Integer.valueOf(ev.idPedido));
                e.pedidos_de_entrega.remove(indice);  //remove da lista de pedidos_de_entrega
                
                e.mudaEstado(e.ativo);//vai para estado ativo
              
                break;
            default:
        }
    }
    
    @Override
    public void run(){  
        /*
        "- 1  Aceitar entrega de pedido (Ex.: idPedido,idRestaurante)     -"
        "- 2  Notificar entrega de pedido (Ex.: idPedido,idRestaurante)   -"
        "- 3  Listar pedidos de entrega                                   -"
        "- 4  Listar itens na mochila                                     -"
        */
        while(true){
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
            //print Menu
            e.printMenu();
            String aux;
            try{
                aux = in.readLine();               
            }catch(IOException | NumberFormatException ex){
                aux = "-1";
            }
            int opcao = Integer.valueOf(aux);
            switch(opcao){
                case 1:
                    //se existe algum pedido pra aceitar
                    if(!e.pedidos_de_entrega.isEmpty()){
                        System.out.println("Digite o id do pedido de entrega que deseja aceitar:");
                        e.listaPedidosdeEntrega();
                        Integer k = -1;
                        try {
                            k = Integer.valueOf(in.readLine());
                        } catch (IOException ex) {
                            Logger.getLogger(EntregadorIdle.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(k>=0){
                            Pedido p = e.pedidos_de_entrega.get(k);
                            e.transicao(new Evento(aceitaPedidoDeEntrega,String.valueOf(p.portaRestaurante),String.valueOf(p.idPedido),String.valueOf(e.portaEntregador)));
                        }
                    }else{
                        System.out.println("Você ainda não tem nenhum pedido pra aceitar");
                    }
                    break;
                case 2:
                    //isso só pode acontecer no estado "Em Entrega"
                    //tirar daqui
                    System.out.println("Você precisa estar no estado em entrega para confirmar a entrega");
                    /*
                    if(!e.mochila.isEmpty()){
                        System.out.println("Digite o id do pedido que deseja notificar a entrega:");
                        e.listaMochila();
                        Integer k2 = -1;
                        try {
                            k2 = Integer.valueOf(in.readLine());
                        } catch (IOException ex) {
                            Logger.getLogger(EntregadorIdle.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(k2>=0){
                            Pedido p2 = e.mochila.get(k2);
                            e.transicao(new Evento(aceitaPedidoDeEntrega,String.valueOf(p2.portaRestaurante),String.valueOf(p2.idPedido),String.valueOf(e.portaEntregador)));
                        }
                    }else{
                        System.out.println("Nada para aceitar...");
                    }*/
                    break;
                case 3:
                    e.listaPedidosdeEntrega();
                    break;
                case 4:
                    e.listaMochila();
                    break;
                    
                default:
                    
            }
            try {
                System.out.println("\nPrecione enter para continuar...");
                aux = in.readLine();
            } catch (IOException ex) {
                Logger.getLogger(EntregadorIdle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
