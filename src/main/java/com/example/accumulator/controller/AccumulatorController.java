package com.example.accumulator.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccumulatorController {
    private int total = 0;
    private int quota = 1000;

    @PostMapping("/consume")
    public ResponseEntity<Map<String, Integer>> addToTotal(@RequestBody Map<String, Integer> request) {
        int value = request.get("value");
        if (total + value > quota) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        total += value;
        int remaining = quota - total;
        Map<String, Integer> response = new HashMap<>();
        response.put("total", total);
        response.put("remaining", remaining);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/consume")
    public ResponseEntity<Map<String, Integer>> getTotal() {
        int remaining = quota - total;
        Map<String, Integer> response = new HashMap<>();
        response.put("total", total);
        response.put("remaining", remaining);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/reset")
    public ResponseEntity<Map<String, Integer>> resetTotal(@RequestBody Map<String, Integer> request) {
        int newQuota = request.get("quota");
        if (newQuota < total) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        quota = newQuota;
        total = 0;
        int remaining = quota - total;
        Map<String, Integer> response = new HashMap<>();
        response.put("total", total);
        response.put("remaining", remaining);
        return ResponseEntity.ok().body(response);
    }
}
