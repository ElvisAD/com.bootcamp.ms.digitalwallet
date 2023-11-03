package com.bootcamp.ms.digitalwallet.service;

import com.bootcamp.ms.digitalwallet.model.dto.DigitalWalletDto;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface DigitalWalletService {
    Single<DigitalWalletDto> create(DigitalWalletDto digitalWalletDto);
    Maybe<DigitalWalletDto> getDigitalWalletNumber(String numberDigitalWallet);
    Completable actualizarSaldoDigitalWallet(String numberDigitalWallet, double saldo);
}