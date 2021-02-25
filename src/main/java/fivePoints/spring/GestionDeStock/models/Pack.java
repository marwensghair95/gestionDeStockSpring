package fivePoints.spring.GestionDeStock.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "packs")
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"produits"})
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int id;

    @NonNull
    @Column(name = "refPack")
    private String refPack;
    @NonNull
    @Column(name = "namePack")
    private String namePack;

    // OneToMany Relations
    @JsonIgnore
    @OneToMany(mappedBy = "pack", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @SortNatural
    private Set<Produit> produits = new HashSet<>();
}
