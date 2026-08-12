package Tasks.Management.repository;

import Tasks.Management.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface GuestRepository extends JpaRepository<Guest, Long> {
    List<Guest> findByUserId(Long userId);
}
