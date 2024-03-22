package org.example.search.controller;

import org.example.search.pojo.GeneralResponse;
import org.example.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/merge/result")
    public ResponseEntity<GeneralResponse> performAsyncOperationsAndMergeResults() {
        Map<String, String> combinedResults = searchService.getCombinedResults();

        GeneralResponse response = new GeneralResponse<>();
        response.setCode(200);
        response.setTimestamp(new Date());
        response.setData(combinedResults);

        return ResponseEntity.ok(response);
    }
}

