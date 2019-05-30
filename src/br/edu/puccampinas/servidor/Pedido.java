package br.edu.puccampinas.servidor;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import br.edu.puccampinas.comum.Comunicado;

/**
 * Classe responsável por centralizar os recursos de uma conexão socket.
 * 
 * @author aleph
 *
 */
public class Pedido implements Closeable {
  private Socket conexao;
  private ObjectInputStream receptor;
  private ObjectOutputStream transmissor;

  /**
   * Cria um novo Pedido.
   * 
   * Valida se uma conexão é válida e instancia os objetos da transmissão e recepção.
   * 
   * @param conexao uma nova conexão socket
   * @throws Exception caso a conexão seja nula
   */
  public Pedido(Socket conexao) throws Exception {
    if (conexao == null)
      throw new Exception("Conexao ausente");
    this.conexao = conexao;
    this.transmissor = new ObjectOutputStream(conexao.getOutputStream());
    this.receptor = new ObjectInputStream(conexao.getInputStream());
  }

  /**
   * Envia um novo Comunicado.
   * 
   * Valida se o parâmetro recebido é um comunicado válido.
   * 
   * @param comunicado Comunicado a ser transmitido
   * @throws Exception Caso o Comunicado esteja nulo ou exista um erro na transmissão do comunicado
   */
  public void responder(Comunicado comunicado) throws Exception {
    if (comunicado == null) {
      throw new Exception("Comunicado inválido");
    }
    try {
      this.transmissor.writeObject(comunicado);
      this.transmissor.flush();
    } catch (Exception erro) {
      throw new RuntimeException("Erro de transmissao");
    }
  }

  /**
   * 
   * @return Devolve um Comunicado recebido de uma transmissão
   * @throws RuntimeException Caso não seja possível receber o Comunicado da transmissão
   */
  public Comunicado receber() throws Exception {
    try {
      return (Comunicado) this.receptor.readObject();
    } catch (Exception erro) {
      throw new RuntimeException("Erro de recepcao");
    }
  }

  /**
   * Encerra essa transmissão e libera qualquer recurso associado com ela.
   * 
   * Se a transmissão já está fechada, então chamar esse método não tem efeito
   * 
   * @throws IOException - se um erro de entrada/saída acontecer
   */
  @Override
  public void close() throws IOException {
    try {
      this.receptor.close();
      this.transmissor.close();
      this.conexao.close();
    } catch (Exception erro) {
      throw new IOException("Erro ao fechar conexão");
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((conexao == null) ? 0 : conexao.hashCode());
    result = prime * result + ((receptor == null) ? 0 : receptor.hashCode());
    result = prime * result + ((transmissor == null) ? 0 : transmissor.hashCode());
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
    Pedido other = (Pedido) obj;
    if (conexao == null) {
      if (other.conexao != null)
        return false;
    } else if (!conexao.equals(other.conexao))
      return false;
    if (receptor == null) {
      if (other.receptor != null)
        return false;
    } else if (!receptor.equals(other.receptor))
      return false;
    if (transmissor == null) {
      if (other.transmissor != null)
        return false;
    } else if (!transmissor.equals(other.transmissor))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Pedido [conexao=" + conexao + ", receptor=" + receptor + ", transmissor=" + transmissor
        + "]";
  }

}
