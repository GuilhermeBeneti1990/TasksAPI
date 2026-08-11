package Tasks.Management.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "user")
    private List<Guest> guests;

    @OneToMany(mappedBy = "creator")
    private List<Task> createdTasks;

}
