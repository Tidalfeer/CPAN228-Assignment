package com.codecrew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codecrew.model.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}