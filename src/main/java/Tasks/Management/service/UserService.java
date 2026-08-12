package Tasks.Management.service;

import Tasks.Management.dto.TaskDTO;
import Tasks.Management.dto.UserDTO;
import Tasks.Management.exception.UserNotFound;
import Tasks.Management.mapper.TaskMapper;
import Tasks.Management.mapper.UserMapper;
import Tasks.Management.model.Guest;
import Tasks.Management.model.User;
import Tasks.Management.repository.GuestRepository;
import Tasks.Management.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private UserMapper userMapper;

    public UserDTO create(UserDTO userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDTO(repository.save(user));
    }

    @Transactional
    public UserDTO update(Long id, UserDTO updatedUser) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFound("Usuário não encontrado!"));

        user.setName(updatedUser.getName());
        user.setPhone(updatedUser.getPhone());
        user.setEmail(updatedUser.getEmail());
        return userMapper.toDTO(user);
    }

    public List<TaskDTO> getTasksByGuest(Long userId) {
        List<Guest> invites = guestRepository.findByUserId(userId);
        return invites.stream().map(Guest::getTask).map(taskMapper::toDTO).collect(Collectors.toList());
    }
}
