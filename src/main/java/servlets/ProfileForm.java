package servlets;

import lombok.Getter;
import lombok.Setter;
import model.Project;

import java.util.List;

@Getter
@Setter
public class ProfileForm {
    private String name;
    private String lastName;
    private List<Project> projects;
}
