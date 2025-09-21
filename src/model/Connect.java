package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    
    private Connection conn;
    private String url;
    private String database;
    private String user;
    private String pass;
    
    public Connect( String dbName, String user, String pwd ) {
        
        conn = null;
        url = "jdbc:mariadb://localhost:3306/";
        database = dbName;
        this.user = user;
        pass = pwd;
    }
    
    public void connectToMakeDatabase()  {
        
        try {
            
            conn = DriverManager.getConnection( url + "?user=" + user + "&password=" + pass );
            
        } catch (SQLException ex) {
            
            System.getLogger(Connect.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public void connect() {
      
        //String connectionStr = "jdbc:mariadb://localhost:3306/pizza?user=root&password=";
        
        try {
            
            conn = DriverManager.getConnection( url + database + "?user=" + user + "&password=" + pass );
            
        } catch ( SQLException ex ) {
            
            System.err.println( "Hiba a kapcsolódás során." );
        }
    }
    
    public boolean closeConnect() {
        
        try {
            
            conn.close();
            return true;
            
        } catch ( SQLException ex ) {
            
            System.err.println( "Hiba a lezárás során." );
            ex.printStackTrace();
            return false;
        }
        
    }
    
    public Connection getConnection() {
        return conn;
    }
}
