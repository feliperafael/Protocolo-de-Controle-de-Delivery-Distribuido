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
/**
 *
 * @author lucas
 */
public class Entregador extends Entidade {
    public Estado idle;
    public Estado ativo;
    public int id;
    public ArrayList<Pedido> mochila; // pedidos que ainda não foram entregues
    public ArrayList<Pedido> pedidos_de_entrega; // buffer requisição de entregas vindas do sistema
    
    public Entregador(int lPort){
        super(lPort);
        
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
}
