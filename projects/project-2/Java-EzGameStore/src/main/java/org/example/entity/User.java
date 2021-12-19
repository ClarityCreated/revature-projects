package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String password;
    private String username;
    @Transient
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles",foreignKey = @ForeignKey(name="user_id"))
    private List<String> authorities;

    public String getUsername() {
        return username;
    }


}
