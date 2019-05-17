package br.edu.puccampinas.servidor.musica.bd;

public class BDSQLServer {
  public static final MeuPreparedStatement COMANDO;

  static {
    MeuPreparedStatement comando = null;

    try {
      comando = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver",
          "jdbc:sqlserver://localhost:1433;databasename=projetoc", "sa", "Haple19761017");
    } catch (Exception erro) {
      System.err.println("Problemas de conexao com o BD");
      System.exit(0); // aborta o programa
    }

    COMANDO = comando;
  }
}
