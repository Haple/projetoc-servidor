package br.edu.puccampinas.servidor;

import java.net.ServerSocket;

/**
 * Responsável por receber e separar em threads individuais cada nova transmissão de dados.
 * 
 * @author aleph
 *
 */
public class AceitadoraDeConexao extends Thread {
  private ServerSocket pedido;

  /**
   * Cria uma aceitadora de conexão.
   * 
   * Torna um programa capaz de receber mensagens pela rede através de uma porta do sistema.
   * 
   * @param porta indica a porta do sistema em que o programa vai receber mensagens.
   * @throws Exception caso a porta seja inválida, ou seja, já esteja sendo usada por outro programa
   */
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AceitadoraDeConexao other = (AceitadoraDeConexao) obj;
    if (pedido == null) {
      if (other.pedido != null)
        return false;
    } else if (!pedido.equals(other.pedido))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "AceitadoraDeConexao [pedido=" + pedido + "]";
  }

}
