package org.example.search.service;

import java.util.concurrent.CompletableFuture;

public interface SearchServiceInterface {
    public CompletableFuture<String> callEmpDeptService();
    public CompletableFuture<String> callDetailsPortService();
}
