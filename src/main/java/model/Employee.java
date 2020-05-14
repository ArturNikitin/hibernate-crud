package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.utils.EmployeeAddress;
import model.utils.EmployeeAddressConverter;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table( indexes = {
        @Index(columnList = "name, lastName", unique = true)
})
public class Employee {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    @Convert(converter = EmployeeAddressConverter.class)
    private EmployeeAddress address;

    @Column
    private int age;

    @ManyToOne(optional = false)
    private Role role;

    @SuppressWarnings("JpaDataSourceORMInspection")
    @ManyToMany
    @JoinTable(
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    private List<Project> projects;

    public Employee(String name, String lastName, String email, Role role) {
        this.name = name;
        this.lastName = lastName;

        this.role = role;
        this.email = email;
    }

    public Employee(String name, String lastName, String email, Role role, EmployeeAddress address) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.role = role;
        this.email = email;
        this.address = address;
    }
}
