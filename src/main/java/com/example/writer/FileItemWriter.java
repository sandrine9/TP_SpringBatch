package com.example.writer;

import com.example.dto.ProductOutDto;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileItemWriter implements ItemWriter<ProductOutDto> {
    @Override
    public void write(List<? extends ProductOutDto> list) throws Exception {
        //écrire dans un fichier
        //écrire dans une BDD
        //...
        System.out.println(list.size());
    }
}
