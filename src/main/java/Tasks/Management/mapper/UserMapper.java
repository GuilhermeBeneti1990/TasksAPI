package Tasks.Management.mapper;

import Tasks.Management.dto.UserDTO;
import Tasks.Management.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDTO toDTO(User entity);

    User toEntity(UserDTO dto);
}
