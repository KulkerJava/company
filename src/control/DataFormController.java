package control;

import java.util.Vector;
import view.MainForm;
import view.WorkerDataForm;

public class DataFormController {

    private DatabaseController FDbCrtl;
    private WorkerDataForm FDataFrm;
    private int FStatus;
    private String oldName;
    
    public DataFormController( DatabaseController ADbCtrl, int AStatus, MainForm mainFrm ) {
        
        FDbCrtl = ADbCtrl;
        FDataFrm = new WorkerDataForm( mainFrm, false );
        FStatus = AStatus;
        addEventHolder();
        initComponents();
    }
    
    private void addEventHolder() {
        
        FDataFrm.getSaveBtn().addActionListener( event -> {
            
            switch( FStatus ) { case 1 -> saveWorker(); case 2 -> updateWorker(); }
        });
        FDataFrm.getCancelBtn().addActionListener( event -> cancelMethod() );
        FDataFrm.getExitBtn().addActionListener( event -> exit() );
    }
    
    private void initComponents() {
        
        
        switch( FStatus ) {
            
            case 1 -> {
                FDataFrm.getSaveBtn().setText( "Mentés" );
                FDataFrm.setTitle( "Új dolgozó felvétele" );
                FDataFrm.setLocationRelativeTo( FDataFrm );
                FDataFrm.setModal( true );
                FDataFrm.setVisible( true );
            }
            case 2 -> {
                FDataFrm.getSaveBtn().setText( "Módosítás" );
                FDataFrm.setTitle( "Dolgozó adatainak módosítása" );
            }
        }
        
        FDataFrm.setLocationRelativeTo( FDataFrm );
        
    }
    
    private void saveWorker() {
        
        Vector<Object> worker = new Vector<>();
        worker.add( FDataFrm.getNameTf().getText() );
        worker.add( FDataFrm.getHireTf().getText() );
        worker.add( FDataFrm.getRoleTf().getText() );
        worker.add( FDataFrm.getDepthTf().getText() );
        
        boolean success = FDbCrtl.newWorker( worker );
        if( success ) {
            
            FDataFrm.setModeLbl( "Sikeres kiírás" );
            cancelMethod();
        }
    }
    
    public void setTextFields( Vector<Object> worker, String oldName ) {
        
        FDataFrm.setNameTf( String.valueOf( worker.get( 0 )));
        FDataFrm.setHireTf( String.valueOf( worker.get( 1 )));
        FDataFrm.setRoleTf( String.valueOf( worker.get( 2 )));
        FDataFrm.setDepthTf( String.valueOf( worker.get( 3 )));
        this.oldName = oldName;
        FDataFrm.setModal( true );
        FDataFrm.setVisible( true );
    }
    
    public void updateWorker() {
        
        Vector<Object> worker = new Vector<>();
        worker.add( FDataFrm.getNameTf().getText() );
        worker.add( FDataFrm.getHireTf().getText() );
        worker.add( FDataFrm.getRoleTf().getText() );
        worker.add( FDataFrm.getDepthTf().getText() );
        
        boolean success = FDbCrtl.updateWorker( worker, oldName );
        if( success ) {
            
            FDataFrm.setModeLbl( "Sikeres frissítés" );
            cancelMethod();
        }
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
