package Tasks.Management.controller;

import Tasks.Management.dto.TaskDTO;
import Tasks.Management.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskDTO task) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(task));
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @Valid @RequestBody TaskDTO task) {
        return ResponseEntity.ok(service.update(id, task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
