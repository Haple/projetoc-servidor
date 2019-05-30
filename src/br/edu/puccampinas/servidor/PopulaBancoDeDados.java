//package br.edu.puccampinas.servidor;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.Arrays;
//
//import br.edu.puccampinas.servidor.musica.bd.BDSQLServer;
//
//public class PopulaBancoDeDados {
//
//  public static void main(String[] args) {
//
//    String sql = "INSERT INTO MUSICAS " + " (TITULO,CANTOR,ESTILO,PRECO,DURACAO)"
//        + " VALUES (?, ?, ?, ?, ?);";
//    try (BufferedReader br =
//        new BufferedReader(new FileReader("/home/aleph/Downloads/PopularBancoDeDados.csv"))) {
//
//      String line;
//      while ((line = br.readLine()) != null) {
//        String[] values = line.split(";");
//        System.out.println(Arrays.toString(values));
//        BDSQLServer.COMANDO.prepareStatement(sql);
//        BDSQLServer.COMANDO.setString(1, values[0]);
//        BDSQLServer.COMANDO.setString(2, values[1]);
//        BDSQLServer.COMANDO.setString(3, values[2]);
//        BDSQLServer.COMANDO.setFloat(4, Float.parseFloat(values[3]));
//        BDSQLServer.COMANDO.setInt(5, Integer.parseInt(values[4]));
//        BDSQLServer.COMANDO.execute();
//        BDSQLServer.COMANDO.commit();
//      }
//    } catch (SQLException | IOException e) {
//      e.printStackTrace();
//    }
//
//  }
//
//}
