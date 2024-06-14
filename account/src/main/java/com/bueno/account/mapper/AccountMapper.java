package com.bueno.account.mapper;


import com.bueno.account.dto.AccountDto;
import com.bueno.account.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

/**
 * The AccountMapper interface is responsible for mapping between Account and AccountDto objects.
 * It uses MapStruct for automatic mapping implementation.
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface AccountMapper {

    AccountDto toDto(Account entity);

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "id", ignore = true)
    Account toEntity(AccountDto dto);
}