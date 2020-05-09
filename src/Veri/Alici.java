/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Veri;


import static Veri.OlusturVeri.password;
import static Veri.OlusturVeri.url2;
import static Veri.OlusturVeri.username;
import kargotakipsistemiyeni.AliciPrivate;
import kargotakipsistemiyeni.TakipTabloPrivate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Arayuz.GirisForm;

public class Alici {
    
    
    
    public static List<AliciPrivate> aliciBilgileri(String mobileNo, String firstName, String lastName) {
        List<AliciPrivate> result = new ArrayList<>();
        String query = "select * from alici where alici.mobileNo=? and alici.firstName=? and alici.lastName=?;";

        try (Connection conn = DriverManager.getConnection(url2, username, password);
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, mobileNo);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            try (ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    AliciPrivate alici1 = new AliciPrivate();
                    alici1.setYoneticiId(rs.getInt("alici_id"));
                    alici1.setAd(rs.getString("firstName"));
                    alici1.setSoyad(rs.getString("lastName"));
                    alici1.setTelefonNo(rs.getString("mobileNo"));
                    alici1.setAdres(rs.getString("address"));
                    alici1.setIl(rs.getString("il"));
                    alici1.setIlce(rs.getString("ilce"));

                    result.add(alici1);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<TakipTabloPrivate> alici_koordinatlari(String il) {
        List<TakipTabloPrivate> result = new ArrayList<>();
        String query = "select lat,lon from iller where iller.isim=?;";

        try (Connection conn = DriverManager.getConnection(url2, username, password);
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, il);
            try (ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    TakipTabloPrivate a = new TakipTabloPrivate();
                    a.setLat(rs.getInt("lat"));
                    a.setLon(rs.getInt("lon"));
                    result.add(a);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void addAlici(String ad, String soyad, String telefon, String ilce, String il, String aliciAdres) {
        String insert_alici = "INSERT INTO alici (firstName,lastName,mobileNo,ilce,il,address) VALUES('" + ad + "','" + soyad + "','" + telefon + "','"
                + ilce + "','" + il + "','" + aliciAdres + "')";
        OlusturVeri.execute(OlusturVeri.url2, insert_alici);
    }
    
    
}
