package com.revature.revaturebookshelfjava.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "address_types")
public class AddressType implements Comparable<AddressType>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;

    @Override
    public int compareTo(AddressType o) {
        if(this.type.equals(o.getType())) {
            return 0;
        }
        return 1;
    }

    // Commented out because of recursive nature
//    @ManyToMany(mappedBy = "types")
//    private List<Address> addresses;

    public AddressType(String type) {
        this.type = type;
    }
}
