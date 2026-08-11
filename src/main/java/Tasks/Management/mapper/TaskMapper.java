package Tasks.Management.mapper;

import Tasks.Management.dto.TaskDTO;
import Tasks.Management.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {
    TaskDTO toDTO(Task task);
    Task toEntity(TaskDTO dto);

    List<TaskDTO> toDTOList(List<Task> tasks);
    List<Task> toEntityList(List<TaskDTO> dtos);
}
