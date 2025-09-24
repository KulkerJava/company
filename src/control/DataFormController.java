package control;

import java.util.Vector;
import view.DataForm;

public class DataFormController {

    private DatabaseController FDbCrtl;
    private DataForm FDataFrm;
    private String sqlFile;
    private int FStatus;
    
    public DataFormController( DatabaseController ADbCtrl, int AStatus ) {
        
        FDbCrtl = ADbCtrl;
        FDataFrm = new DataForm();
        FStatus = AStatus;
        initComponents();
        addEventHolder();
    }
    
    private void addEventHolder() {
        
        FDataFrm.getSaveBtn().addActionListener( event -> saveWorker() );
        FDataFrm.getCancelBtn().addActionListener( event -> cancelMethod() );
        FDataFrm.getExitBtn().addActionListener( event -> exit() );
    }
    
    private void initComponents() {
        
        
        switch( FStatus ) {
            
            case 1 -> {
                FDataFrm.getSaveBtn().setText( "Mentés" );
                FDataFrm.setTitle( "Új dolgozó felvétele" );
                sqlFile = "__NEWWORKER__";
            }
            case 2 -> {
                FDataFrm.getSaveBtn().setText( "Módosítás" );
                FDataFrm.setTitle( "Dolgozó adatainak módosítása" );
                sqlFile = "__UPDATEWORKER__";
            }
        }
        
        FDataFrm.setLocationRelativeTo( FDataFrm );
        FDataFrm.setVisible( true );
    }
    
    private void saveWorker() {
        
        Vector<Object> worker = new Vector<>();
        worker.add( FDataFrm.getNameTf().getText() );
        worker.add( FDataFrm.getHireTf().getText() );
        worker.add( FDataFrm.getRoleTf().getText() );
        worker.add( FDataFrm.getDepthTf().getText() );
        
        boolean success = FDbCrtl.newWorker( worker, sqlFile );
        if( success ) {
            
            FDataFrm.setModeLbl( "Sikeres kiírás" );
            cancelMethod();
        }
    }
    
    public void updateWorker( Vector<Object> worker ) {
        
        FDataFrm.setNameTf( String.valueOf( worker.get( 0 )));
        FDataFrm.setHireTf( String.valueOf( worker.get( 1 )));
        FDataFrm.setRoleTf( String.valueOf( worker.get( 2 )));
        FDataFrm.setDepthTf( String.valueOf( worker.get( 3 )));
        
        //saveWorker();
    }
    
    private void cancelMethod() {
        
        FDataFrm.setNameTf( "" );
        FDataFrm.setHireTf( "" );
        FDataFrm.setRoleTf( "" );
        FDataFrm.setDepthTf( "" );
    }
    
    private void exit() {
        
        FDataFrm.dispose();
    }
}
