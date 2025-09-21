package control;

import java.sql.Connection;
import model.Connect;
import model.SqlRunner;
import java.sql.SQLException;

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
}
