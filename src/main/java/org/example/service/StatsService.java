package org.example.service;

import org.example.dto.StatsResponse;
import org.example.respository.DnaRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    private final DnaRecordRepository repository;

    public StatsService(DnaRecordRepository repository) {
        this.repository = repository;
    }

    public StatsResponse getStats() {

        long mutants = repository.countByIsMutant(true);
        long humans = repository.countByIsMutant(false);

        double ratio;

        if (humans == 0) {
            ratio = mutants > 0 ? mutants : 0.0;
        } else {
            ratio = (double) mutants / humans;
        }

        return new StatsResponse(mutants, humans, ratio);
    }
}
