package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue()
    private int id;

    @Column(unique = true, nullable = false, length = 50, name = "role_name")
    private String roleName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
    private List<Employee> employees;

    public Role(String roleName){
        this.roleName = roleName;
    }
}
