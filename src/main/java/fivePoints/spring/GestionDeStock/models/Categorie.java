package fivePoints.spring.GestionDeStock.models;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "categories")
@NoArgsConstructor
@RequiredArgsConstructor
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int _id;

    @NonNull
    @Column(name = "refCat")
    private String refCat;
    @NonNull
    @Column(name = "nameCat")
    private String nameCat;

}
