package az.edu.ada.wm2.resfuldemo.model.mapper;

import az.edu.ada.wm2.resfuldemo.model.dto.ProductDto;
import az.edu.ada.wm2.resfuldemo.model.entity.Product;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductMapperManual {

    public static ProductDto productToProductDto(Product prod) {
        if (prod == null) return null;

//        ProductDto prodDto = new ProductDto();
//        prodDto.setId(prod.getId());
//        prodDto.setName(prod.getName());
//        prodDto.setDescription(prod.getDescription());
//        prodDto.setPrice(prod.getPricePerItem());

        ProductDto prodDto = ProductDto.builder()
                .id(prod.getId())
                .name(prod.getName())
                .description(prod.getDescription())
                .price(prod.getPricePerItem())
                .build();

        return prodDto;
    }

    public static Product productDtoToProduct(ProductDto prodDto) {
        if (prodDto == null) return null;

        return Product.builder()
                .id(prodDto.getId())
                .name(prodDto.getName())
                .description(prodDto.getDescription())
                .pricePerItem(prodDto.getPrice())
                .build();
    }

    public static Collection<ProductDto> productsToProductDtos(Collection<Product> products) {
        //validate the input
        return products.stream()
                .map(ent -> productToProductDto(ent))
                .collect(Collectors.toList());
    }
}
