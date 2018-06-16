/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import framework.Entidade;
import framework.Estado;

/**
 *
 * @author lucas
 */
public class Sistema extends Entidade {
    public Estado idle;
    
    public Sistema(int lPort){
        super(lPort);
        
        idle = new SistemaIdle(this);
        mudaEstado(idle);
    }
}
