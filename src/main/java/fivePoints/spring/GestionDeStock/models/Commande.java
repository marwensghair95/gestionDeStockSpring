package fivePoints.spring.GestionDeStock.models;
import lombok.*;

import javax.persistence.*;


@Data
@Entity
@Table(name = "commandes")
@NoArgsConstructor
@RequiredArgsConstructor
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int _id;

    @NonNull
    @Column(name = "refCommande")
    private String refCommande;
    @NonNull
    @Column(name = "montant_total")
    private String montant_total;
    @NonNull
    @Column(name = "idClient")
    private int idClient;
    @NonNull
    @Column(name = "date_commande")
    private String date_commande;
    @NonNull
    @Column(name = "valide")
    private Boolean valide;
    @NonNull
    @Column(name = "produitRows")
    private String produitRows;
}
