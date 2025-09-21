package model;

import java.sql.Statement;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;

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
}
