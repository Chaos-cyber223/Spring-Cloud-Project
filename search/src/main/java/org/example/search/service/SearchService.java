package org.example.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class SearchService {

    private final WebClient.Builder webClientBuilder;

    @Autowired
    public SearchService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }


    public CompletableFuture<String> callEmpDeptService() {
        return webClientBuilder.build().get()
                .uri("http://empdepartment/employees")
                .retrieve()
                .bodyToMono(String.class)
                .toFuture();
    }

    public CompletableFuture<String> callDetailsPortService() {
        return webClientBuilder.build().get()
                .uri("http://details/details/port")
                .retrieve()
                .bodyToMono(String.class)
                .toFuture();
    }

    public Map<String, String> getCombinedResults() {
        CompletableFuture<String> empDeptFuture = callEmpDeptService();
        CompletableFuture<String> detailsPortFuture = callDetailsPortService();

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(empDeptFuture, detailsPortFuture);

        Map<String, String> combinedResults = allFutures.thenApply(voided -> {
            Map<String, String> results = new HashMap<>();
            results.put("empDeptServiceResult", empDeptFuture.join());
            results.put("detailsPortServiceResult", detailsPortFuture.join());
            return results;
        }).join();

        return combinedResults;
    }
}

