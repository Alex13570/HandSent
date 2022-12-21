package ru.ivmiit.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProductEntity extends BaseEntity {

    private String title;

    @Column(columnDefinition = "varchar(1000)")
    private String description;

    @Column(columnDefinition = "decimal(10,2)")
    private Integer price;

}
