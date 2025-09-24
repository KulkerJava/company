package model;

import java.sql.Statement;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import java.sql.Connection;
import java.sql.ResultSet;

public class SqlRunner {

    private String FFileName;
    
    public SqlRunner( String AFileName ) {
        
        FFileName = "sql/" + AFileName + ".sql";
    }
    
    private String[] getSqlQuery() {
        
        String[] xSqlQueries = null;
        Path filePath = Path.of( FFileName );
        try {
            
            String content = Files.readString( filePath );
            xSqlQueries = content.split( ";" );
            
        } catch ( IOException ex ) {
            
            System.err.println( "Az sql fájl nem található" );
        }
        
        return xSqlQueries;
    }
    
    public void createDatabase( Connection AConn ) {
        
        String[] xSql = getSqlQuery();
        Statement stmt = null;
        try {
            
            stmt = AConn.createStatement();
            for( String xCommand : xSql ) {
                
                stmt.executeUpdate( xCommand ); 
            }
            
        } catch ( SQLException ex ) {
            
            ex.printStackTrace();
        }
    }
    
    public Vector<Vector<Object>> getWorkersData( Connection AConn ) {
        
        String[] xQueries = getSqlQuery();
        Statement xStmt = null;
        ResultSet xRs = null;
        Vector<Vector<Object>> xItems = new Vector<>();
        
        try {
            
            xStmt = AConn.createStatement();
            xRs = xStmt.executeQuery( xQueries[ 0 ] );
            if( xRs != null ) {
                
                while( xRs.next() ) {
                    
                    Vector<Object> xRow = new Vector<>();
                    xRow.add( xRs.getObject( 1 ));
                    xRow.add( xRs.getObject( 2 ));
                    xRow.add( xRs.getObject( 3 ));
                    xRow.add( xRs.getObject( 4 ));
                    
                    xItems.add( xRow );
                }
            }
        } catch ( SQLException ex ) {
            
            System.out.println( "Hiba a lekérdezés során" );
        }
        
        return xItems;
    }
}
