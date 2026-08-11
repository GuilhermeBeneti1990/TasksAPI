package Tasks.Management.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "guets")
@Data
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "taskId")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

}
