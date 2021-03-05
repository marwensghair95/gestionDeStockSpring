package fivePoints.spring.GestionDeStock.models;

import lombok.*;


import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "packs")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"produits"})
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    @Column(name = "refPack")
    private String refPack;
    @NonNull
    @Column(name = "namePack")
    private String namePack;



    // OneToMany Relations
//    @ManyToOne
//    @JoinColumn(name="produit_Name")
//    private Produit produit;
}
