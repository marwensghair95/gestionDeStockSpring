package fivePoints.spring.GestionDeStock.models;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "fournisseurs")
@NoArgsConstructor
@RequiredArgsConstructor
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int _id;

    @NonNull
    @Column(name = "nameFournisseur")
    private String nameFournisseur;
    @NonNull
    @Column(name = "telpFournisseur")
    private String telpFournisseur;
    @NonNull
    @Column(name = "faxFournisseur")
    private String faxFournisseur;
    @NonNull
    @Column(name = "adresseFournisseur")
    private String adresseFournisseur;
    @NonNull
    @Column(name = "emailFournisseur")
    private String emailFournisseur;
    @NonNull
    @Column(name = "modepaimentFournisseur")
    private String modepaimentFournisseur;
    @NonNull
    @Column(name = "villeFournisseur")
    private String villeFournisseur;
    @NonNull
    @Column(name = "typeFournisseur")
    private String typeFournisseur;
    @NonNull
    @Column(name = "favorisFournisseur")
    private Boolean favorisFournisseur;
    @NonNull
    @Column(name = "refFournisseur")
    private String refFournisseur;


}
