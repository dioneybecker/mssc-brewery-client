package guru.springframework.msscbreweryclient.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jt on 2019-04-20.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto{

    @Null
    private UUID id;

    @Null
    private Integer version;

    @Null
    private OffsetDateTime createdDate;

    @Null
    private OffsetDateTime updatedDate;

    @NotBlank
    private String beerName;
    
    @NotNull
    private BeerStyleEnum beerStyle;
    
    @Positive
    @NotNull
    private Long UPC;

    @Positive
    @NotNull
    private BigDecimal price;
    private Integer quantityOnHand;
}