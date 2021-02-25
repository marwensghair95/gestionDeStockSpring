package fivePoints.spring.GestionDeStock.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "roles")
@NoArgsConstructor
@RequiredArgsConstructor
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private Integer _id;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ERole name;


}
