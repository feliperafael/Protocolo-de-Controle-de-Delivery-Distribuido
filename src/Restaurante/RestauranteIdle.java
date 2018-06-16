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
public class RestauranteIdle extends Estado{
    Restaurante r;
    
    public static final int cadastroRestaurante = 0;
    public static final int solicitaEntrega = 1;    
    public static final int desalocaPedido  = 2;    
    
    public RestauranteIdle(Entidade e){
        super(e);
        r = (Restaurante)e; //Pra facilitar na função transição
    }
    
    @Override
    public void transicao(Evento ev){
        switch(ev.code){
            case cadastroRestaurante:
                
                break;
            case solicitaEntrega:
                
                break;
            case desalocaPedido:
                
                break;
            default:
        }
    }
}
