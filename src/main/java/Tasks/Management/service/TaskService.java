package Tasks.Management.service;

import Tasks.Management.exception.TaskNotFound;
import Tasks.Management.model.Task;
import Tasks.Management.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public List<Task> findAll () {
        return repository.findAll();
    }

    public Task findById(Long id) {
        Optional<Task> taskOp = repository.findById(id);
        return taskOp.orElseThrow(() -> new TaskNotFound("Tarefa com o ID " + id + " não encontrado!"));
    }

    public Task create(Task task) {
        return repository.save(task);
    }

    public Task update(Long id, Task task) {
        Optional<Task> taskOp = repository.findById(id);
        if(taskOp.isPresent()) {
            task.setId(id);
            return repository.save(task);
        }
        throw new TaskNotFound("Tarefa com o ID " + id + " não encontrado!");
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new TaskNotFound("Tarefa com o ID " + id + " não encontrado!");
        }

        repository.deleteById(id);
    }
}
