package C043.PowerUp.repositories;

import C043.PowerUp.entities.BackLog;
import C043.PowerUp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BackLogRepository extends JpaRepository<BackLog, Integer> {
    BackLog findByGameIdAndUser(int gameId, User user);

    List<BackLog> findAllByUser(User user);
}
