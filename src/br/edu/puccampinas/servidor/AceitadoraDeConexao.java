package br.edu.puccampinas.servidor;

import java.net.ServerSocket;

public class AceitadoraDeConexao extends Thread {
  private ServerSocket pedido;

  public AceitadoraDeConexao(int porta) throws Exception {
    try {
      this.pedido = new ServerSocket(porta);
    } catch (Exception erro) {
      throw new Exception("Porta invalida");
    }
  }

  /**
   * Recebe novas requisições, direcionando uma thread para cada uma.
   */
  public void run() {
    for (;;) {
      try {
        new TratadorDeRequisicao(this.pedido.accept()).start();
      } catch (Exception erro) {
        erro.printStackTrace();
      }
    }
  }
}
