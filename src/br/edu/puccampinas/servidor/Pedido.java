package br.edu.puccampinas.servidor;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import br.edu.puccampinas.comum.Comunicado;

public class Pedido implements Closeable {
  private Socket conexao;
  private ObjectInputStream receptor;
  private ObjectOutputStream transmissor;

  public Pedido(Socket conexao) throws Exception {
    if (conexao == null)
      throw new Exception("Conexao ausente");
    this.conexao = conexao;
    this.transmissor = new ObjectOutputStream(conexao.getOutputStream());
    this.receptor = new ObjectInputStream(conexao.getInputStream());
  }

  public void responder(Comunicado x) throws RuntimeException {
    try {
      this.transmissor.writeObject(x);
      this.transmissor.flush();
    } catch (IOException erro) {
      throw new RuntimeException("Erro de transmissao");
    }
  }

  public Comunicado receber() throws RuntimeException {
    try {
      return (Comunicado) this.receptor.readObject();
    } catch (Exception erro) {
      throw new RuntimeException("Erro de recepcao");
    }
  }

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
}
