package dev.kuch.MediTrackApi.util.mapper;

import dev.kuch.MediTrackApi.dto.response.TransactionResponse;
import dev.kuch.MediTrackApi.entity.InventoryTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.stereotype.Component;

/**
 * @author Korol Artur
 * 21.12.2025
 */

@Mapper(componentModel = "spring", nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
@Component
public interface TransactionMapper {

    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "batchNumber", source = "batch.batchNumber")
    @Mapping(target = "timestamp", source = "createdAt")
    @Mapping(target = "userName", source = "user.email")
    TransactionResponse toResponse(InventoryTransaction inventoryTransaction);

}
