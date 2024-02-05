package java12.entity;

import jakarta.persistence.*;
import java12.BaseEntity;
import java12.enums.Gender;
import lombok.*;

import java.util.Date;
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
@ToString

@SequenceGenerator(name="base_id_gen",sequenceName = "owner_seq",allocationSize = 1)
@Table(name="owner",uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class Owner extends BaseEntity {
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    private String email;
    @Column(name="date_of_birth")
    private Date dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;



    @ManyToMany(mappedBy = "AGowners")
    private List<Agency> agencies;
    @OneToMany(mappedBy = "owner",cascade = {PERSIST})
    private List<House>houses;
    @OneToMany(mappedBy = "owner")
    private List<Rent_Info>rent_infos;

}
