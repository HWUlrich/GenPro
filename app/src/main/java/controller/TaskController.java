package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import model.Task;
import util.ConnectionFactory;



/**
 *
 * @author HansUlrich
 */
public class TaskController {
    
    public void save(Task task) {
        
        String sql = "INSERT INTO tasks (idProject, name, description, completed"
                + "notes, deadline, createDat, updateDat)"
                + "values (?,?,?,?,?,?,?,?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreateDat().getTime()));
            statement.setDate(8, new Date(task.getUpdateDat().getTime()));
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar a tarefa " + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
        
       
    }
    
    public void update(Task task){
         
        String sql = "UPDADE tasks SET idProject = ?, name = ?, description = ?,"
                + "notes = ?, deadline = ?, completed = ?, createDat = ? "
                + "updateDat = ? WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(1, task.getName());
            statement.setString(1, task.getDescription());
            statement.setString(1, task.getNotes());
            statement.setBoolean(1, task.isCompleted());
            statement.setDate(1, new Date(task.getDeadline().getTime()));
            statement.setDate(1, new Date(task.getCreateDat().getTime()));
            statement.setDate(1, new Date(task.getUpdateDat().getTime()));
            statement.execute();
        } catch (Exception ex) { 
             throw new RuntimeException("Erro ao atualizar a tarefa");
            
            
        }finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    }
    
    public void deleteById(int taskId) throws SQLException{
        
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.execute();            
        } catch (Exception ex) { // qualquer tipo de excessão
            throw new RuntimeException("Erro ao deletar a tarefa");
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public List<Task> getAll(int idProject){
        return null;
    }
    
    
     
}
