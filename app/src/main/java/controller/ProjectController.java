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
        
        String sql = "INSERT INTO project (name, description, "
                + "createDat, updateDat) VALUES "
                + "(?,?,?,?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreateDat().getTime()));
            statement.setDate(4, new Date(project.getUpdateDat().getTime()));
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar a tarefa " + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
        
       
    }
    
    public void update(Project project){
         
        String sql = "UPDADE project SET name = ?, description = ?,"
                + " createDat = ?, updateDat = ? WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //estabelecendo a conxão com o banco de dados
            connection = ConnectionFactory.getConnection();
            // preparando a query
            statement = connection.prepareStatement(sql);
            // setando os valores do statyement
            
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreateDat().getTime()));
            statement.setDate(4, new Date(project.getUpdateDat().getTime()));
            statement.setInt(5, project.getId());
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
        
        // BUSCA TODOS OS PROJETOS NO BANCO DE DADOS
        String sql = "SELECT * FROM Projects";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null; // variavel que guarda o retorno do Banco de dados 
        
        List<Project> projects = new ArrayList<>(); // LISTA DE TAREFAS QUE SERA DEVOLVIDA QUANDO A CHAMADA DO METODO ACONTECER
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            // SETANDO O VALOR QUE CORRESPONDE AO FILTRO DE BUSCA
            statement.setInt(1, idProject);
            // VALOR RETORNADO PELA EXECUÇÃO DA QUERY
            resultSet = statement.executeQuery(); // NOS VALOR A UM RESULSET DE RESPOSTAS
            
            
            // ENQUANTO HOUVEREM VALORE A SEREM PERCORRIDOS NO RESULTSET
            while (resultSet.next()) { // O resultSet PODE DEVOLVER UM OU VARIOS VALORES
                
                Project project = new Project ();
                
                project.setId(resultSet.getInt("id")); // BUSCA AS INFORMAÇÕES NO REGISTRO
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreateDat(resultSet.getDate("createDat"));
                project.setUpdateDat(resultSet.getDate("updateDat"));
                projects.add(project); // COLOCA OS VALORES DENTRO NA LISTA DE PROJETOS 
              
            }
            
            } catch (Exception ex) {
                throw new RuntimeException("Erro ao inserir a tarefa" + ex.getMessage(), ex);
            } finally {
                ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
            // LISTA DE TAREFAS QUE FOI CRIADA E CARREGADA DO BANCO DE DADOS
            return projects;
        
    }
    
}
