package java12.entity;

import jakarta.persistence.*;
import java12.BaseEntity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

@SequenceGenerator(name="base_id_gen",sequenceName = "address_seq",allocationSize = 1)
@Table(name="address", uniqueConstraints = {@UniqueConstraint(columnNames = "street")})

public class Address extends BaseEntity {
    private String city;
    private String region;
    private String street;




    @OneToOne
    private Agency agency;

    public Address(String city, String region, String street) {
        this.city = city;
        this.region = region;
        this.street = street;
    }


}
