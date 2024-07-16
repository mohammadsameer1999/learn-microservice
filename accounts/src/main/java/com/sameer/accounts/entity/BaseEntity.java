package com.sameer.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Data
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(insertable =false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Column(updatable = false,name = "created_By",nullable = true)
    @CreatedBy
    private String createdBy;
    @Column(insertable = false,name = "updated_By",nullable = true)
    @LastModifiedBy
    private String updatedBy;
}
