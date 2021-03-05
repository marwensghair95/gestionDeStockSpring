package fivePoints.spring.GestionDeStock.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "roles")
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"users"})
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private long id;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private ERole name;
}
