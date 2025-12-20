package dev.kuch.MediTrackApi.util.mapper;

import dev.kuch.MediTrackApi.dto.request.RestockRequest;
import dev.kuch.MediTrackApi.entity.Batch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.stereotype.Component;

/**
 * @author Artur Korol
 */

@Mapper(componentModel = "spring", nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
@Component
public interface BatchMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clinic", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "currentQuantity", source = "quantity")
    @Mapping(target = "initialQuantity", source = "quantity")
    @Mapping(target = "createdAt", ignore = true)
    Batch toBatch(RestockRequest restockRequest);

}
