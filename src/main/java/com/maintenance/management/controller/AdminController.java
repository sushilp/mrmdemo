package com.maintenance.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maintenance.management.service.RequestService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private RequestService requestService;

	@PutMapping("/approve/{id}")
	public ResponseEntity<String> approveRequest(@PathVariable Long id, @RequestBody String comments) {
		try {
			// Call service method to approve request
			requestService.approveRequest(id, comments);
			return ResponseEntity.ok("Request approved with comment: " + comments);
		} catch (Exception e) {
			// Handle any errors that occur during approval
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error approving request: " + e.getMessage());
		}
	}

	@PutMapping("/reject/{id}")
	public ResponseEntity<String> rejectRequest(@PathVariable Long id, @RequestBody String comments) {
		try {
			// Call service method to reject request
			requestService.rejectRequest(id, comments);
			return ResponseEntity.ok("Request rejected with comment: " + comments);
		} catch (Exception e) {
			// Handle any errors that occur during rejection
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error rejecting request: " + e.getMessage());
		}
	}
}
