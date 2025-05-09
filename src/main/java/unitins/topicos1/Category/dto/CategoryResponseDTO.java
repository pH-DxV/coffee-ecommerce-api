package unitins.topicos1.Category.dto;

import java.time.LocalDateTime;

import unitins.topicos1.Category.model.Category;

public record CategoryResponseDTO
(

    Long id,
    String name,
    String description,
    String roastLevel,
    String origin,
    LocalDateTime dateRegister,
    LocalDateTime dateChange

) 
{

    public static CategoryResponseDTO valueof(Category category){

        return new CategoryResponseDTO
                                    (

                                        category.getId(),
                                        category.getName(),
                                        category.getDescription(),
                                        category.getRoastLevel(),
                                        category.getOrigin(),
                                        category.getDateRegister(),
                                        category.getDateChange()

                                    );
                                    
    }

}