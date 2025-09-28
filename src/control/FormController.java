package control;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import view.MainForm;
import view.WorkerDataForm;

public class FormController {

    private MainForm FMainFrm;
    private DatabaseController FDbCtrl;
    private Vector<Vector<Object>> FTableData;
    private Vector<String> FColumNames;
    
    public FormController( DatabaseController ADbCtrl ) {
        
        FDbCtrl = ADbCtrl;
        FMainFrm = new MainForm();
        initComponents();
    }
 
    private void initComponents() {
        
        addEventHolder();
        FTableData = new Vector<>();
        FColumNames = new Vector<>();
        FColumNames.add( "Név" );
        FColumNames.add( "Belépés" );
        FColumNames.add( "Beosztás" );
        FColumNames.add( "Részleg" );
        
        DefaultTableModel tModel = new DefaultTableModel( null, FColumNames );
        FMainFrm.getWorkerTbl().setModel( tModel );
        FMainFrm.setLocationRelativeTo( FMainFrm );
        FMainFrm.setVisible( true );
    }
    
    private void addEventHolder() {
        
        FMainFrm.getExitBtn().addActionListener( event -> exit() );
        FMainFrm.getLoadBtn().addActionListener( event -> loadData() );
        FMainFrm.getSaveBtn().addActionListener( event -> addNewWorker() );
        FMainFrm.getUpdateBtn().addActionListener( event -> updateWorker() );
        FMainFrm.getDeleteBtn().addActionListener( event -> deleteWorker() );
    }
    
    private void loadData() {
        
        FTableData = FDbCtrl.getWorkersData( "__GETWORKERSDATA__" );
        
        DefaultTableModel model = new DefaultTableModel( FTableData, FColumNames );
        FMainFrm.getWorkerTbl().setModel( model );
    }
    
    private void addNewWorker() {
        
        DataFormController dataFrmCtrl = new DataFormController( FDbCtrl, 1, FMainFrm );
        
    }
    
    private void updateWorker() {
        
        if( FMainFrm.getWorkerTbl().getSelectedRow() != -1 ) {
            
            DataFormController dataFrmCtrl = new DataFormController( FDbCtrl, 2, FMainFrm );
            Vector<Object> worker = new Vector<>();
            String oldName = String.valueOf( FMainFrm.getWorkerTbl().getValueAt( FMainFrm.getWorkerTbl().getSelectedRow(), 0 ));
            
            for( int i = 0; i < FMainFrm.getWorkerTbl().getColumnCount(); i++ ) {
            
                worker.add( FMainFrm.getWorkerTbl().getValueAt( FMainFrm.getWorkerTbl().getSelectedRow(), i ));
            }
            
            dataFrmCtrl.setTextFields( worker, oldName );
            
        }else {
            
            FMainFrm.setStatusLbl( "Nincs dolgozó kiválasztva" );
        }
        
    }
    
    private void deleteWorker() {
        
        Vector<Object> worker = new Vector<>();
        for( int i = 0; i < FMainFrm.getWorkerTbl().getColumnCount(); i++ ) {
            
            worker.add( FMainFrm.getWorkerTbl().getValueAt( FMainFrm.getWorkerTbl().getSelectedRow(), i ));
        }
        
        boolean success = FDbCtrl.deleteWorker( worker );
        if( success ) {
            
            FMainFrm.setStatusLbl( "Sikeres törlés" );
        }
    }
    
    private void exit() {
        
        System.exit( 0 );
    }
}
