/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entregador;

import framework.Entidade;
import framework.Estado;
import java.util.ArrayList;
import Restaurante.Pedido;
import framework.Evento;
/**
 *
 * @author lucas
 */
public class Entregador extends Entidade {
    public Estado idle;
    public Estado ativo;
    public int portaEntregador;
    public ArrayList<Pedido> mochila; // pedidos que ainda não foram entregues
    public ArrayList<Pedido> pedidos_de_entrega; // buffer requisição de entregas vindas do sistema
    
    public static final int cadastraEntregador = 9;
    public static final int aceitaPedidoDeEntrega = 1;    
    public static final int notificaEntrega = 6;
    public static final int recebePedidoDeEntrega = 3;
    public static final int confirmacaoDeAceite = 4;
    public static final int erroPedidoJaAceito = 5;
    public static final int cadastroEntregador = 0;  
    
    public Entregador(int lPort){
        super(lPort);
        portaEntregador = lPort;
        idle = new EntregadorIdle(this);
        ativo = new EntregadorAtivo(this);
        
        //instancia array lists
        mochila = new ArrayList<>();
        pedidos_de_entrega = new ArrayList<>();
        
        mudaEstado(idle);
        
    }
    
    public int getIndicePedidoEntrega(int portaRestaurante,int idPedido){
        int i = 0;
        for(Pedido ped : pedidos_de_entrega){
            if(ped.idPedido == idPedido && ped.portaRestaurante == portaRestaurante){
                return i;
            }
            i++;
        }
        return -1;
    
    }
    
    public void removePedidoMochila(int portaRestaurante, int idPedido){
        int indice = -1;
        for(int i = 0; i < mochila.size(); i++){
            if(mochila.get(i).idPedido == idPedido && mochila.get(i).portaRestaurante == portaRestaurante){
                indice = i;
            }
        }
        mochila.remove(indice);
    }
    
    public void adicionaNaMochila(Pedido p){
        mochila.add(p);
    }
    //não esta sendo usada  
    public Boolean aceitaPedido(int idPedidoEntrega){
        //envia mensagem para o sistema dizendo que quer aceitar o pedido
        //se conseguir aceitar o pedido retorna true
        Pedido p = pedidos_de_entrega.get(idPedidoEntrega);
        return false;
    }
    
    public void RecebePedidoDeEntrega(Evento ev){
        //desempacota(pacote_pedido);
        int PortaRestaurante = Integer.valueOf(ev.portaRestaurante);
        int idPedido = Integer.valueOf(ev.idPedido);
        
        // aqui deve setar a PortadoRestaurante e o Id do pedido desempacotado
        
        Pedido p = new Pedido(PortaRestaurante, idPedido);
        if(!pedidoDeEntregaExiste(p))
            pedidos_de_entrega.add(p);
    }
    
    private boolean pedidoDeEntregaExiste(Pedido p){
        for(Pedido ped : pedidos_de_entrega){
            if(ped.idPedido == p.idPedido && ped.portaRestaurante== p.portaRestaurante){
                return true;
            }
        }
        return false;
    }
    
    public void SinalizaEntregaDePedido(Pedido p){
        int PortaRestaurante = p.portaRestaurante;
        int IdPedido = p.idPedido;
        //envia mensagem de resposta de restaurante
    }
    
    public void LimpaBufferDePedidosDeEntrega(){
        pedidos_de_entrega.clear();
    }
    
    public void listaPedidosdeEntrega(){
        System.out.println("------ Pedidos de Entrega -------");
        if(pedidos_de_entrega.isEmpty()){
            System.out.println("Vazia");
        }
        int i = 0;
        for(Pedido p : pedidos_de_entrega){
            System.out.println(" "+i+") idPedido: "+p.idPedido+ "| idRestaurante: "+p.portaRestaurante);
            i+=1;
        }
        
    }
    
    public void listaMochila(){
        System.out.println("------- Mochila --------");
        if(mochila.isEmpty()){
            System.out.println("Vazia");
        }
        int i = 0;
        for(Pedido p : mochila){
            System.out.println(" "+i+") idPedido: "+p.idPedido+ "| idRestaurante: "+p.portaRestaurante);
            i++;
        }
        
        
    }
    
    public void listaMenu(){
        System.out.println("\nOpções:\n 1 - Aceitar entrega de pedido (Ex.: idPedido,idRestaurante)\n 2 - Notificar entrega de pedido(Ex.: idPedido,idRestaurante)\n");
        System.out.println("3 - Listar pedidos de entrega\n4 - Listar itens na mochila");
    }
    
    public void printMenu(){
        System.out.println("----------------- Menu ------------------");
        System.out.println("- 1  Aceitar entrega de pedido          -");
        System.out.println("- 2  Notificar entrega de pedido        -");
        System.out.println("- 3  Listar pedidos de entrega          -");
        System.out.println("- 4  Listar itens na mochila            -");
        System.out.println("-                                       -");
        System.out.println("-                                       -");
        System.out.println("-----------------------------------------");
    }
}
