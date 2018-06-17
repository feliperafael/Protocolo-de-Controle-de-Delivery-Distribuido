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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        idle = new RestauranteIdle(this);
        mudaEstado(idle);
        
        
    }
    
 
    public Pedido gerarPedido(int idPedido){
        try{
            Pedido p = new Pedido(msg.lPort,idPedido);
            prontos.add(p);
            return p;
        } 
        catch(Exception ex){
            Logger.getLogger(RestauranteIdle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
