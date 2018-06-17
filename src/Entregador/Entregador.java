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
    
    public Entregador(int lPort){
        super(lPort);
        portaEntregador = lPort;
        idle = new EntregadorIdle(this);
        ativo = new EntregadorAtivo(this);
        
        mudaEstado(idle);
    }
    
    private Boolean aceitaPedido(Pedido p){
        //envia mensagem para o sistema dizendo que quer aceitar o pedido
        //se conseguir aceitar o pedido retorna true
        return false;
    }
    
    private void RecebePedidoDeEntrega(String pacote_pedido){
        //desempacota(pacote_pedido);
        int PortaRestaurante = 0;
        int idPedido = 0;
        
        // aqui deve setar a PortadoRestaurante e o Id do pedido desempacotado
        
        Pedido p = new Pedido(PortaRestaurante, idPedido);
        pedidos_de_entrega.add(p);
    }
    
    private void SinalizaEntregaDePedido(Pedido p){
        int PortaRestaurante = p.portaRestaurante;
        int IdPedido = p.idPedido;
        //envia mensagem de resposta de restaurante
    }
    
    private void LimpaBufferDePedidosDeEntrega(){
        pedidos_de_entrega.clear();
    }
    
    public void listaPedidosdeEntrega(){
        System.out.println("------ Pedidos de Entrega -------");
        pedidos_de_entrega.forEach((p) -> {
            System.out.println("idPedido: "+p.idPedido+ "| idRestaurante: "+p.portaRestaurante);
        });
        
    }
    
    public void listaMochila(){
        System.out.println("------ Mochila -------");
        mochila.forEach((p) -> {
            System.out.println("idPedido: "+p.idPedido+ "| idRestaurante: "+p.portaRestaurante);
        });
        
    }
    
    public void listaMenu(){
        System.out.println("\nOpções:\n 1 - Aceitar entrega de pedido (Ex.: idPedido,idRestaurante)\n 2 - Notificar entrega de pedido(Ex.: idPedido,idRestaurante)\n");
        System.out.println("3 - Listar pedidos de entrega\n4 - Listar itens na mochila");
    }
}
