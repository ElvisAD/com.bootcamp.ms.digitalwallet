package com.bootcamp.ms.digitalwallet.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "digitalwallet")
public class DigitalWalletEntity {

    /**
     * Identificador único de la persona.
     */
    @Id
    public String id;
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