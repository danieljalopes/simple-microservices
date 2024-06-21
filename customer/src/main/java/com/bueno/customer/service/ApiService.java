package com.bueno.customer.service;

import com.bueno.customer.dto.TransactionRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;


@Service
@Slf4j
public class ApiService {

    private final String url;
    private final TransactionGeneratorService transactionGeneratorService;
    private final RestTemplate restTemplate;
    private final ThreadPoolTaskScheduler taskScheduler;

    public ApiService(TransactionGeneratorService transactionGeneratorService,
                      @Value("${account.api.url}") String url, ThreadPoolTaskScheduler taskScheduler) {
        this.transactionGeneratorService = transactionGeneratorService;
        this.url = url;
        this.taskScheduler = taskScheduler;
        restTemplate = new RestTemplate();
        //START
        scheduleFixedDelayTask();
    }

    private void scheduleFixedDelayTask() {
        int numberOfTasks = 4;
        for (int i = 0; i < numberOfTasks; i++) {
            taskScheduler.scheduleAtFixedRate(this::sendData, Duration.ofMillis(100));
        }

    }

    public void sendData() {
        try {
            TransactionRequestDto request = transactionGeneratorService.generateTransaction();
            HttpEntity<TransactionRequestDto> requestEntity = new HttpEntity<>(request);
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
                    String.class);
        } catch (RestClientException e) {
            log.error("Error sending data {}", e.getMessage());
        }

    }
}
