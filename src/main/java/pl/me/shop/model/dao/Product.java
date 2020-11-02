package pl.me.shop.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity                         //Zmienia obiekt na string i na odwrót: seriazlizacja stringa na obiket javy
public class Product  implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String productName;
    private int price;
    private Double quantity;

}
