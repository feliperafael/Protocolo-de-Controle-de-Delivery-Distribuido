/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restaurante;

import framework.Entidade;
import framework.Estado;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class Restaurante extends Entidade{
    public Estado idle;
    public int id;
    public ArrayList<Pedido> prontos;
    public ArrayList<Pedido> entregues;
    
    public Restaurante(int lPort){
        super(lPort);
        
        prontos = new ArrayList<>();
        entregues = new ArrayList<>();
        
        msg.conecta("localhost", 9000);
        idle = new RestauranteIdle(this);
        mudaEstado(idle);
        
    }
    
    public void gerarPedido(int id){
        Pedido p = new Pedido(this.id,id);
        prontos.add(p);
    }
    
    public void fecharPedidoEntregue(int id){
        for(int i = 0; i < prontos.size(); i++){
            if(prontos.get(i).idPedido == id){
                Pedido removido = prontos.get(i);
                entregues.add(removido);
                prontos.remove(i);
            }
        }
    }
    
}
