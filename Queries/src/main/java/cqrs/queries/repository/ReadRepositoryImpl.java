package cqrs.queries.repository;

import cqrs.queries.ReadStore.ReadStore;

public class ReadRepositoryImpl {
    private ReadStore readStore;


    public ReadRepositoryImpl (ReadStore readStore){
        this.readStore = readStore;
    }

}
