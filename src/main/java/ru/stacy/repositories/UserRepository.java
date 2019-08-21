package ru.stacy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.stacy.entities.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    List<User> findByUserState(Boolean userState);
    List<User> findByUnixTimestampAfter(Long unixTimestamp);
    List<User> findByUserStateAndUnixTimestampAfter(Boolean userState, Long unixTimestamp);
}