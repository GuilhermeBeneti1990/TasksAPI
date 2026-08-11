package Tasks.Management.service;

import Tasks.Management.dto.TaskDTO;
import Tasks.Management.exception.TaskNotFound;
import Tasks.Management.mapper.TaskMapper;
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

    @Autowired
    private TaskMapper taskMapper;

    public List<TaskDTO> findAll () {
        return taskMapper.toDTOList(repository.findAll());
    }

    public TaskDTO findById(Long id) {
        Optional<Task> taskOp = repository.findById(id);
        Task task = taskOp.orElseThrow(() -> new TaskNotFound("Tarefa com o ID " + id + " não encontrado!"));
        return taskMapper.toDTO(task);
    }

    public TaskDTO create(TaskDTO task) {
        Task taskEntity = taskMapper.toEntity(task);
        return taskMapper.toDTO(repository.save(taskEntity));
    }

    public TaskDTO update(Long id, TaskDTO task) {
        Task taskEntity = taskMapper.toEntity(task);
        Optional<Task> taskOp = repository.findById(id);
        if(taskOp.isPresent()) {
            taskEntity.setId(id);
            return taskMapper.toDTO(repository.save(taskEntity));
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
