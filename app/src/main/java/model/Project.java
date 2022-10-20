package model;
import java.util.Date;

/**
 *
 * @author HansUlrich
 */
public class Project {
    
    private int id;
    private String name;
    private String description;
    private Date createDat;
    private Date updateDat;
    
    //METODO CONSTRUTOR
    public Project(int id, String name, String description, Date createDat, Date updateDat) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createDat = createDat;
        this.updateDat = updateDat;
    }

    //METODOS DE ACESSO
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDat() {
        return createDat;
    }

    public void setCreateDat(Date createDat) {
        this.createDat = createDat;
    }

    public Date getUpdateDat() {
        return updateDat;
    }

    public void setUpdateDat(Date updateDat) {
        this.updateDat = updateDat;
    }

    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", name=" + name + ", description=" + description + ", createDat=" + createDat + ", updateDat=" + updateDat + '}';
    }
    
    
    
}
 