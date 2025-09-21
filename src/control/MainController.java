package control;

public class MainController {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        initialize();
    }
    
    private static void initialize() {
        
        DatabaseController dbCtrl = new DatabaseController();
        FormController formCtrl = new FormController( dbCtrl );
    }
}
