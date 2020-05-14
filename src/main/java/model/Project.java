package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.utils.ProjectStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "project_name")
    private String projectName;

    @Column
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projects")
    private List<Employee> employees;

    public Project(String projectName, ProjectStatus status) {
        this.projectName = projectName;
        this.status = status;
    }
}
