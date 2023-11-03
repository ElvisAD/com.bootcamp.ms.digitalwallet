package com.bootcamp.ms.digitalwallet.repository;

import com.bootcamp.ms.digitalwallet.model.entity.DigitalWalletEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DigitalWalletRepository extends ReactiveMongoRepository<DigitalWalletEntity, String> {
}
