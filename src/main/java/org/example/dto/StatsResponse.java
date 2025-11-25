package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatsResponse {

    @Schema(description = "Cantidad de ADN mutante verificado")
    @JsonProperty("count_mutant_dna")
    private long countMutantDna;

    @Schema(description = "Cantidad de ADN humano verificado")
    @JsonProperty("count_human_dna")
    private long countHumanDna;

    @Schema(description = "Ratio: mutantes/humanos")
    private double ratio;
}
