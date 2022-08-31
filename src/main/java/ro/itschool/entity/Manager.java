package ro.itschool.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity

public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private Integer price;

    @ToString.Exclude
    @OneToOne(mappedBy = "manager", cascade = CascadeType.ALL)
    private Actor actor;

}
