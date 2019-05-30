package br.edu.puccampinas.servidor.musica.bd;

/**
 * Cria a uma com o banco de dados.
 * 
 * Essa conexão é compartilhada entre todas as requisições recebidas pelo servidor.
 * 
 * @author aleph
 *
 */
public class BDSQLServer {
  public static final MeuPreparedStatement COMANDO;

  static {
    MeuPreparedStatement comando = null;

    try {
      comando = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver",
          "jdbc:sqlserver://localhost:1433;databasename=projetoc", "sa", "UmaSenhaMuitoBoa");
    } catch (Exception erro) {
      System.err.println("Problemas de conexao com o BD");
      System.exit(0); // aborta o programa
    }

    COMANDO = comando;
  }
}
