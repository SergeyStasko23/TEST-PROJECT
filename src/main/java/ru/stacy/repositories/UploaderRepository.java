package ru.stacy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stacy.entities.Image;

@Repository
public interface UploaderRepository extends JpaRepository<Image, Long> {
    Image findImageById(Long id);
}
