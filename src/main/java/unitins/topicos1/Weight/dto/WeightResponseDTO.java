package unitins.topicos1.Weight.dto;

import unitins.topicos1.Weight.model.Weight;

public record WeightResponseDTO(

    Long id,
    Double weightValue,
    String unitOfMeasurement,
    String packageType,
    String description

) {
    public static WeightResponseDTO valueOf(Weight weight) {

        return new WeightResponseDTO(

            weight.getId(),
            weight.getWeightValue(),
            weight.getUnitOfMeasurement(),
            weight.getPackageType(),
            weight.getDescription()

        );

    }
    
}
