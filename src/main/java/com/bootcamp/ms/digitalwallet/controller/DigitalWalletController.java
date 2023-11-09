package com.bootcamp.ms.digitalwallet.controller;

import com.bootcamp.ms.digitalwallet.model.dto.DigitalWalletDto;
import com.bootcamp.ms.digitalwallet.service.DigitalWalletService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "Billetera Digital Controller")
public class DigitalWalletController {

    @Autowired
    DigitalWalletService service;

    @PostMapping
    @ApiOperation(value = "Crea una billetera digital", response = DigitalWalletDto.class, responseContainer = "List")
    public Maybe<ResponseEntity<DigitalWalletDto>> crearWallet(@RequestBody DigitalWalletDto digitalWalletDto) {
        return Maybe.just(digitalWalletDto)
                .flatMap(p -> service.create(p).toMaybe())
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{numberPhoneWallet}")
    @ApiOperation(value = "Obtiene una billetera digital por su numero", response = DigitalWalletDto.class, responseContainer = "List")
    public Maybe<ResponseEntity<DigitalWalletDto>> obtenerNumberDigitalWallet(@PathVariable String numberPhoneWallet) {

        return service.getDigitalWalletPhone(numberPhoneWallet)
                .observeOn(Schedulers.io())
                .map(digitalWallet -> ResponseEntity.ok(digitalWallet))
                .doOnError(throwable -> log.error("do on Error,{}", throwable))
                .doOnSuccess(response -> log.info("do on Success,{}", response))
                .onErrorResumeNext(throwable -> throwable.getMessage().equalsIgnoreCase("F1") ?
                        Maybe.just(ResponseEntity.notFound().build()) :
                        Maybe.just(ResponseEntity.internalServerError().build()));
    }
    @PatchMapping("/{phoneDigitalWallet}")
    @ApiOperation(value = "Actualiza el saldo de una billetera digital por numero telefono", response = DigitalWalletDto.class, responseContainer = "List")
    public Completable actualizarDigitalWallet(
            @PathVariable String phoneDigitalWallet,
            @RequestParam double valor) {
        return service.actualizarSaldoDigitalWallet(phoneDigitalWallet, valor);
    }
}
