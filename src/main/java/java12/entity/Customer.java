package java12.entity;

import jakarta.persistence.*;
import java12.BaseEntity;
import java12.enums.FamilyStatus;
import java12.enums.Gender;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor


@SequenceGenerator(name = "base_id_gen", sequenceName = "customer_seq", allocationSize = 1)
@Table(name = "customer")
public class Customer extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private FamilyStatus familyStatus;



    @OneToMany(mappedBy = "customer",cascade = {PERSIST})
    private List<Rent_Info>rent_infos;

    public Customer(String firstName, String lastName, String email, Date dateOfBirth, Gender gender, String nationality, FamilyStatus familyStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.nationality = nationality;
        this.familyStatus = familyStatus;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", nationality='" + nationality + '\'' +
                ", familyStatus=" + familyStatus +
                ", rent_infos=" + rent_infos +
                '}';
    }
}
