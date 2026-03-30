package com.codecrew.Service;

import com.codecrew.model.AuditLog;
import com.codecrew.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    /**
     * Logs an admin action.
     *
     * @param adminEmail  The admin performing the action
     * @param entityType  Entity type (USER, BOOKING, etc.)
     * @param entityId    ID of the entity
     * @param action      Action performed (CREATE, UPDATE, DELETE)
     * @param oldValue    Previous state
     * @param newValue    New state
     */
    public void log(String adminEmail,
                    String entityType,
                    Long entityId,
                    String action,
                    String oldValue,
                    String newValue) {

        AuditLog log = new AuditLog();
        log.setAdminEmail(adminEmail);
        log.setEntityType(entityType);
        log.setEntityId(entityId);
        log.setAction(action);
        log.setOldValue(oldValue);
        log.setNewValue(newValue);

        auditLogRepository.save(log);
    }
}