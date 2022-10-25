package GenPro;
import controller.ProjectController;
import java.sql.Connection;
import java.util.List;
import model.Project;
import util.ConnectionFactory;



/**
 *
 * @author HansUlrich
 */
public class Main {
    
    public static void main(String[] args){
        
        
        Connection start = ConnectionFactory.getConnection();
        
        ConnectionFactory.closeConnection(start);
        
        ProjectController projectController = new ProjectController();

        Project project = new Project();
        project.setName("Projeto teste");
        project.setDescription("description");
        projectController.save(project);

        project.setName("Novo nome do projeto");
        projectController.update(project);

        List<Project> projects = projectController.getAll();
        System.out.println("Total de projetos " + project.size());
       
        
              
        
    }
    
}
