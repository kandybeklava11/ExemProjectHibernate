package java12.entity;

import jakarta.persistence.*;
import java12.BaseEntity;
import java12.enums.HouseType;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

@SequenceGenerator(name="base_id_gen",sequenceName = "house_seq",allocationSize = 1)
@Table(name="house")
public class House extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private HouseType houseType;
    private BigDecimal price;
    private double rating;
    private String description;
    private int room;
    private boolean furniture;



    @ManyToOne
    private Owner owner;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

    public House(HouseType houseType, BigDecimal price, double rating, String description, int room, boolean furniture,  Address address) {
        this.houseType = houseType;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.room = room;
        this.furniture = furniture;
        this.address = address;
    }
}
