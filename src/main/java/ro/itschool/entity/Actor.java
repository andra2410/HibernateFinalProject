package ro.itschool.entity;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "actors")

public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "actor_name", nullable = false)
    private String name;

    @Column(name = "actor_age", nullable = false)
    private Integer age;


    @OneToOne(cascade = CascadeType.ALL)
    private Manager manager;

    @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Kids> kids;

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", manager=" + manager +
                '}';
    }
}
