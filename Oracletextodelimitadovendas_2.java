/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracletextodelimitadovendas_2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author oracle
 */
public class Oracletextodelimitadovendas_2 {

    public static Connection conexion = null;

    public static Connection getConexion() throws SQLException {
        String usuario = "hr";
        String password = "hr";
        String host = "localhost";
        String puerto = "1521";
        String sid = "orcl";
        String ulrjdbc = "jdbc:oracle:thin:" + usuario + "/" + password + "@" + host + ":" + puerto + ":" + sid;

        conexion = DriverManager.getConnection(ulrjdbc);
        return conexion;
    }

    public static void closeConexion() throws SQLException {
        conexion.close();
    }

    public static void main(String[] args) throws SQLException, IOException {

        PrintWriter pw = new PrintWriter(
                new BufferedWriter(
                        new FileWriter("/home/oracle/Desktop/totalvendas.txt")));
        getConexion();
        String sql = "SELECT * from vendas";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(sql);
        //RECOGEMOS TODO DE VENDAS
        while (rs.next()) {
            int nv = rs.getInt("nv");
            String codp = rs.getString("codp");
            int cantv = rs.getInt("cantv");
            String des = rs.getString("des");

            System.out.println("venda: " + nv + ", " + codp + ", " + cantv + ", " + des);

            sql = "SELECT * from stock where codp = ?";
            PreparedStatement pst2 = conexion.prepareStatement(sql);
            pst2.setString(1, codp);
            ResultSet rs2 = pst2.executeQuery();
            //RECOGEMOS TODO DE STOCK
            while (rs2.next()) {

                String nomp = rs2.getString("nomp");
                int cants = rs2.getInt("cants");

                System.out.println("nome produto: " + nomp + " ,stock antes: " + cants);

                //ACCESO A STOCK PARA QUITAR LAS CANTIDADES
                PreparedStatement pst3 = conexion.prepareStatement("UPDATE STOCK SET CANTS = (CANTS -?) WHERE CODP =(?)");

                pst3.setInt(1, cantv);
                pst3.setString(2, codp);

                pst3.executeUpdate();

                sql = "SELECT * from prezos where codp = ?";
                PreparedStatement pst4 = conexion.prepareStatement(sql);
                pst4.setString(1, codp);
                ResultSet rs3 = pst4.executeQuery();
                //RECOGEMOS TODO DE PREZOS E CALCULAMOS TOTALVENTAS
                while (rs3.next()) {

                    int prezo = rs3.getInt("prezo");
                    int de = rs3.getInt("de");

                    System.out.println("prezo: " + prezo + " ,desconto posible: " + de);

                    int totalv;

                    if (des.equals("s")) {
                        totalv = cantv * (prezo - de);
                    } else {
                        totalv = cantv * prezo;
                    }

                    System.out.println("total: " + totalv + "\n");

                    pw.print(nv + "\t");
                    pw.print(nomp + "\t");
                    pw.print(cantv + "\t");
                    pw.println(totalv + "\t");
                }

            }

        }
        pw.close();
        closeConexion();
    }

}
