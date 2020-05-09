
package Veri;

import static Veri.OlusturVeri.password;
import static Veri.OlusturVeri.url2;
import static Veri.OlusturVeri.username;
import kargotakipsistemiyeni.TakipTabloPrivate;
import kargotakipsistemiyeni.MusteriPrivate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Arayuz.MusteriGiris;
import Arayuz.KargoGonder;
public class Musteri {
    
    
    
    public static List<MusteriPrivate> musteriBilgileri(String userName, int parametre2) {
        List<MusteriPrivate> result = new ArrayList<>();
        String query = "select * from musteri where musteri.userName=?;";

        try (Connection conn = DriverManager.getConnection(url2, username, password);
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userName);

            try (ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    MusteriPrivate musteri1 = new MusteriPrivate();
                    musteri1.setMusteriId(rs.getInt("musteri_id"));
                    musteri1.setAd(rs.getString("firstName"));
                    musteri1.setSoyad(rs.getString("lastName"));
                    musteri1.setKullaniciAdi(rs.getString("userName"));
                    musteri1.setSifre(rs.getString("password"));
                    musteri1.setMail(rs.getString("email"));
                    musteri1.setMobileNo(rs.getString("mobileNo"));
                    musteri1.setAdres(rs.getString("address"));
                    result.add(musteri1);
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

    public static List<MusteriPrivate> musteriBilgileri(String userName) {
        List<MusteriPrivate> result = new ArrayList<>();
        String query = "select * from musteri where musteri.userName=?;";

        try (Connection conn = DriverManager.getConnection(url2, username, password);
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userName);

            try (ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    MusteriPrivate musteri1 = new MusteriPrivate();
                   musteri1.setMusteriId(rs.getInt("musteri_id"));
                    musteri1.setAd(rs.getString("firstName"));
                    musteri1.setSoyad(rs.getString("lastName"));
                    musteri1.setKullaniciAdi(rs.getString("userName"));
                    musteri1.setSifre(rs.getString("password"));
                    musteri1.setMail(rs.getString("email"));
                    musteri1.setMobileNo(rs.getString("mobileNo"));
                    musteri1.setAdres(rs.getString("address"));
                    result.add(musteri1);
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

    public static List<TakipTabloPrivate> Musteri_koordinatlari() {
        List<TakipTabloPrivate> result = new ArrayList<>();
        String query = "select lat,lon from iller where iller.isim=(select (musteri.il) from musteri where musteri_id=?);";

        try (Connection conn = DriverManager.getConnection(url2, username, password);
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, MusteriGiris.online.getMusteriId());
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

    public static void addMusteri(String firstName, String lastName, String userName, String password, String email, String mobileNo, String il, String ilce, String address) {
        String query = "INSERT INTO musteri (firstName,lastName,userName,password,email,mobileNo,il,ilce,address) values('" + firstName + "','" + lastName + "','" + userName + "','"
                + password + "','" + email + "','" + mobileNo + "','" + il + "','" +ilce + "','" + address + "')";
        
        OlusturVeri.execute(url2, query);
    }
    
    public static boolean isAvailable(String userName){
          String isExists = "SELECT musteri.userName FROM musteri WHERE userName=?;";
        boolean result = false;
        try (Connection conn = DriverManager.getConnection(url2, username, password);
                PreparedStatement stmt = conn.prepareStatement(isExists)) {
            stmt.setString(1, userName);
            try (ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {

                    result = true;
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
}
