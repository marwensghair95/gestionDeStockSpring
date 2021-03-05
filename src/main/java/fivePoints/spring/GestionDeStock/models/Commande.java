package fivePoints.spring.GestionDeStock.models;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "commandes")
@NoArgsConstructor
@RequiredArgsConstructor
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int id;

    @NonNull
    @Column(name = "refCommande")
    private String refCommande;
    @NonNull
    @Column(name = "montant_total")
    private double montant_total;
//    @NonNull
//    @Column(name = "idClient")
//    private int idClient;
    @NonNull
    @Column(name = "date_commande")
    private Date date_commande;
    @NonNull
    @Column(name = "valide")
    private Boolean valide;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "commande")
    private Collection<CommandeItem> commandeItems;
}
