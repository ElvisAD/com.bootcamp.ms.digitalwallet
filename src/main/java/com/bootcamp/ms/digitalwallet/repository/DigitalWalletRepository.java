package com.bootcamp.ms.digitalwallet.repository;

import com.bootcamp.ms.digitalwallet.model.entity.DigitalWalletEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface DigitalWalletRepository extends ReactiveMongoRepository<DigitalWalletEntity, String> {
    Mono<DigitalWalletEntity> findByTelephono(String telephono);
}