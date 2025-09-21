package control;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;
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
        
        DefaultTableModel tModel = new DefaultTableModel( FTableData, FColumNames );
        FMainFrm.getWorkerTbl().setModel( tModel );
        
        FMainFrm.setVisible( true );
    }
    
    private void addEventHolder() {
        
        FMainFrm.getExitBtn().addActionListener( event -> exit() );
    }
    
    private void exit() {
        
        System.exit( 0 );
    }
}
