/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import Restaurante.Restaurante;
import framework.Entidade;
import framework.Estado;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class Sistema extends Entidade {
    public Estado idle;
    public ArrayList<Restaurante> restaurantes;
    
    
    public Sistema(int lPort){
        super(lPort);
        
        idle = new SistemaIdle(this);
        mudaEstado(idle);
    }
    
    public int cadastraRestaurante(int porta){
        if(!verificaRestaurante(porta)){
            Restaurante r = new Restaurante(porta);
            restaurantes.add(r);
            return restaurantes.indexOf(r); // retorna posição de cadastro = ID
        }
        return -1;// Em caso de restaurante ja cadastrado no sistema
    }
    
    public boolean verificaRestaurante(int porta){
        for(Restaurante r : restaurantes){
            if(r.msg.lPort == porta){
                return true;
            }
        }
        return false;
    }
}
