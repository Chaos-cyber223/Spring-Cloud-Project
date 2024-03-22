package org.example.search.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.example.search.pojo.GeneralResponse;
import org.example.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/merge/result")
    @HystrixCommand(fallbackMethod = "getFallbackForMergeResults",
                commandProperties = {
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",
                            value = "5000")
                })
    public ResponseEntity<GeneralResponse> performAsyncOperationsAndMergeResults() {
        Map<String, String> combinedResults = searchService.getCombinedResults();

        GeneralResponse response = new GeneralResponse<>();
        response.setCode(200);
        response.setTimestamp(new Date());
        response.setData(combinedResults);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<GeneralResponse> getFallbackForMergeResults() {
        GeneralResponse<Map<String, String>> fallbackResponse = new GeneralResponse<>();
        fallbackResponse.setCode(503);
        fallbackResponse.setTimestamp(new Date());
        fallbackResponse.setData(Collections.emptyMap());

        return ResponseEntity.ok(fallbackResponse);
    }
}

