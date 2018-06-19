package Entregador;

import framework.Entidade;
import framework.Evento;
import framework.Msg;

public class SocketThread extends framework.SocketThread {
    String codigo;
    String portaRestaurante;
    String idPedido;
    String portaEntregador;
    
    public SocketThread(Msg _m, Entidade _u){
        super(_m, _u);
    }
    @Override
    public void desempacota(){
         /// Desempacota Mensagem
        String[] split;
        split = tmp.split(",");
        codigo        = split[0];
        portaRestaurante     = split[1];
        idPedido = split[2];
        portaEntregador = split[3];
        
        Evento e = new Evento(Integer.valueOf(codigo),portaRestaurante,idPedido,portaEntregador);
        ent.colocaEvento(e);
    }
}
