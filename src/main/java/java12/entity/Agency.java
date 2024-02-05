package java12.entity;

import jakarta.persistence.*;
import java12.BaseEntity;
import lombok.*;


import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor


@SequenceGenerator(name="base_id_gen",sequenceName = "agency_seq",allocationSize = 1)
@Table(name="agency")
public class Agency extends BaseEntity {
    private String name;
    @Column(name="phone_number",length = 13)
    private String phoneNumber;


    @OneToOne(cascade = {PERSIST,REMOVE})
    private Address address;
    @ManyToMany
    private List<Owner>AGowners;
    @OneToMany(mappedBy = "agency",cascade = {REMOVE})
    private List<Rent_Info>rent_infos;

    public Agency(String name, String phoneNumber, Address address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Agency{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address +
                '}';
    }
}
