package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
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
            //estabelecendo a conxão com o banco de dados
            connection = ConnectionFactory.getConnection();
            // preparando a query
            statement = connection.prepareStatement(sql);
            // setando os valores do statyement
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.isCompleted());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreateDat().getTime()));
            statement.setDate(8, new Date(task.getUpdateDat().getTime()));
            statement.setInt(9, task.getId());
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
            // CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
            connection = ConnectionFactory.getConnection();
            
            // PREPARANDO A QUERY
            statement = connection.prepareStatement(sql);
            // SETANDO OS VALORES
            statement.setInt(1, taskId);
            // EXECUTANDO A QUERY
            statement.execute();            
        } catch (Exception ex) { // qualquer tipo de excessão
            throw new RuntimeException("Erro ao deletar a tarefa");
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public List<Task> getAll(int idProject){
        
        // BUSCA AS TAREFAS NO BANCO DE DADOS
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null; // variavel que guarda o retorno do Banco de dados 
        
        List<Task> tasks = new ArrayList<Task>(); // LISTA DE TAREFAS QUE SERA DEVOLVIDA QUANDO A CHAMADA DO METODO ACONTECER
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            // SETANDO O VALOR QUE CORRESPONDE AO FILTRO DE BUSCA
            statement.setInt(1, idProject);
            // VALOR RETORNADO PELA EXECUÇÃO DA QUERY
            resultSet = statement.executeQuery(); // NOS VALOR A UM RESULSET DE RESPOSTAS
            
            
            // ENQUANTO HOUVEREM VALORE A SEREM PERCORRIDOS NO RESULTSET
            while (resultSet.next()) { // O resultSet PODE DEVOLVER UM OU VARIOS VALORES
                
                Task task = new Task(); // CRIAR UMA NOVA TAREFA
                
                task.setId(resultSet.getInt("id")); // BUSCA AS INFORMAÇÕES NO REGISTRO
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setCompleted(resultSet.getBoolean("completed"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreateDat(resultSet.getDate("createDat"));
                task.setUpdateDat(resultSet.getDate("updateDat"));
                tasks.add(task); // COLOCA OS VALORES DENTRO DAS TAREFAS 
              
            }
            
            } catch (Exception ex) {
                throw new RuntimeException("Erro ao inserir a tarefa" + ex.getMessage(), ex);
            } finally {
                ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
            // LISTA DE TAREFAS QUE FOI CRIADA E CARREGADA DO BANCO DE DADOS
            return tasks;
        
    }
    
}
