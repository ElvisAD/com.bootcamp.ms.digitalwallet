package com.bootcamp.ms.digitalwallet.service.impl;

import com.bootcamp.ms.digitalwallet.model.entity.DigitalWalletEntity;
import com.bootcamp.ms.digitalwallet.repository.DigitalWalletRepository;
import com.bootcamp.ms.digitalwallet.service.DigitalWalletService;
import io.reactivex.rxjava3.core.Single;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.adapter.rxjava.RxJava3Adapter;

@Component
@AllArgsConstructor
@Slf4j
public class DigitalWalletServiceImpl implements DigitalWalletService {

    @Autowired
    DigitalWalletRepository repository;

    @Override
    public Single<DigitalWalletEntity> create(DigitalWalletEntity cardEntity) {
        return RxJava3Adapter.monoToSingle(repository.save(cardEntity));
    }
}
