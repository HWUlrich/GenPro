package GenPro;
import java.sql.Connection;
import util.ConnectionFactory;



/**
 *
 * @author HansUlrich
 */
public class Main {
    
    public static void main(String[] args){
        
        
        Connection start = ConnectionFactory.getConnection();
        
        ConnectionFactory.closeConnection(start);
              
        
    }
    
}
