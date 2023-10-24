package com.example.reader;

import com.example.dto.ProductInDto;
import org.springframework.batch.item.*;
import org.springframework.stereotype.Component;

@Component
public class FileItemReader implements ItemReader<ProductInDto> {
    int i=0;

    @Override
    public ProductInDto read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        //lire une BDD
        //ou lire un fichier
        // ....
        while (i<36){
            i++;
            return new ProductInDto("nomProduit", "descrption");
        }
        return null;

    }
}
