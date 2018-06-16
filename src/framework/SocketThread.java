/*
 * FRAMEWORK:
 * Essa classe captura o comportamento comum no
 * processo de desempacotar as PDU's do protocolo.
 */
package framework;
public class SocketThread implements Runnable {
    public Msg ms;
    public Entidade ent;
    public String tmp;
    public Boolean continua;
    public SocketThread(Msg _m, Entidade _u){
        ent=_u;
        ms=_m;
        continua=true;
    }
    @Override
    public void run(){
        String [] split;
        int code;
        String n;
        String m;
            while (continua) {
                tmp=null;
                System.out.println("Esperando mensagem na porta "+ms.lPort); 
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
    public void para(){
        continua=false;
    }
    public void desempacota(){
        System.out.println("O metodo desempacota, na classe SimpleThread_r deve ser especializado.");
    }
}
