package com.maintenance.management.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "maintenance_requests")
public class MaintenanceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestType type;  // Assuming you have an enum for request types

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestPriority priority;  // Assuming you have an enum for priority

    @Column(length = 500, nullable = false)
    private String description;

    @Column(nullable = false)
    private String owner;  // Username or ID of the customer who created the request

    @Column
    private String approver;  // Username or ID of the admin who approved/rejected the request

    @Column
    private String approvalComments;  // Comments added by the admin when approving

    @Column
    private String rejectionComments;  // Comments added by the admin when rejecting

    @Column(nullable = false)
    private LocalDateTime datetimeCreated;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status;  // Enum to keep track of the request status

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public RequestPriority getPriority() {
        return priority;
    }

    public void setPriority(RequestPriority priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getApprovalComments() {
        return approvalComments;
    }

    public void setApprovalComments(String approvalComments) {
        this.approvalComments = approvalComments;
    }

    public String getRejectionComments() {
        return rejectionComments;
    }

    public void setRejectionComments(String rejectionComments) {
        this.rejectionComments = rejectionComments;
    }

    public LocalDateTime getDatetimeCreated() {
        return datetimeCreated;
    }

    public void setDatetimeCreated(LocalDateTime datetimeCreated) {
        this.datetimeCreated = datetimeCreated;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}
