package util;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author HansUlrich
 */
public class ConnectionFactory {
    
    public static final String DRIVER = "com.mysql.jdbc.Driver"; //DRIVER USADO DO BANCO DE DADOS E PODE MUDAR DE ACORDO COM O BANCO USADO
    public static final String URL = "jdbc:mysql://localhost:3306/genpro"; // CAMINHO DE CONEXAÕ COM O BANCO DE DADOS
    public static final String USER = "root"; //USUARIO por padrão root
    public static final String PASS = ""; //SENHA
    
    
    // o 'static' significa que não precisa criar um Objeto para chamar esse método...
    public static Connection getConnection() {
        
        // o try é um recurso para tratamento de excessão (Erros), que pega erros que possam acontecer...
        try {
                Class.forName(DRIVER);
                return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception ex) {
                        throw new RuntimeException("Erro na conexão com o banco de dados", ex);
        }
    }
    
    public static void closeConnection(Connection connection){
        
        try {
            if(connection != null) {
                connection.close();
            }
            
        } catch (Exception ex) {
        
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados");
            
        }
        
    }
                
                
    
    
    
}
