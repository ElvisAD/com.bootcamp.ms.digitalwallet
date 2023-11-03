package com.bootcamp.ms.digitalwallet.model.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@ToString
public class DigitalWalletDto {

    /**
     * Identificador único de la persona.
     */
    private int typeDocument;
    private String descriptionDocument;
    private String numberDocument;
    private String name;
    private String lastName;
    private String telephono;
    private String imeiTelephone;
    private String correo;
    private double amount;
    private boolean associationCard;
}
