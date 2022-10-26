package ru.ivmiit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivmiit.models.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
