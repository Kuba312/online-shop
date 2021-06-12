package pl.me.shop.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Basket {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Product product;

    private Double quantity;

    @ManyToOne
    private User user;


}
