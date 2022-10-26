package ru.ivmiit.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class NewsEntity extends BaseEntity {

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

}
