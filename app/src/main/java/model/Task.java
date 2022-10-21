package model;
import java.util.Date;

/**
 *
 * @author HansUlrich
 */
public class Task {
    
    private int id;
    private int idProject;
    private String name;
    private String notes;
    private String Description;
    private boolean completed;
    private Date deadline;
    private Date createDat;
    private Date updateDat;

    public Task(int id, int idProject, String name, String notes, boolean completed, Date deadline, Date createDat, Date updateDat) {
        this.id = id;
        this.idProject = idProject;
        this.name = name;
        this.notes = notes;
        this.completed = completed;
        this.deadline = deadline;
        this.createDat = createDat;
        this.updateDat = updateDat;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

       
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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
        return "Task{" + "id=" + id + ", idProject=" + idProject + ", name=" + name + ", notes=" + notes + ", completed=" + completed + ", deadline=" + deadline + ", createDat=" + createDat + ", updateDat=" + updateDat + '}';
    }
   
    
}
