package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.validation.ValidDnaSequence;

@Data
@Schema(description = "Request para verificar si un ADN es mutante")

public class DnaRequest {

    @Schema(
            description = "Secuencia de ADN representada como matriz NxN",
            example = "[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]",
            required = true
    )


    @NotNull(message = "El DNA no puede ser nulo")
    @NotEmpty(message = "El DNA no puede estar vacío")
    @ValidDnaSequence
    private String[] dna;

    public DnaRequest() {
    }

    public DnaRequest(String[] dna) {
        this.dna = dna;
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}
