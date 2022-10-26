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
public class OrderEntity extends BaseEntity {

    private String fromAddress;

    private String toAddress;

    private Integer volume;

    private Integer distance;

    @JoinColumn(name = "customer_id")
    @ManyToOne
    private UserEntity customer;

    @JoinColumn(name = "product_id")
    @ManyToOne
    private ProductEntity product;

}
