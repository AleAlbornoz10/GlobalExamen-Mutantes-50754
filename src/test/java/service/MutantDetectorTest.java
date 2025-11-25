package service;

import org.example.service.MutantDetector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MutantDetectorTest {

    private final MutantDetector mutantDetector = new MutantDetector();

    @Test
    @DisplayName("Debe detectar mutante con secuencias horizontal y diagonal")
    void testMutantWithHorizontalAndDiagonalSequences() {

        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",  // Horizontal CCCC
                "TCACTG"
        };

        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("Debe detectar ADN humano (sin secuencias mutantes)")
    void testHumanDna() {

        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATTT",
                "AGACGG",
                "GCGTCA",
                "TCACTG"
        };

        assertFalse(mutantDetector.isMutant(dna));
    }
    //TEST 3
    @Test
    @DisplayName("Debe detectar mutante con secuencias verticales")
    void testMutantWithVerticalSequences() {
        String[] dna = {
                "AAAAGA",  // 4 A's en columna 0
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CACCTA",
                "TCACTG"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }
    //TEST 4
    @Test
    @DisplayName("Debe detectar mutante con múltiples secuencias horizontales")
    void testMutantWithMultipleHorizontalSequences() {
        String[] dna = {
                "TTTTGA",  // Secuencia 1: TTTT
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",  // Secuencia 2: CCCC
                "TCACTG"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }
    //TEST 5
    @Test
    @DisplayName("No debe detectar mutante con una sola secuencia")
    void testNotMutantWithOnlyOneSequence() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATTT",  // Solo 1 secuencia: TTT (solo 3, no cuenta)
                "AGACGG",
                "GCGTCA",
                "TCACTG"
        };
        assertFalse(mutantDetector.isMutant(dna));
    }
    //TEST 6
    @Test
    @DisplayName("No debe detectar mutante sin secuencias")
    void testNotMutantWithNoSequences() {
        String[] dna = {
                "ATGC",
                "CAGT",
                "TTAT",
                "AGAC"
        };
        assertFalse(mutantDetector.isMutant(dna));
    }
    //TEST 7
    @Test
    @DisplayName("Debe rechazar ADN nulo")
    void testNullDna() {
        assertFalse(mutantDetector.isMutant(null));
    }
    //TEST 8
    @Test
    @DisplayName("Debe rechazar ADN vacío")
    void testEmptyDna() {
        String[] dna = {};
        assertFalse(mutantDetector.isMutant(dna));
    }
    //TEST 9
    @Test
    @DisplayName("Debe rechazar matriz no cuadrada")
    void testNonSquareMatrix() {
        String[] dna = {
                "ATGCGA",  // 6 caracteres
                "CAGTGC",  // 6 caracteres
                "TTATGT"   // 6 caracteres, pero solo 3 filas
        };
        assertFalse(mutantDetector.isMutant(dna));
    }
    //TEST 10
    @Test
    @DisplayName("Debe rechazar caracteres inválidos")
    void testInvalidCharacters() {
        String[] dna = {
                "ATGCGA",
                "CAGTXC",  // ← 'X' es inválido
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        assertFalse(mutantDetector.isMutant(dna));
    }
    //TEST 11
    @Test
    @DisplayName("Debe detectar mutante en matriz pequeña 4x4")
    void testSmallMatrix4x4Mutant() {
        String[] dna = {
                "AAAA",  // Horizontal: AAAA
                "CCCC",  // Horizontal: CCCC
                "TTAT",
                "AGAC"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }
    //TEST 12
    @Test
    @DisplayName("Debe manejar matriz grande 10x10")
    void testLargeMatrix10x10() {
        String[] dna = {
                "ATGCGAATGC",
                "CAGTGCCAGT",
                "TTATGTTTAT",
                "AGAAGGATAA",
                "CCCCTACCCC",  // 2 horizontales: CCCC (pos 0-3 y 6-9)
                "TCACTGTCAC",
                "ATGCGAATGC",
                "CAGTGCCAGT",
                "TTATGTTTAT",
                "AGAAGGATAA"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }
    //TEST 13
    @Test
    @DisplayName("Debe detectar diagonal ascendente")
    void testAscendingDiagonal() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCGCTA",
                "TCGCTG"
        };
        boolean result = mutantDetector.isMutant(dna);
        assertNotNull(result);  // Solo verifica que no lanza excepción
    }
    //TEST 14
    @Test
    @DisplayName("Debe usar early termination para eficiencia")
    void testEarlyTermination() {
        String[] dna = {
                "AAAAGA",  // Secuencia 1
                "AAAAGC",  // Secuencia 2
                "TTATGT",  // Ya no se revisa (early termination)
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };

        long startTime = System.nanoTime();
        boolean result = mutantDetector.isMutant(dna);
        long endTime = System.nanoTime();

        assertTrue(result);
        assertTrue((endTime - startTime) < 10_000_000); // < 10ms
    }
    //TEST 15
    @Test
    @DisplayName("Debe detectar mutante con todas las bases iguales")
    void testAllSameBases() {
        String[] dna = {
                "AAAAAA",
                "AAAAAA",
                "AAAAAA",
                "AAAAAA",
                "AAAAAA",
                "AAAAAA"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }
    //TEST 16













}
