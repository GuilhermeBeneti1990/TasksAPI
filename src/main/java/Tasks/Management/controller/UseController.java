package Tasks.Management.controller;

import Tasks.Management.dto.TaskDTO;
import Tasks.Management.dto.UserDTO;
import Tasks.Management.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UseController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(userDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(service.update(id, userDTO));
    }

    @GetMapping("/{userID}/invites")
    public ResponseEntity<List<TaskDTO>> getInvites(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getTasksByGuest(userId));
    }
}
