package dev.kuch.MediTrackApi.util.mapper;

import dev.kuch.MediTrackApi.dto.response.TransactionResponse;
import dev.kuch.MediTrackApi.entity.InventoryTransaction;
import dev.kuch.MediTrackApi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
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
    @Mapping(target = "userName", source = "user", qualifiedByName = "getFullName")
    TransactionResponse toResponse(InventoryTransaction inventoryTransaction);

    @Named("getFullName")
    default String getFullName(User user){
        if(user == null) return "UNKNOWN";

        if(user.getFirstName() == null && user.getLastName() == null){
            return user.getEmail();
        }

        String firstName = user.getFirstName() != null ? user.getFirstName() : "";
        String lastName = user.getLastName() != null ? user.getLastName() : "";

        return (firstName + " " + lastName).trim();
    }

}
