package br.edu.puccampinas.servidor;

import java.net.Socket;

import br.edu.puccampinas.comum.Comunicado;
import br.edu.puccampinas.servidor.estruturas.Lista;
import br.edu.puccampinas.servidor.musica.daos.Musicas;
import br.edu.puccampinas.servidor.musica.dbos.Musica;

public class TratadorDeRequisicao extends Thread {

  private Socket conexao;

  public TratadorDeRequisicao(Socket conexao) throws Exception {
    if (conexao == null)
      throw new Exception("Conexao ausente");
    this.conexao = conexao;
  }

  public void run() {
    try (Pedido requisicao = new Pedido(conexao)) {
      Comunicado comunicado = requisicao.receber();
      if (comunicado == null)
        return;
      switch (comunicado.getComando()) {
        case "COM":
          try {
            consultarMusicas(requisicao, comunicado);
          } catch (Exception e) {
            requisicao.responder(new Comunicado("ERR", e.getMessage()));
          }
          break;
        default:
          requisicao.responder(new Comunicado("INV"));// Comando inválido
          break;
      }
    } catch (Exception e) {
      System.err.println("Erro ao tratar requisição: " + e.getMessage());
    }
  }

  /**
   * Responde uma requisição de consulta de músicas com a lista de músicas
   * 
   * @param requisicao
   * @param comunicado
   * @throws Exception
   */
  private void consultarMusicas(Pedido requisicao, Comunicado comunicado) throws Exception {
    String chaveDeBusca = comunicado.getComplemento(0);
    if (chaveDeBusca == null)
      return;
    Lista<Musica> musicas = Musicas.getMusicas(chaveDeBusca);
    while (!musicas.isVazia()) {
      Musica m = musicas.getItem();
      requisicao.responder(
          new Comunicado("MUS", String.valueOf(m.getCodigo()), m.getTitulo(), m.getCantor(),
              m.getEstilo(), String.valueOf(m.getPreco()), String.valueOf(m.getDuracao())));
      musicas.removeItem();
    }
    requisicao.responder(new Comunicado("FIC"));
    requisicao.close();
  }

}
