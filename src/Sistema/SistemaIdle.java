/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import Entregador.Entregador;
import Restaurante.Pedido;
import framework.Entidade;
import framework.Estado;
import framework.Evento;

/**
 *
 * @author lucas
 */
public class SistemaIdle extends Estado{
    Sistema s;
    
    
    public SistemaIdle(Entidade e){
        super(e);
        s = (Sistema)e; //Pra facilitar na função transição
    }
    
    @Override
    public void transicao(Evento ev){
        int portaRestaurante;
        switch(ev.codigo){
            case Sistema.cadastraRestaurante:
                portaRestaurante = Integer.valueOf(ev.portaRestaurante);
                int teste = s.cadastraRestaurante(portaRestaurante);
                if(teste != -1){
                    System.out.println("Restaurante com a porta " + portaRestaurante + " cadastrado com sucesso.");
                    
                    Evento e = new Evento(Restaurante.Restaurante.cadastroRestaurante,String.valueOf(portaRestaurante),"-1","-1"); // Apenas para cadastro
                    s.msg.conecta("localhost", portaRestaurante);
                    s.msg.envia(e.toString());
                    s.msg.termina();
                    
                }else
                    System.out.println("Restaurante já cadastrado.");
                //s.mudaEstado(this);
                break;
            case Sistema.recebePedidoDeEntrega:
                portaRestaurante = Integer.valueOf(ev.portaRestaurante);
                int idPedido = Integer.valueOf(ev.idPedido);
                s.adicionarPedidoOfertado(portaRestaurante,idPedido);
                System.out.println("Pedido com id -> " + idPedido + " | Restaurante porta -> " + portaRestaurante + " cadastrado no sistema.\n" );
                //Evento ev = ;
                s.transicao(new Evento(Sistema.divulgaPedidoDeEntrega,String.valueOf(ev.portaRestaurante),String.valueOf(ev.idPedido),"-1"));
           
                break;
            case Sistema.divulgaPedidoDeEntrega:
                //todos que ainda não tem entregador vinculado
                System.out.println("******** DivulgaPedidoDeEntrega **********");
                
                for(Pedido p : s.pedidosOfertados){
                    if(p.portaEntregador < 1){
                        for(Entregador entregadorAtual : s.entregadores){
                            //envia mensagem para o entregador
                            Evento e = new Evento(Entregador.recebePedidoDeEntrega,String.valueOf(p.portaRestaurante),String.valueOf(p.idPedido),"-1");
                            System.out.println("PORTA ENTREGADOR: "+String.valueOf(entregadorAtual.portaEntregador));
                            /// Envia a Menssagem
                            int fracasso;
                            do{
                                fracasso = s.msg.conecta("localhost", (entregadorAtual.portaEntregador)); 
                                s.msg.envia(e.toString());
                                s.msg.termina();
                            }while(fracasso == 1);
                        }
                    }
                }
                
                break;
            case Sistema.recebeConfirmacaoDePedidoDeEntrega:
                System.out.println("_______________recebeConfirmacaoDePedidoDeEntrega______________");
                if(s.associarEntregadorPedido(Integer.valueOf(ev.portaRestaurante), Integer.valueOf(ev.idPedido), Integer.valueOf(ev.portaEntregador))){
                    System.out.println("Associado com sucesso");
                    //respode para entregador avisando que ele conseguiu aceitar
                    Evento ev_3 = new Evento(Entregador.confirmacaoDeAceite,String.valueOf(ev.portaRestaurante),String.valueOf(ev.idPedido),ev.portaEntregador);
                   
                    /// Envia a Menssagem
                    s.msg.conecta("localhost", Integer.valueOf(ev.portaEntregador)); 
                    s.msg.envia(ev_3.toString());
                    s.msg.termina();
                }else{
                    System.out.println("alguem já aceitou essa entrega");
                    //responde para o entregador avisando que a entrega não está disponivel
                    Evento ev_3 = new Evento(Entregador.erroPedidoJaAceito,ev.portaRestaurante,ev.idPedido,ev.portaEntregador);

                    /// Envia a Menssagem
                    s.msg.conecta("localhost", Integer.valueOf(ev.portaEntregador)); 
                    s.msg.envia(ev_3.toString());
                    s.msg.termina();
                }
                break;
            case Sistema.cadastraEntregador:
                int portaEntregador = Integer.valueOf(ev.portaEntregador);
                int teste2 = s.cadastraEntregador(portaEntregador);
                if(teste2 != -1){
                    System.out.println("Entregador com a porta " + portaEntregador + " cadastrado com sucesso.");
                    
                    Evento ev_2 = new Evento(Entregador.cadastroEntregador,"-1","-1",String.valueOf(portaEntregador)); // 
                    //System.out.println("Repondendo ao entregador");
                    //System.out.println(ev_2.toString());
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
