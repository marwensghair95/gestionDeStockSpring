package fivePoints.spring.GestionDeStock.payload.requests;

import fivePoints.spring.GestionDeStock.models.Client;
import fivePoints.spring.GestionDeStock.models.CommandeItem;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;


@Data
public class CommandeRequest {
    private int idClient;
    @NotBlank
    private String refCommande;
    private double montant_total;
    private Date date_commande;
    private Boolean valide;
    private Client client;


    private Set<CommandeItem> commandeItems;


}
