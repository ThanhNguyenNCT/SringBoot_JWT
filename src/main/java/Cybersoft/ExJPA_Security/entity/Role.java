package Cybersoft.ExJPA_Security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity(name = "roles")
@Data
public class Role {
    @Id
    private int id;
    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> userList;
}
