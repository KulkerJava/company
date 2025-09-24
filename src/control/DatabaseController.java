package control;

import java.sql.Connection;
import model.Connect;
import model.SqlRunner;
import java.sql.SQLException;
import java.util.Vector;

public class DatabaseController {

    private Connect FConnect;
    private Connection FConn;
    
    public DatabaseController() {
        
        checkDataBase();
    }
    
    private void checkDataBase() {
        
        FConnect = new Connect( "resahh_company", "resahh", "hajakend" );
        FConnect.connect();
        FConn = FConnect.getConnection();
        
        if( FConn == null  ) {
            
            FConnect.connectToMakeDatabase();
            FConn = FConnect.getConnection();
            SqlRunner sqlRun = new SqlRunner( "__CREATEDATABASE__" );
            sqlRun.createDatabase( FConn );
            
        }else {
            
            FConn = FConnect.getConnection();
        }
    }
    
    public Vector<Vector<Object>> getWorkersData() {
        
        FConnect = new Connect( "resahh_company", "resahh", "hajakend" );
        FConnect.connect();
        FConn = FConnect.getConnection();
        Vector<Vector<Object>> items = new Vector<>();
        
        if( FConn != null ) {
            
            SqlRunner sqlRun = new SqlRunner( "__GETWORKERSDATA__" );
            items = sqlRun.getWorkersData( FConn );
            
            return items;
        
        }else {
            
            return null;
        }
    }
}
