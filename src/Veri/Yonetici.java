
package Veri;



import static Veri.OlusturVeri.password;
import static Veri.OlusturVeri.url2;
import static Veri.OlusturVeri.username;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Yonetici {
    public static void yonetici(String ad, String soyad, String kullaniciadi, String sifre, String mail, String mobilNo,String il, String ilce, String Adres) {
        String query = "INSERT INTO admin (firstName,lastName,userName,password,email,mobileNo,il,ilce,address) values('" + ad + "','" + soyad + "','" + kullaniciadi + "','"
                + password + "','" + mail + "','" + mobilNo + "','" + il + "','" + ilce + "','" + Adres + "')";

        OlusturVeri.execute(url2, query);
    }

    public static boolean isAvailable(String userName) {
        String isExists = "SELECT admin.userName FROM admin WHERE userName=?;";
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
