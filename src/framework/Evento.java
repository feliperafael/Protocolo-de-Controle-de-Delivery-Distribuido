/*
 * FRAMEWORK:
 * Classe que implementa o conceito de EVENTO.
 */
package framework;

public class Evento {
    public int codigo;
    public String portaRestaurante;
    public String idPedido;
    public String portaEntregador;
    public Evento(int _c, String _n, String _m, String _p){
        codigo = _c;
        portaRestaurante = _n;
        idPedido = _m;
        portaEntregador = _p;
    }
    
    @Override
    public String toString(){
        return String.valueOf(codigo) + "," + portaRestaurante + "," + idPedido + "," + portaEntregador;    
    }
}
