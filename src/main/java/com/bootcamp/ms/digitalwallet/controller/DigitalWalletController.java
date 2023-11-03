package com.bootcamp.ms.digitalwallet.controller;

import com.bootcamp.ms.digitalwallet.model.dto.DigitalWalletDto;
import com.bootcamp.ms.digitalwallet.model.entity.DigitalWalletEntity;
import com.bootcamp.ms.digitalwallet.service.DigitalWalletService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Maybe<ResponseEntity<DigitalWalletDto>> crearCard(@RequestBody DigitalWalletDto digitalWalletDto) {
        return Maybe.just(digitalWalletDto)
                .flatMap(p -> service.create(p).toMaybe())
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{numberWallet}")
    public Maybe<ResponseEntity<DigitalWalletDto>> obtenerNUmberDigitalWallet(@PathVariable String numberWallet) {

        return service.getDigitalWalletNumber(numberWallet)
                .observeOn(Schedulers.io())
                .map(digitalWallet -> ResponseEntity.ok(digitalWallet))
                .doOnError(throwable -> log.error("do on Error,{}", throwable))
                .doOnSuccess(response -> log.info("do on Success,{}", response))
                .onErrorResumeNext(throwable -> throwable.getMessage().equalsIgnoreCase("F1") ?
                        Maybe.just(ResponseEntity.notFound().build()) :
                        Maybe.just(ResponseEntity.internalServerError().build()));
    }
    @PatchMapping("/{numberWallet}")
    public Completable actualizarDigitalWallet(
            @PathVariable String numberWallet,
            @RequestParam double valor) {
        return service.actualizarSaldoDigitalWallet(numberWallet, valor);
    }
}
