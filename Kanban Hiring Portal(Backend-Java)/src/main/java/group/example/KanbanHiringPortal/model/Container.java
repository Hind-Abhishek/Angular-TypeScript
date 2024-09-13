package group.example.KanbanHiringPortal.model;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Container {
	@Id
    @Column (name="containerName")
    String containerName;

    @Column(name="containerStatus")
    String containerStatus;

    @Column(name="position")
    int position;


    public Container(){

    }

    public Container(String v1, String v3, int v4){
        containerName=v1;
        containerStatus=v3;
        position=v4;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getContainerStatus() {
        return containerStatus;
    }

    public void setContainerStatus(String containerStatus) {
        this.containerStatus = containerStatus;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
