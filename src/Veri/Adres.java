/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Veri;

import static Veri.OlusturVeri.password;
import static Veri.OlusturVeri.url2;
import static Veri.OlusturVeri.username;
import kargotakipsistemiyeni.AdresPrivate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class Adres {
    
     public static List<AdresPrivate> ilceGoster(String il_adi) {
        List<AdresPrivate> result = new ArrayList<>();
        String query = "select * from ilceler inner join iller on ilceler.il_no=iller.il_no where iller.isim=?;";
        
        try (Connection conn = DriverManager.getConnection(url2, username, password);
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, il_adi);
            

            try(ResultSet rs = stmt.executeQuery();){
            while (rs.next()) {
                AdresPrivate ilc = new AdresPrivate();   
                ilc.setIlId(rs.getInt("il_no"));
                ilc.setIlceId(rs.getInt("ilce_no"));
                ilc.setIsim(rs.getString("isim"));
                result.add(ilc);
            }
           }catch( SQLException e){
               System.out.println(e);
           }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
}
