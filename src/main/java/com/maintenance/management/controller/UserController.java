package com.maintenance.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maintenance.management.model.MaintenanceRequest;
import com.maintenance.management.service.RequestService;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    @Autowired
    private RequestService requestService;

    private HttpHeaders createCorsHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:3000");
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        return headers;
    }

    // Submit a new maintenance request
    @PostMapping("/request")
    public ResponseEntity<String> submitRequest(@RequestBody MaintenanceRequest request) {
        try {
            requestService.submitRequest(request);
            HttpHeaders headers = createCorsHeaders();
            return ResponseEntity.ok()
                .headers(headers)
                .body("Request submitted successfully");
        } catch (Exception e) {
            HttpHeaders headers = createCorsHeaders();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .headers(headers)
                .body("Error submitting request: " + e.getMessage());
        }
    }

    // View all requests for the logged-in user
    @GetMapping("/requests")
    public ResponseEntity<List<MaintenanceRequest>> getAllRequests() {
        List<MaintenanceRequest> requests = requestService.getRequestsForUser();
        HttpHeaders headers = createCorsHeaders();
        if (requests.isEmpty()) {
            return ResponseEntity.noContent()
                .headers(headers)
                .build();
        }
        return ResponseEntity.ok()
            .headers(headers)
            .body(requests);
    }

    // Edit a specific maintenance request
    @PutMapping("/request/{id}")
    public ResponseEntity<String> editRequest(@PathVariable Long id, @RequestBody MaintenanceRequest updatedRequest) {
        try {
            requestService.updateRequest(id, updatedRequest);
            HttpHeaders headers = createCorsHeaders();
            return ResponseEntity.ok()
                .headers(headers)
                .body("Request updated successfully");
        } catch (Exception e) {
            HttpHeaders headers = createCorsHeaders();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .headers(headers)
                .body("Error updating request: " + e.getMessage());
        }
    }

    // Delete a specific maintenance request
    @DeleteMapping("/request/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable Long id) {
        try {
            requestService.deleteRequest(id);
            HttpHeaders headers = createCorsHeaders();
            return ResponseEntity.ok()
                .headers(headers)
                .body("Request deleted successfully");
        } catch (Exception e) {
            HttpHeaders headers = createCorsHeaders();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .headers(headers)
                .body("Error deleting request: " + e.getMessage());
        }
    }
}
