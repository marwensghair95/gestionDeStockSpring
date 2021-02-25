package fivePoints.spring.GestionDeStock.payload.responses;

import lombok.Data;
import lombok.NonNull;

@Data
public class MessageResponse {
    @NonNull
    private String message;
}
