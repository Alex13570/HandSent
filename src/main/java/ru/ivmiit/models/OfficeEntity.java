package ru.ivmiit.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OfficeEntity extends BaseEntity {

    private String title;

    private String address;

    private String latitude;

    private String longitude;

}
