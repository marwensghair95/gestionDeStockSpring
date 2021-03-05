package fivePoints.spring.GestionDeStock.models;

import lombok.*;

import javax.persistence.*;


@Data
@Entity
@Table(name = "clients")
@NoArgsConstructor
@RequiredArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int id;

    @NonNull
    @Column(name = "nameClient")
    private String nameClient;
    @NonNull
    @Column(name = "telpClient")
    private String telpClient;
    @NonNull
    @Column(name = "emailClient")
    private String emailClient;
    @NonNull
    @Column(name = "faxClient")
    private String faxClient;
    @NonNull
    @Column(name = "refClient")
    private String refClient;
    @NonNull
    @Column(name = "modepaimentClient")
    private String modepaimentClient;
    @NonNull
    @Column(name = "adresseClient")
    private String adresseClient;
    @NonNull
    @Column(name = "villeClient")
    private String villeClient;
    @NonNull
    @Column(name = "typeClient")
    private String typeClient;
    @NonNull
    @Column(name = "favorisClient")
    private Boolean favorisClient;


}
