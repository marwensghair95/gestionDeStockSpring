package fivePoints.spring.GestionDeStock.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Null;


@Data
@Entity
@Table(name = "produits")
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"pack"})
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int id;

    @NonNull
    @Column(name = "refProduit")
    private String refProduit;
    @NonNull
    @Column(name = "nameProduit")
    private String nameProduit;
    @NonNull
    @Column(name = "qteProduit")
    private String qteProduit;
    @NonNull
    @Column(name = "prixDachat")
    private String prixDachat;
    @NonNull
    @Column(name = "prixVente")
    private String prixVente;
    @NonNull
    @Column(name = "idFournisseur")
    private String idFournisseur;
    @NonNull
    @Column(name = "idCat")
    private String idCat;

    // OneToMany Relations
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="pack_id", nullable = false)
    private Pack pack;
}
