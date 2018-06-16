/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restaurante;

import framework.Entidade;
import framework.Estado;

/**
 *
 * @author lucas
 */
public class Restaurante extends Entidade{
    public Estado idle;
    public int id;
    
    public Restaurante(int lPort){
        super(lPort);
        
        idle = new RestauranteIdle(this);
        mudaEstado(idle);
        
    }
}
