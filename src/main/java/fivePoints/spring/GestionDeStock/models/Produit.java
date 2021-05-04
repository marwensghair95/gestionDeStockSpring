package fivePoints.spring.GestionDeStock.models;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "produits")
@NoArgsConstructor
@RequiredArgsConstructor
//@EqualsAndHashCode(exclude = {"pack"})
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private long id;

    @NonNull
    @Column(name = "refProduit")
    private String refProduit;
    @NonNull
    @Column(name = "nameProduit")
    private String nameProduit;
    @NonNull
    @Column(name = "qteProduit")
    private int qteProduit;
    @NonNull
    @Column(name = "prixDachat")
    private double prixDachat;
    @NonNull
    @Column(name = "prixVente")
    private double prixVente;
    @NonNull
    @Column(name = "idFournisseur")
    private String idFournisseur;
    @NonNull
    @Column(name = "idCat")
    private String idCat;

    // OneToMany Relations
    @JsonIgnore
    @OneToMany(mappedBy = "pack", cascade = CascadeType.ALL)
    private List<Produit> produits;

}
