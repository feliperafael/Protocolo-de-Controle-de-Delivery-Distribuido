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
import Entregador.Entregador;
import Restaurante.Pedido;
import framework.Evento;

/**
 *
 * @author lucas
 */
public class Sistema extends Entidade {
    public Estado idle;
    public ArrayList<Restaurante> restaurantes;
    public ArrayList<Entregador> entregadores;
    public ArrayList<Pedido> pedidosOfertados;
    
    
    public Sistema(int lPort){
        super(lPort);
        restaurantes = new ArrayList<>();
        entregadores = new ArrayList<>();
        pedidosOfertados = new ArrayList<>();
        
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
        if(restaurantes.size() == 0)
            return false;
        for(Restaurante r : restaurantes){
            if(r.msg.lPort == porta){
                return true;
            }
        }
        return false;
    }
    
    public int cadastraEntregador(int porta){
        if(!verificaEntregador(porta)){
            Entregador r = new Entregador(porta);
            entregadores.add(r);
            return entregadores.indexOf(r); // retorna posição de cadastro = ID
        }
        return -1;// Em caso de restaurante ja cadastrado no sistema
    }
    
    public boolean verificaEntregador(int porta){
        if(entregadores.isEmpty())
            return false;
        for(Entregador r : entregadores){
            if(r.msg.lPort == porta){
                return true;
            }
        }
        return false;
    }
    
    public void adicionarPedidoOfertado(int portaRestaurante,int idPedido){
        Pedido p = new Pedido(portaRestaurante,idPedido);
        p.portaEntregador = -1;
        pedidosOfertados.add(p);
        
        printPedidosOfertados();
    }
    
    private void printPedidosOfertados(){
        //print pedidos ofertados
        System.out.println("Pedidos ofertados -> ");
        pedidosOfertados.forEach((x) -> {
            System.out.println("idRestaurante: "+x.portaRestaurante+" idPedido: "+x.idPedido);
        });    
    }
    
    public void associarEntregadorPedido(int portaRestaurante,int idPedido,int portaEntregador){
        for(Pedido p : pedidosOfertados){
            if(p.portaRestaurante == portaRestaurante && p.idPedido == idPedido){
                p.portaEntregador = portaEntregador;
            }
        }
    }
    
    public void removerPedidoOfertado(int portaRestaurante,int idPedido,int portaEntregador ){
        for(Pedido p : pedidosOfertados){
            if(p.portaRestaurante == portaRestaurante && p.idPedido == idPedido && p.portaEntregador == portaEntregador){
                pedidosOfertados.remove(p);
            }
        }
    }
    /*
        envia os pedidos ofertados aos entregadores
    */
    public void divulgaPedidos(){
/*
        pedidosOfertados.forEach((p) -> {
            entregadores.forEach((entregadorAtual) -> {
                //envia mensagem para o entregador
                Evento e = new Evento(3,String.valueOf(p.portaRestaurante),String.valueOf(p.idPedido),String.valueOf(entregadorAtual.portaEntregador));
                                  
                /// Envia a Menssagem
                s.msg.conecta("localhost", 9000); 
                s.msg.envia(e.toString());
                s.msg.termina();
            });
        });
*/    
    }
}
