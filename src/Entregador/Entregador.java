/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entregador;

import framework.Entidade;
import framework.Estado;

/**
 *
 * @author lucas
 */
public class Entregador extends Entidade {
    public Estado idle;
    public Estado ativo;
    public int id;
    
    public Entregador(int lPort){
        super(lPort);
        
        idle = new EntregadorIdle(this);
        ativo = new EntregadorAtivo(this);
        
        mudaEstado(idle);
    }
}
