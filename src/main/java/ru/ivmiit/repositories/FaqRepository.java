package ru.ivmiit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivmiit.models.FaqEntity;

public interface FaqRepository extends JpaRepository<FaqEntity, Long> {
}
