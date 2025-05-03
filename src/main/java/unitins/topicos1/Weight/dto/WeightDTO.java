package unitins.topicos1.Weight.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WeightDTO(
    
    @NotNull(message = "Weight value cannot be null.")
    @DecimalMin(value = "0.01", message = "Weight must be greater than zero")
    Double weightValue,
    
    @NotBlank(message = "The unit of measurement cannot be null or empty")
    String unitOfMeasurement,
    
    @NotBlank(message = "The package type cannot be null or empty")
    String packageType,
    
    String description

) { }
