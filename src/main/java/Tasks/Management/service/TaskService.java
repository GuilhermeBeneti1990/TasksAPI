package Tasks.Management.service;

import Tasks.Management.dto.TaskDTO;
import Tasks.Management.exception.TaskNotFound;
import Tasks.Management.model.Task;
import Tasks.Management.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<TaskDTO> findAll () {
        return modelMapper.map(repository.findAll(), new TypeToken<List<TaskDTO>>() {}.getType());
    }

    public TaskDTO findById(Long id) {
        Optional<Task> taskOp = repository.findById(id);
        TaskDTO taskDTO = modelMapper.map(taskOp.orElseThrow(() -> new TaskNotFound("Tarefa com o ID " + id + " não encontrado!")), TaskDTO.class);
        return taskDTO;
    }

    public TaskDTO create(TaskDTO task) {
        Task taskEntity = modelMapper.map(task, Task.class);
        return modelMapper.map(repository.save(taskEntity), TaskDTO.class);
    }

    public TaskDTO update(Long id, TaskDTO task) {
        Task taskEntity = modelMapper.map(task, Task.class);
        Optional<Task> taskOp = repository.findById(id);
        if(taskOp.isPresent()) {
            taskEntity.setId(id);
            return modelMapper.map(repository.save(taskEntity), TaskDTO.class);
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
