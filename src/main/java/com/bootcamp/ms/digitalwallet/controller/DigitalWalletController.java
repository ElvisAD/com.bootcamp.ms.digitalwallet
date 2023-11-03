package com.bootcamp.ms.digitalwallet.controller;

import com.bootcamp.ms.digitalwallet.model.entity.DigitalWalletEntity;
import com.bootcamp.ms.digitalwallet.service.DigitalWalletService;
import io.reactivex.rxjava3.core.Maybe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase Controller que representa a un digitalwallet.
 */
@RestController
@RequestMapping("/v1/bootcamp/ms/digitalwallet")
@Slf4j
public class DigitalWalletController {

    @Autowired
    DigitalWalletService service;

    @PostMapping
    public Maybe<ResponseEntity<DigitalWalletEntity>> crearCard(@RequestBody DigitalWalletEntity entity) {
        return Maybe.just(entity)
                .flatMap(p -> service.create(p).toMaybe())
                .map(ResponseEntity::ok);
    }

}
