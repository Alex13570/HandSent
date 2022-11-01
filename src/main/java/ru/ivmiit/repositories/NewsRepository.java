package ru.ivmiit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivmiit.models.NewsEntity;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
}
