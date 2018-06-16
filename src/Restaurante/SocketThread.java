package Restaurante;

import framework.Entidade;
import framework.Evento;
import framework.Msg;

public class SocketThread extends framework.SocketThread {
    //int idRecurso;
    //int portaProcesso;
    //int codigo;
    
    public SocketThread(Msg _m, Entidade _u){
        super(_m, _u);
    }
    @Override
    public void desempacota(){
        /// Desempacota Mensagem
        
        //Evento e = new Evento(codigo, idRecurso, portaProcesso); /// Cria Evento
        //ent.colocaEvento(e); /// Coloca no Buffer da Entidade
    }
}
