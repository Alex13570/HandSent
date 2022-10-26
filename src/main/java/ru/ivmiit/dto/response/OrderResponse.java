package ru.ivmiit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Long id;

    private String fromAddress;

    private String toAddress;

    private Integer volume;

    private Integer distance;

    private UserResponse customer;

    private ProductResponse product;

}