/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restaurante;

/**
 *
 * @author lucas
 */
public class Pedido {
    public int portaRestaurante;
    public int idEntregador;
    public int idPedido;
    public int status; //1 - "em entrega", 2 - "entregue", 3 - "aguardando entregador"
    
    public Pedido(int portaRestaurante, int idPedido){
        this.portaRestaurante = portaRestaurante;
        this.idPedido = idPedido;
    }

    public Pedido() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
