package com.revature.services;

import com.revature.models.Batch;

import java.util.List;
import java.util.Optional;

public interface BatchService {

    List<Batch> getBatches();

    Optional<Batch> getBatchByNumber(int id);

    List<Batch> getBatchByLocation(String location);

    Batch addBatch(Batch batch);

    Batch updateBatch(Batch batch);

    String deleteBatchByNumber(int number);
}
