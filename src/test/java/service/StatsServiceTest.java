package service;

import org.example.dto.StatsResponse;
import org.example.respository.DnaRecordRepository;
import org.example.service.StatsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatsServiceTest {

    @Mock
    private DnaRecordRepository dnaRecordRepository;

    @InjectMocks
    private StatsService statsService;

    //TEST 1
    @Test
    @DisplayName("Debe calcular estadísticas correctamente")
    void testGetStatsWithData() {

        when(dnaRecordRepository.countByIsMutant(true)).thenReturn(40L);
        when(dnaRecordRepository.countByIsMutant(false)).thenReturn(100L);

        StatsResponse stats = statsService.getStats();

        assertEquals(40, stats.getCountMutantDna());
        assertEquals(100, stats.getCountHumanDna());
        assertEquals(0.4, stats.getRatio(), 0.001);
    }

    // TEST 2
    @Test
    @DisplayName("Debe retornar ratio 0 cuando no hay humanos")
    void testGetStatsWithNoHumans() {

        when(dnaRecordRepository.countByIsMutant(true)).thenReturn(10L);
        when(dnaRecordRepository.countByIsMutant(false)).thenReturn(0L);

        StatsResponse stats = statsService.getStats();

        assertEquals(10, stats.getCountMutantDna());
        assertEquals(0, stats.getCountHumanDna());
        assertEquals(10.0, stats.getRatio(), 0.001);
    }

    //TEST 3
    @Test
    @DisplayName("Debe retornar ratio 0 cuando no hay datos")
    void testGetStatsWithNoData() {

        when(dnaRecordRepository.countByIsMutant(true)).thenReturn(0L);
        when(dnaRecordRepository.countByIsMutant(false)).thenReturn(0L);

        StatsResponse stats = statsService.getStats();

        assertEquals(0, stats.getCountMutantDna());
        assertEquals(0, stats.getCountHumanDna());
        assertEquals(0.0, stats.getRatio(), 0.001);
    }

    // TEST 4
    @Test
    @DisplayName("Debe calcular ratio con decimales correctamente")
    void testGetStatsWithDecimalRatio() {

        when(dnaRecordRepository.countByIsMutant(true)).thenReturn(1L);
        when(dnaRecordRepository.countByIsMutant(false)).thenReturn(3L);

        StatsResponse stats = statsService.getStats();

        assertEquals(1, stats.getCountMutantDna());
        assertEquals(3, stats.getCountHumanDna());
        assertEquals(0.333, stats.getRatio(), 0.001);
    }

    // TEST 5
    @Test
    @DisplayName("Debe retornar ratio 1.0 cuando hay igual cantidad")
    void testGetStatsWithEqualCounts() {

        when(dnaRecordRepository.countByIsMutant(true)).thenReturn(50L);
        when(dnaRecordRepository.countByIsMutant(false)).thenReturn(50L);

        StatsResponse stats = statsService.getStats();

        assertEquals(50, stats.getCountMutantDna());
        assertEquals(50, stats.getCountHumanDna());
        assertEquals(1.0, stats.getRatio(), 0.001);
    }

    // TEST 6
    @Test
    @DisplayName("Debe manejar grandes cantidades de datos")
    void testGetStatsWithLargeNumbers() {

        when(dnaRecordRepository.countByIsMutant(true)).thenReturn(1_000_000L);
        when(dnaRecordRepository.countByIsMutant(false)).thenReturn(2_000_000L);

        StatsResponse stats = statsService.getStats();

        assertEquals(1_000_000, stats.getCountMutantDna());
        assertEquals(2_000_000, stats.getCountHumanDna());
        assertEquals(0.5, stats.getRatio(), 0.001);
    }
}
