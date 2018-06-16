package Sistema;

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
    
    @Override
    public void run(){
        String [] split;
        int code;
        String n;
        String m;
            while (continua) {
                tmp=null;
                ms.conecta(0);
                tmp = ms.recebe(); 
                ms.fecha_leitura();
                System.out.println("Mensagem recebida: "+tmp);
                if (tmp!=null){
                   desempacota();
                }
                else
                    break;
            }
    }
}
