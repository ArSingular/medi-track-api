package dev.kuch.MediTrackApi.util.mapper;

import dev.kuch.MediTrackApi.dto.request.CreateProductRequest;
import dev.kuch.MediTrackApi.dto.response.ProductResponse;
import dev.kuch.MediTrackApi.entity.Product;
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
public interface ProductMapper {

    @Mapping(target = "totalQuantity", source = "totalQuantity")
    ProductResponse toProductResponse(Product product, Integer totalQuantity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clinic", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Product toProduct(CreateProductRequest createProductRequest);

}
