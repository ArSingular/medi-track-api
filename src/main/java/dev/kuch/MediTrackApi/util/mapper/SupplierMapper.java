package dev.kuch.MediTrackApi.util.mapper;

import dev.kuch.MediTrackApi.dto.request.CreateSupplierRequest;
import dev.kuch.MediTrackApi.dto.response.SupplierResponse;
import dev.kuch.MediTrackApi.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.stereotype.Component;

/**
 * @author Korol Artur
 * 19.12.2025
 */


@Mapper(componentModel = "spring", nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
@Component
public interface SupplierMapper {

    @Mapping(target = "phone" , source = "phoneNumber")
    SupplierResponse toSupplierResponse(Supplier supplier);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clinic", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "phoneNumber", source = "phone")
    Supplier toSupplier(CreateSupplierRequest createSupplierRequest);


}
