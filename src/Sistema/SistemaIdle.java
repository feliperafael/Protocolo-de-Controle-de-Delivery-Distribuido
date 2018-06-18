/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import framework.Entidade;
import framework.Estado;
import framework.Evento;

/**
 *
 * @author lucas
 */
public class SistemaIdle extends Estado{
    Sistema s;
    
    public static final int cadastraRestaurante = 0;
    public static final int recebePedidoDeEntrega = 3;    
    public static final int divulgaPedidoDeEntrega = 4;
    public static final int recebeConfirmacaoDePedidoDeEntrega = 5;
    public static final int recebeConfirmacaoDeEntrega = 6;
    public static final int enviaConfirmacaoDePedidoDeEntrega = 7;
    public static final int enviaConfirmacaoDeEntrega = 8;
    public static final int cadastraEntregador = 9;
    
    
    public SistemaIdle(Entidade e){
        super(e);
        s = (Sistema)e; //Pra facilitar na função transição
    }
    
    @Override
    public void transicao(Evento ev){
        int portaRestaurante;
        switch(ev.codigo){
            case cadastraRestaurante:
                portaRestaurante = Integer.valueOf(ev.portaRestaurante);
                int teste = s.cadastraRestaurante(portaRestaurante);
                if(teste != -1){
                    System.out.println("Restaurante com a porta " + portaRestaurante + " cadastrado com sucesso.");
                    
                    Evento e = new Evento(0,String.valueOf(portaRestaurante),"-1","-1"); // Apenas para cadastro
                    s.msg.conecta("localhost", portaRestaurante);
                    s.msg.envia(e.toString());
                    s.msg.termina();
                    
                }else
                    System.out.println("Restaurante já cadastrado.");
                //s.mudaEstado(this);
                break;
            case recebePedidoDeEntrega:
                portaRestaurante = Integer.valueOf(ev.portaRestaurante);
                int idPedido = Integer.valueOf(ev.idPedido);
                s.adicionarPedidoOfertado(portaRestaurante,idPedido);
                System.out.println("Pedido com id -> " + idPedido + " | Restaurante porta -> " + portaRestaurante + " cadastrado no sistema.\n" );
                //Evento ev = ;
                s.transicao(new Evento(4,String.valueOf(ev.portaRestaurante),String.valueOf(ev.idPedido),"-1"));
           
                break;
            case divulgaPedidoDeEntrega:
                System.out.println("******** DivulgaPedidoDeEntrega **********");
                s.pedidosOfertados.forEach((p) -> {
                    s.entregadores.forEach((entregadorAtual) -> {
                        //envia mensagem para o entregador
                        Evento e = new Evento(3,String.valueOf(p.portaRestaurante),String.valueOf(p.idPedido),"-1");
                        System.out.println("PORTA ENTREGADOR: "+String.valueOf(entregadorAtual.portaEntregador));
                        /// Envia a Menssagem
                        s.msg.conecta("localhost", (entregadorAtual.portaEntregador)); 
                        s.msg.envia(e.toString());
                        s.msg.termina();
                    });
                });
                
                break;
            case recebeConfirmacaoDePedidoDeEntrega:
                System.out.println("_______________recebeConfirmacaoDePedidoDeEntrega______________");
                break;
            case recebeConfirmacaoDeEntrega:
               
                break;
            case enviaConfirmacaoDePedidoDeEntrega:
                
                break;
            case enviaConfirmacaoDeEntrega:
                
                break;
            case cadastraEntregador:
                int portaEntregador = Integer.valueOf(ev.portaEntregador);
                int teste2 = s.cadastraEntregador(portaEntregador);
                if(teste2 != -1){
                    System.out.println("Entregador com a porta " + portaEntregador + " cadastrado com sucesso.");
                    
                    Evento ev_2 = new Evento(Entregador.EntregadorIdle.cadastroEntregador,"-1","-1",String.valueOf(portaEntregador)); // 
                    System.out.println("Repondendo ao entregador");
                    System.out.println(ev_2.toString());
                    s.msg.conecta("localhost", portaEntregador);
                    s.msg.envia(ev_2.toString());
                    s.msg.termina();
                }else
                    System.out.println("Entregador já cadastrado.");
                
                s.mudaEstado(this);
                break;
            default:
        }
    }
}
