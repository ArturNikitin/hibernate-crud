package model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"roleName"})
public class Role {
    @Id
    @GeneratedValue()
    private int id;

    @Column(unique = true, nullable = false, length = 50, name = "role_name")
    private String roleName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<Employee> employees;

    public Role(String roleName){
        this.roleName = roleName;
    }
}
