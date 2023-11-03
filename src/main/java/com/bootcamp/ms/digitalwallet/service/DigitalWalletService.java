package com.bootcamp.ms.digitalwallet.service;

import com.bootcamp.ms.digitalwallet.model.entity.DigitalWalletEntity;
import io.reactivex.rxjava3.core.Single;

public interface DigitalWalletService {
    Single<DigitalWalletEntity> create(DigitalWalletEntity cardEntity);
}