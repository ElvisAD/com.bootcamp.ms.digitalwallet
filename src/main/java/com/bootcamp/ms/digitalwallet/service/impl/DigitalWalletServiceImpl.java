package com.bootcamp.ms.digitalwallet.service.impl;

import com.bootcamp.ms.digitalwallet.mapper.IDigitalWalletMapper;
import com.bootcamp.ms.digitalwallet.model.dto.DigitalWalletDto;
import com.bootcamp.ms.digitalwallet.model.entity.DigitalWalletEntity;
import com.bootcamp.ms.digitalwallet.repository.DigitalWalletRepository;
import com.bootcamp.ms.digitalwallet.service.DigitalWalletService;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.adapter.rxjava.RxJava3Adapter;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
@Slf4j
public class DigitalWalletServiceImpl implements DigitalWalletService {

    @Autowired
    DigitalWalletRepository repository;

    @Autowired
    IDigitalWalletMapper mapper;

    @Override
    public Single<DigitalWalletDto> create(DigitalWalletDto digitalWalletDto) {
        //return RxJava3Adapter.monoToSingle(repository.save(cardEntity));
        return  RxJava3Adapter.monoToSingle(repository.findByNumberDocument(digitalWalletDto.getNumberDocument())
                        .switchIfEmpty(Mono.just(DigitalWalletEntity.builder().numberDocument("").build()))
                        .flatMap(clientEntity -> {
                            if(clientEntity.getNumberDocument().equalsIgnoreCase("")){
                                return repository.save(mapper.toDigitalWalletDtoAEntity(digitalWalletDto));
                            }else{
                                return Mono.error(new Throwable("El cliente ya se encuentra registrado: " + clientEntity.getNumberDocument()));
                            }
                        }))
                .doOnError(throwable -> log.error("do on Error,{}", throwable))
                .doOnSuccess(response -> log.info("do on Success,{}", response))
                .map(mapper::toDigitalWalletEntityADto);
    }

    @Override
    public Maybe<DigitalWalletDto> getDigitalWalletNumber(String numberDigitalWallet) {
        return Maybe.create(emitter -> {
            Mono<DigitalWalletDto> mono = repository.findByNumberDocument(numberDigitalWallet)
                    .map(mapper:: toDigitalWalletEntityADto)
                    .switchIfEmpty(Mono.error(new Exception("No se encontro cuenta en digital wallet")));
            mono.subscribe(
                    digitalWalletDto -> {
                        if (digitalWalletDto != null) {
                            emitter.onSuccess(digitalWalletDto);
                        } else {
                            emitter.onComplete();
                        }
                    },
                    emitter::onError
            );
        });
    }

    @Override
    public Completable actualizarSaldoDigitalWallet(String numberDigitalWallet, double saldo) {
        return RxJava3Adapter.monoToCompletable(repository.findByNumberDocument(numberDigitalWallet)
                .flatMap(existingDigitalWallet ->{
                    existingDigitalWallet.setAmount(saldo);
                    return repository.save(existingDigitalWallet);
                })
                .doOnError(Throwable::printStackTrace)
        );
    }
}