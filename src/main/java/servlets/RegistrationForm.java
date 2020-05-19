package servlets;

import lombok.Getter;
import lombok.Setter;
import model.Role;

import java.util.List;

@Getter
@Setter
public class RegistrationForm {
    private String name;
    private String lastName;
    private String password;
    private List<Role> roles;
    private Role role;
}
