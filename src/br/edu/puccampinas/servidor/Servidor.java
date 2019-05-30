package br.edu.puccampinas.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Classe principal.
 * 
 * Inicia o servidor com uma porta personalizada ou uma porta padrão.
 *
 * @author aleph
 *
 */
public class Servidor {

  private static BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

  /**
   * Recebe como argumento uma porta customizada para iniciar o servidor
   * 
   * @param args argumentos recebidos ao iniciar o programa
   */
  public static void main(String[] args) {
    if (args.length > 1) {
      System.err.println("Uso esperado: java Servidor [PORTA]\n");
      return;
    }
    int porta = 12345;
    if (args.length == 1)
      porta = Integer.parseInt(args[0]);
    try {
      new AceitadoraDeConexao(porta).start();
    } catch (Exception erro) {
      System.err.println("Escolha uma porta liberada para uso!\n");
      return;
    }
    String comando = "";
    for (;;) {
      System.out.println("O servidor esta ativo! Para desativa-lo,");
      System.out.println("use o comando \"desativar\"\n");
      System.out.print("> ");
      try {
        comando = teclado.readLine();
      } catch (IOException erro) {
      }
      if (comando.toLowerCase().equals("desativar")) {
        System.out.println("O servidor foi desativado!\n");
        System.exit(0);
      } else
        System.err.println("Comando invalido!\n");
    }

  }
}
