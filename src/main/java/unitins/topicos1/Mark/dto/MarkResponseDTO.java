package unitins.topicos1.Mark.dto;

import unitins.topicos1.Mark.model.Mark;

public record MarkResponseDTO(

    Long id,
    String name,
    String logo

) {
    public static MarkResponseDTO valueOf(Mark mark){

        return new MarkResponseDTO(
            
                                    mark.getId(),
                                    mark.getName(),
                                    mark.getLogo()

        );

    }

}