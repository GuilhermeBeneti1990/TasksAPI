package Tasks.Management.service;

import Tasks.Management.model.Task;
import Tasks.Management.repository.TaskRepository;
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

    public Optional<Task> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<Task> create(Task task) {
        if (task.getId() != null && repository.existsById(task.getId())) {
            return Optional.empty();
        }

        return Optional.of(repository.save(task));
    }

    public Optional<Task> update(Long id, Task task) {
        return repository.findById(id)
                .map(taskFound -> {
                    taskFound.setTitle(task.getTitle());
                    taskFound.setDescription(task.getDescription());
                    taskFound.setLocal(task.getLocal());
                    taskFound.setDate(task.getDate());

                    return repository.save(taskFound);
                });
    }

    public boolean delete(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}
