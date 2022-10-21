package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;



/**
 *
 * @author HansUlrich
 */
public class ProjectController {
    
    public void save(Project project) {
        
        String sql = "INSERT INTO project (idProject, name, description, completed"
                + "notes, deadline, createDat, updateDat)"
                + "values (?,?,?,?,?,?,?,?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, project.getIdProject());
            statement.setString(2, project.getName());
            statement.setString(3, project.getDescription());
            statement.setBoolean(4, project.isCompleted());
            statement.setString(5, project.getNotes());
            statement.setDate(6, new Date(project.getDeadline().getTime()));
            statement.setDate(7, new Date(project.getCreateDat().getTime()));
            statement.setDate(8, new Date(project.getUpdateDat().getTime()));
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar a tarefa " + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
        
       
    }
    
    public void update(Project project){
         
        String sql = "UPDADE project SET idProject = ?, name = ?, description = ?,"
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
            statement.setInt(1, project.getIdProject());
            statement.setString(2, project.getName());
            statement.setString(3, project.getDescription());
            statement.setString(4, project.getNotes());
            statement.setBoolean(5, project.isCompleted());
            statement.setDate(6, new Date(project.getDeadline().getTime()));
            statement.setDate(7, new Date(project.getCreateDat().getTime()));
            statement.setDate(8, new Date(project.getUpdateDat().getTime()));
            statement.setInt(9, project.getId());
            statement.execute();
        } catch (Exception ex) { 
             throw new RuntimeException("Erro ao atualizar a tarefa");
            
            
        }finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    }
    
    public void deleteById(int projectId) throws SQLException{
        
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            // CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
            connection = ConnectionFactory.getConnection();
            
            // PREPARANDO A QUERY
            statement = connection.prepareStatement(sql);
            // SETANDO OS VALORES
            statement.setInt(1, projectId);
            // EXECUTANDO A QUERY
            statement.execute();            
        } catch (Exception ex) { // qualquer tipo de excessão
            throw new RuntimeException("Erro ao deletar a tarefa");
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public List<Project> getAll(int idProject){
        
        // BUSCA AS TAREFAS NO BANCO DE DADOS
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null; // variavel que guarda o retorno do Banco de dados 
        
        List<Project> project = new ArrayList<Project>(); // LISTA DE TAREFAS QUE SERA DEVOLVIDA QUANDO A CHAMADA DO METODO ACONTECER
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            // SETANDO O VALOR QUE CORRESPONDE AO FILTRO DE BUSCA
            statement.setInt(1, idProject);
            // VALOR RETORNADO PELA EXECUÇÃO DA QUERY
            resultSet = statement.executeQuery(); // NOS VALOR A UM RESULSET DE RESPOSTAS
            
            
            // ENQUANTO HOUVEREM VALORE A SEREM PERCORRIDOS NO RESULTSET
            while (resultSet.next()) { // O resultSet PODE DEVOLVER UM OU VARIOS VALORES
                
                Project project = new Project(); // CRIAR UM NOVo PROJETO
                project.setId(resultSet.getInt("id")); // BUSCA AS INFORMAÇÕES NO REGISTRO
                project.setIdProject(resultSet.getInt("idProject"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setNotes(resultSet.getString("notes"));
                project.setCompleted(resultSet.getBoolean("completed"));
                project.setDeadline(resultSet.getDate("deadline"));
                project.setCreateDat(resultSet.getDate("createDat"));
                project.setUpdateDat(resultSet.getDate("updateDat"));
                projects.add(project); // COLOCA OS VALORES DENTRO DAS TAREFAS 
              
            }
            
            } catch (Exception ex) {
                throw new RuntimeException("Erro ao inserir a tarefa" + ex.getMessage(), ex);
            } finally {
                ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
            // LISTA DE TAREFAS QUE FOI CRIADA E CARREGADA DO BANCO DE DADOS
            return project;
        
    }
    
    
     
}
