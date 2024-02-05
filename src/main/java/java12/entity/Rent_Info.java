package java12.entity;

import jakarta.persistence.*;
import java12.BaseEntity;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

@SequenceGenerator(name="base_id_gen",sequenceName = "rent_Info_seq",allocationSize = 1)
@Table(name="rent_Info")
public class Rent_Info extends BaseEntity {
    @Column(name="check_in")
    private Date checkIn;
    @Column(name="check_out")
    private Date checkOut;



    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Owner owner;
    @ManyToOne
    private Agency agency;
    @OneToOne
    private House house;
}
