package ru.vyazankin.persists;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="roles")
@NamedQueries({
        @NamedQuery(name = "findAllRoles", query = "from Role r"),
        @NamedQuery(name = "countAllRoles", query = "select count(*) from Role r"),
        @NamedQuery(name = "findRoleByName", query = "from Role r where name = :name"),
        @NamedQuery(name = "deleteRoleById", query = "delete from Role r where r.id = :id")
})
public class Role {

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rolename")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;


}
