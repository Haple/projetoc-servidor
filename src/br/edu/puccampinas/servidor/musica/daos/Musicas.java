package br.edu.puccampinas.servidor.musica.daos;

import java.sql.SQLException;

import br.edu.puccampinas.servidor.estruturas.Lista;
import br.edu.puccampinas.servidor.musica.bd.BDSQLServer;
import br.edu.puccampinas.servidor.musica.bd.MeuResultSet;
import br.edu.puccampinas.servidor.musica.dbos.Musica;



public class Musicas {

  public static Lista<Musica> getMusicas(String chaveDeBusca) throws Exception {

    MeuResultSet resultado = null;
    Lista<Musica> musicas = new Lista<Musica>();
    try {
      String sql;

      sql = "SELECT * FROM MUSICAS" + " WHERE TITULO = ? OR CANTOR = ? OR ESTILO = ?";
      BDSQLServer.COMANDO.prepareStatement(sql);
      BDSQLServer.COMANDO.setString(1, chaveDeBusca);
      BDSQLServer.COMANDO.setString(2, chaveDeBusca);
      BDSQLServer.COMANDO.setString(3, chaveDeBusca);
      resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
      if (!resultado.first())
        throw new Exception("Nada encontrado");
      do {
        musicas.insereItem(new Musica(resultado.getInt("CODIGO"), resultado.getString("TITULO"),
            resultado.getString("CANTOR"), resultado.getString("ESTILO"),
            resultado.getFloat("PRECO"), resultado.getInt("DURACAO")));
      } while (resultado.next());
    } catch (SQLException erro) {
      throw new Exception("Erro ao recuperar músicas");
    }

    return musicas;
  }

  public static Lista<Musica> getMusicasByTitulo(String titulo) throws Exception {

    MeuResultSet resultado = null;
    Lista<Musica> musicas = new Lista<Musica>();
    try {
      String sql;

      sql = "SELECT * FROM MUSICAS" + " WHERE TITULO = ?";
      // + " AND (?2 IS NULL OR ?2 = '' OR CANTOR = ?2)"
      // + " AND (?3 IS NULL OR ?3 = '' OR ESTILO = ?3)";

      BDSQLServer.COMANDO.prepareStatement(sql);
      BDSQLServer.COMANDO.setString(1, titulo);
      resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
      if (!resultado.first())
        throw new Exception("Nada encontrado");
      do {
        musicas.insereItem(new Musica(resultado.getInt("CODIGO"), resultado.getString("TITULO"),
            resultado.getString("CANTOR"), resultado.getString("ESTILO"),
            resultado.getFloat("PRECO"), resultado.getInt("DURACAO")));
      } while (resultado.next());
    } catch (SQLException erro) {
      throw new Exception("Erro ao recuperar músicas");
    }

    return musicas;
  }

  public static Lista<Musica> getMusicasByCantor(String cantor) throws Exception {

    MeuResultSet resultado = null;
    Lista<Musica> musicas = new Lista<Musica>();
    try {
      String sql;

      sql = "SELECT * FROM MUSICAS" + " WHERE CANTOR = ?";
      // + " AND (?2 IS NULL OR ?2 = '' OR CANTOR = ?2)"
      // + " AND (?3 IS NULL OR ?3 = '' OR ESTILO = ?3)";

      BDSQLServer.COMANDO.prepareStatement(sql);
      BDSQLServer.COMANDO.setString(1, cantor);
      resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
      if (!resultado.first())
        throw new Exception("Nada encontrado");
      do {
        musicas.insereItem(new Musica(resultado.getInt("CODIGO"), resultado.getString("TITULO"),
            resultado.getString("CANTOR"), resultado.getString("ESTILO"),
            resultado.getFloat("PRECO"), resultado.getInt("DURACAO")));
      } while (resultado.next());
    } catch (SQLException erro) {
      throw new Exception("Erro ao recuperar músicas");
    }

    return musicas;
  }

  public static Lista<Musica> getMusicasByEstilo(String estilo) throws Exception {

    MeuResultSet resultado = null;
    Lista<Musica> musicas = new Lista<Musica>();
    try {
      String sql;

      sql = "SELECT * FROM MUSICAS" + " WHERE ESTILO = ?";
      // + " AND (?2 IS NULL OR ?2 = '' OR CANTOR = ?2)"
      // + " AND (?3 IS NULL OR ?3 = '' OR ESTILO = ?3)";

      BDSQLServer.COMANDO.prepareStatement(sql);
      BDSQLServer.COMANDO.setString(1, estilo);
      resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
      if (!resultado.first())
        throw new Exception("Nada encontrado");
      do {
        musicas.insereItem(new Musica(resultado.getInt("CODIGO"), resultado.getString("TITULO"),
            resultado.getString("CANTOR"), resultado.getString("ESTILO"),
            resultado.getFloat("PRECO"), resultado.getInt("DURACAO")));
      } while (resultado.next());
    } catch (SQLException erro) {
      throw new Exception("Erro ao recuperar músicas");
    }

    return musicas;
  }
}
