package tikape.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.rakenteet.Kysymys;
import tikape.rakenteet.Vastaus;

public class VastausDao implements Dao<Vastaus, Integer> {

    private Database database;
    private Dao<Kysymys, Integer> kysymysDao;

    public VastausDao(Database database, Dao<Kysymys, Integer> kysymysDao) {
        this.database = database;
        this.kysymysDao = kysymysDao;
    }

    @Override
    public Vastaus findOne(Integer key) throws SQLException {
        
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("Select * FROM Vastaus WHERE id = ?");
        stmt.setInt(1, key);
        
        ResultSet result = stmt.executeQuery();
        boolean hasOne = result.next();
        if (!hasOne) {
            return null;
        }
        
        Kysymys kysymys = kysymysDao.findOne(result.getInt("kysymys_id"));
        
        Vastaus palautus = new Vastaus(key, kysymys, result.getString("vastausteksti"), result.getBoolean("oikein"));
        stmt.close();
        result.close();
        conn.close();
                
        return palautus;
    }

    @Override
    public List<Vastaus> findAll() throws SQLException {
        List<Vastaus> vastaukset = new ArrayList<>();

        try (Connection conn = database.getConnection();
                ResultSet result = conn.prepareStatement("SELECT * FROM Kysymys").executeQuery()) {

            while (result.next()) {
                Kysymys kysymys = kysymysDao.findOne(result.getInt("kysymys_id"));
                
                vastaukset.add(new Vastaus(result.getInt("id"), kysymys, result.getString("vastausteksti"), result.getBoolean("oikein")));
            }
        result.close();
        conn.close();
        }
        
        return vastaukset;
    }

    public List<Vastaus> kaikkiKyssarille(Integer key) throws SQLException {
        List<Vastaus> vastaukset = new ArrayList<>();
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("Select * FROM Vastaus WHERE kysymys_id = ?");
        stmt.setInt(1, key);
        
        ResultSet result = stmt.executeQuery();
        while (result.next()) {
                Kysymys kysymys = kysymysDao.findOne(result.getInt("kysymys_id"));
                
                vastaukset.add(new Vastaus(result.getInt("id"), kysymys, result.getString("vastausteksti"), result.getBoolean("oikein")));
            }
        stmt.close();
        result.close();
        conn.close();
        return vastaukset;
    }
    
    
    public boolean eiOikeaaKyssarille(Integer key) throws SQLException {
        List<Vastaus> vastaukset = new ArrayList<>();
        Boolean palautus = true;
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("Select * FROM Vastaus WHERE kysymys_id = ? AND oikein = ?");
        stmt.setInt(1, key);
        stmt.setBoolean(2, true);
        
        ResultSet result = stmt.executeQuery();
        while (result.next()) {
                Kysymys kysymys = kysymysDao.findOne(result.getInt("kysymys_id"));
                
                vastaukset.add(new Vastaus(result.getInt("id"), kysymys, result.getString("vastausteksti"), result.getBoolean("oikein")));
            }
        if (vastaukset.size() > 0) {
            palautus = false;
        }
        stmt.close();
        result.close();
        conn.close();
        return palautus;
    }
    
     public boolean kaikkiOikeinKyssarille(Integer key) throws SQLException {
        List<Vastaus> vastaukset = new ArrayList<>();
        Boolean palautus = true;
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("Select * FROM Vastaus WHERE kysymys_id = ?");
        stmt.setInt(1, key);
        
        ResultSet result = stmt.executeQuery();
        while (result.next()) {
            if (!result.getBoolean("oikein")) {
                palautus = false;
            }
        }
        stmt.close();
        result.close();
        conn.close();
        return palautus;
    }
    
    @Override
    public Vastaus saveOrUpdate(Vastaus vastaus) throws SQLException {
  
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Vastaus (vastausteksti, oikein, kysymys_id) VALUES (?, ?, ?)");
            stmt.setString(1, vastaus.getVastausteksti());
            stmt.setBoolean(2, vastaus.getOikein());
            stmt.setInt(3, vastaus.getKysymys().getId());

            stmt.executeUpdate();
        stmt.close();
        conn.close();
        }
        return null;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Vastaus WHERE Id = ?");
            stmt.setInt(1, key);
            
            stmt.executeUpdate();
        stmt.close();
        conn.close();
        }
        
    }

}
