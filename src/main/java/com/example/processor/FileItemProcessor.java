package com.example.processor;

import com.example.dto.ProductInDto;
import com.example.dto.ProductOutDto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class FileItemProcessor implements ItemProcessor<ProductInDto, ProductOutDto> {
    @Override
    public ProductOutDto process(ProductInDto productInDto) throws Exception {
        //code métier
        return new ProductOutDto(productInDto.getNom(), productInDto.getDescription());
    }
}
