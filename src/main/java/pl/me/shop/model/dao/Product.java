package pl.me.shop.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String productName;
    private int price;
    private Double quantity;

}
