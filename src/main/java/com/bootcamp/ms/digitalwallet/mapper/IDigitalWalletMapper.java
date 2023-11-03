package com.bootcamp.ms.digitalwallet.mapper;

import com.bootcamp.ms.digitalwallet.model.dto.DigitalWalletDto;
import com.bootcamp.ms.digitalwallet.model.entity.DigitalWalletEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface IDigitalWalletMapper {
    DigitalWalletEntity toDigitalWalletDtoAEntity(DigitalWalletDto digitalWalletDto);
    DigitalWalletDto toDigitalWalletEntityADto(DigitalWalletEntity digitalWalletEntity);
}