package br.edu.puccampinas.servidor.musica.daos;

import java.sql.SQLException;

import br.edu.puccampinas.servidor.estruturas.Lista;
import br.edu.puccampinas.servidor.musica.bd.BDSQLServer;
import br.edu.puccampinas.servidor.musica.bd.MeuResultSet;
import br.edu.puccampinas.servidor.musica.dbos.Musica;


/**
 * Classe responsável por expor os métodos de busca de músicas.
 * 
 * @author aleph
 *
 */
public class Musicas {


  /**
   * Busca músicas de acordo com a chave de busca.
   * 
   * 
   * @param chaveDeBusca título, cantor ou estilo da música desejada
   * @return seram retornadas todas as músicas em que o título, cantor ou estilo sejam iguais à
   *         chave de busca
   * @throws Exception Caso não seja encontrada nenhuma música ou ocorra um erro de conexão com o
   *         banco
   */
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
}
