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
    
    private void connectToDatabase() {
        
        FConnect = new Connect( "resahh_company", "resahh", "hajakend" );
        FConnect.connect();
        FConn = FConnect.getConnection();
    }
    private void checkDataBase() {
        
        connectToDatabase();
        
        if( FConn == null  ) {
            
            FConnect.connectToMakeDatabase();
            FConn = FConnect.getConnection();
            SqlRunner sqlRun = new SqlRunner( "__CREATEDATABASE__" );
            sqlRun.createDatabase( FConn );
            FConnect.closeConnect();
        }
    }
    
    public Vector<Vector<Object>> getWorkersData( String sqlFile ) {
        
        connectToDatabase();
        Vector<Vector<Object>> items = new Vector<>();
        
        if( FConn != null ) {
            
            SqlRunner sqlRun = new SqlRunner( sqlFile );
            items = sqlRun.getWorkersData( FConn );
            FConnect.closeConnect();
            return items;
        
        }else {
            
            return null;
        }
    }
    
    public boolean newWorker( Vector<Object> worker ) {
        
        connectToDatabase();
        if( FConn != null ) {
            
            SqlRunner sqlRun = new SqlRunner( "__NEWWORKER__" );
            boolean success = sqlRun.saveWorker( FConn, worker );
            FConnect.closeConnect();
            
            return true;
            
        }else {
            
            return false;
        }
    }
    
    public boolean updateWorker( Vector<Object> worker, String oldName ) {
        
        connectToDatabase();
        if( FConn != null ) {
            
            SqlRunner sqlRun = new SqlRunner( "__UPDATEWORKER__" );
            boolean success = sqlRun.updateWorker( FConn, worker, oldName );
            FConnect.closeConnect();
            
            return true;
            
        }else {
            
            return false;
        }
        
    }
    
    public boolean deleteWorker( Vector<Object> worker ) {
        
        connectToDatabase();
        if( FConn != null ) {
            
            SqlRunner sqlRun = new SqlRunner( "__DELETEWORKER__" );
            boolean success = sqlRun.deleteWorker( FConn, worker );
            FConnect.closeConnect();
           
            return true;
            
        }else {
            
            return false;
        }
    }
}
