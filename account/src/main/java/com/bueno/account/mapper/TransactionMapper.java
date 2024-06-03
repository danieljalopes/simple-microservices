package com.bueno.account.mapper;


import com.bueno.account.dto.ItemExtratoResponseDto;
import com.bueno.account.dto.TransactionDto;
import com.bueno.account.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {AccountMapper.class}
)
public interface TransactionMapper {

    TransactionDto toDto(Transaction entity);

    @Mapping(target = "id", ignore = true)
    Transaction toEntity(TransactionDto dto);

    ItemExtratoResponseDto toExtratoItemDto(Transaction transaction);
}