package com.revature.revaturebookshelfjava.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "addresses")
@Slf4j
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String streetName;
    private String city;
    private String state;
    private int postalCode;
    // ManyToMany/ManyToOne Mappings
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "address_addressType",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name ="address_type_id")
    )
    private List<AddressType> types;
    //Commented out because of recursive nature
    @ManyToMany(mappedBy = "addresses")
    @JsonIgnore
    private List<User> user;

    public Address(String streetName, String city, String state, int postalCode) {
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    public Address(String streetName, String city, String state, int postalCode, List<AddressType> types) {
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.types = types;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode=" + postalCode +
                ", types=" + types +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return postalCode == address.postalCode
                && streetName.equals(address.streetName)
                && city.equals(address.city)
                && state.equals(address.state);
    }
}
