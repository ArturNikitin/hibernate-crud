package dao;

import model.Employee;
import model.Project;
import model.utils.ProjectStatus;

import java.util.List;

public interface ProjectDAO {
    Project createProject(String projectName, ProjectStatus status);
    Project findByProjectName(String projectName);
    Project updateProjectStatus(Project project, ProjectStatus status);
    void removeProject(Project project);
}
