package control;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import view.MainForm;

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
    }
    
    private void loadData() {
        
        FTableData = FDbCtrl.getWorkersData();
        
        DefaultTableModel model = new DefaultTableModel( FTableData, FColumNames );
        FMainFrm.getWorkerTbl().setModel( model );
    }
    
    private void addNewWorker() {
        
    }
    
    private void updateWorker() {
        
        //int selectedRow = FMainFrm.getWorkerTbl().getSelectedRow();
        //System.out.println( selectedRow );
        
        for( int i = 0; i < FMainFrm.getWorkerTbl().getColumnCount(); i++ ) {
            
            System.out.println( FMainFrm.getWorkerTbl().getValueAt( FMainFrm.getWorkerTbl().getSelectedRow(), i ));
        }
    }
    
    private void exit() {
        
        System.exit( 0 );
    }
}
