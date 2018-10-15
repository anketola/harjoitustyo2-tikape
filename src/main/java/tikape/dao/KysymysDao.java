package tikape.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.dao.Database;
import tikape.rakenteet.Kysymys;

public class KysymysDao implements Dao<Kysymys, Integer> {

    private Database database;

    public KysymysDao(Database database) {
        this.database = database;
    }

    @Override
    public Kysymys findOne(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("Select * FROM Kysymys WHERE id = ?");
        stmt.setInt(1, key);
        
        ResultSet result = stmt.executeQuery();
        boolean hasOne = result.next();
        if (!hasOne) {
            return null;
        }
        
        Kysymys palautus = new Kysymys(result.getInt("id"), result.getString("kurssi"), result.getString("aihe"), result.getString("kysymysteksti"));
        stmt.close();
        result.close();
        conn.close();
                
        return palautus;
    }

    @Override
    public List<Kysymys> findAll() throws SQLException {
        List<Kysymys> kysymykset = new ArrayList<>();

        try (Connection conn = database.getConnection();
                ResultSet result = conn.prepareStatement("SELECT id, kurssi, aihe, kysymysteksti FROM Kysymys").executeQuery()) {

            while (result.next()) {
                
                kysymykset.add(new Kysymys(result.getInt("id"), result.getString("kurssi"), result.getString("aihe"), result.getString("kysymysteksti")));
            }
        result.close();
        conn.close();
        }
        
        return kysymykset;
    }

    @Override
    public Kysymys saveOrUpdate(Kysymys kysymys) throws SQLException {
       
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO KYSYMYS (kurssi, aihe, kysymysteksti) VALUES (?, ?, ?)");
            stmt.setString(1, kysymys.getKurssi());
            stmt.setString(2, kysymys.getAihe());
            stmt.setString(3, kysymys.getKysymysteksti());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }

        return null;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Kysymys WHERE Id = ?");
            stmt.setInt(1, key);
            
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        
    }

}
