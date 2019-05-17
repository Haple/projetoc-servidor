package br.edu.puccampinas.servidor;

import br.edu.puccampinas.servidor.utils.Teclado;

public class Servidor {
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
      comando = Teclado.getUmString();
      if (comando.toLowerCase().equals("desativar")) {
        System.out.println("O servidor foi desativado!\n");
        System.exit(0);
      } else
        System.err.println("Comando invalido!\n");
    }

  }
}
