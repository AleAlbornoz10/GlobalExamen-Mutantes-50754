package org.example.service;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@Component
public class MutantDetector {

    private static final int SEQUENCE_LENGTH = 4;

    public boolean isMutant(String[] dna) {


        if (dna == null || dna.length < SEQUENCE_LENGTH) return false;

        final int n = dna.length;

        for (String fila : dna) {
            if (fila == null || fila.length() != n) return false;

            for (char c : fila.toCharArray()) {
                if (c != 'A' && c != 'T' && c != 'C' && c != 'G') {
                    return false;
                }
            }
        }

        // Convertir String[] a matriz char[][]
        char[][] matrix = new char[n][n];
        for (int i = 0; i < n; i++) {
            matrix[i] = dna[i].toCharArray();
        }

        int contSecuencia = 0;

        //busqueda de secuencia
        for (int fila = 0; fila < n; fila++) {
            for (int col = 0; col < n; col++) {

                // Horizontal
                if (col <= n - SEQUENCE_LENGTH && checkHorizontal(matrix, fila, col)) {
                    contSecuencia++;
                }

                // Vertical
                if (fila <= n - SEQUENCE_LENGTH && checkVertical(matrix, fila, col)) {
                    contSecuencia++;
                }

                // Diagonal
                if (fila <= n - SEQUENCE_LENGTH && col <= n - SEQUENCE_LENGTH
                        && checkDiagonalDescending(matrix, fila, col)) {
                    contSecuencia++;
                }

                // Diagonal
                if (fila >= SEQUENCE_LENGTH - 1 && col <= n - SEQUENCE_LENGTH
                        && checkDiagonalAscending(matrix, fila, col)) {
                    contSecuencia++;
                }

                if (contSecuencia >= 2) return true; // EARLY TERMINATION
            }
        }
        return false;
    }




    private boolean checkHorizontal(char[][] matrix, int fila, int col) {
        char base = matrix[fila][col];
        return matrix[fila][col + 1] == base &&
                matrix[fila][col + 2] == base &&
                matrix[fila][col + 3] == base;
    }

    private boolean checkVertical(char[][] matrix, int fila, int col) {
        char base = matrix[fila][col];
        return matrix[fila + 1][col] == base &&
                matrix[fila + 2][col] == base &&
                matrix[fila + 3][col] == base;
    }

    private boolean checkDiagonalDescending(char[][] matrix, int fila, int col) {
        char base = matrix[fila][col];
        return matrix[fila + 1][col + 1] == base &&
                matrix[fila + 2][col + 2] == base &&
                matrix[fila + 3][col + 3] == base;
    }

    private boolean checkDiagonalAscending(char[][] matrix, int fila, int col) {
        char base = matrix[fila][col];
        return matrix[fila - 1][col + 1] == base &&
                matrix[fila - 2][col + 2] == base &&
                matrix[fila - 3][col + 3] == base;
    }
}
