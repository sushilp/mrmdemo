-- Insert admin user
INSERT INTO users (username, password, role) VALUES ('admin', '{noop}admin', 'ADMIN');

-- Insert regular user
INSERT INTO users (username, password, role) VALUES ('user', '{noop}user', 'USER');


-- Insert sample data into maintenance_requests

INSERT INTO maintenance_requests (id, type, priority, description, owner, approver, approval_comments, rejection_comments, datetime_created, status) VALUES
(1, 'REPAIR', 'HIGH', 'Leaky faucet in the kitchen', 'user', 'admin', 'Approved after review', NULL, '2024-09-14 10:00:00', 'APPROVED'),
(2, 'INSTALLATION', 'MEDIUM', 'New light fixture installation', 'user2', NULL, NULL, NULL, '2024-09-14 11:00:00', 'PENDING'),
(3, 'MAINTENANCE', 'LOW', 'Annual HVAC maintenance', 'user3', NULL, NULL, NULL, '2024-09-14 12:00:00', 'PENDING'),
(4, 'REPAIR', 'HIGH', 'Broken window in the living room', 'user', 'admin', NULL, 'Not enough budget', '2024-09-14 13:00:00', 'REJECTED');