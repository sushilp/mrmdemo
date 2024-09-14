package com.maintenance.management.service;

import com.maintenance.management.model.MaintenanceRequest;
import com.maintenance.management.model.RequestStatus;
import com.maintenance.management.repository.MaintenanceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private MaintenanceRequestRepository requestRepository;

    // Method for customers to submit a maintenance request
    public void submitRequest(MaintenanceRequest request) {
        // Set the owner of the request to the currently logged-in user
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        request.setOwner(username);

        requestRepository.save(request);
    }

    // Method for users to view all their requests
    public List<MaintenanceRequest> getRequestsForUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        System.out.println("Getting requests for username: "+ username);
        return requestRepository.findByOwner(username);
    }

    // Method to update a request
    public void updateRequest(Long id, MaintenanceRequest updatedRequest) {
        Optional<MaintenanceRequest> existingRequestOpt = requestRepository.findById(id);
        if (existingRequestOpt.isPresent()) {
            MaintenanceRequest existingRequest = existingRequestOpt.get();
            existingRequest.setType(updatedRequest.getType());
            existingRequest.setPriority(updatedRequest.getPriority());
            existingRequest.setDescription(updatedRequest.getDescription());
            existingRequest.setOwner(updatedRequest.getOwner());
            existingRequest.setApprover(updatedRequest.getApprover());
            existingRequest.setDatetimeCreated(updatedRequest.getDatetimeCreated());

            requestRepository.save(existingRequest);
        } else {
            throw new RuntimeException("Request not found with id: " + id);
        }
    }

    // Method to delete a request
    public void deleteRequest(Long id) {
        Optional<MaintenanceRequest> existingRequestOpt = requestRepository.findById(id);
        if (existingRequestOpt.isPresent()) {
            requestRepository.deleteById(id);
        } else {
            throw new RuntimeException("Request not found with id: " + id);
        }
    }

    // Method to approve a request
    public void approveRequest(Long id, String comments) {
        Optional<MaintenanceRequest> existingRequestOpt = requestRepository.findById(id);
        if (existingRequestOpt.isPresent()) {
            MaintenanceRequest request = existingRequestOpt.get();
            request.setStatus(RequestStatus.APPROVED);
            request.setApprovalComments(comments);
            requestRepository.save(request);
        } else {
            throw new RuntimeException("Request not found with id: " + id);
        }
    }

    // Method to reject a request
    public void rejectRequest(Long id, String comments) {
        Optional<MaintenanceRequest> existingRequestOpt = requestRepository.findById(id);
        if (existingRequestOpt.isPresent()) {
            MaintenanceRequest request = existingRequestOpt.get();
            request.setStatus(RequestStatus.REJECTED);
            request.setRejectionComments(comments);
            requestRepository.save(request);
        } else {
            throw new RuntimeException("Request not found with id: " + id);
        }
    }
}
