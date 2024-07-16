package com.sameer.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Accounts  extends BaseEntity {
    @Column(name = "customer_id")
    private Long customerId;
    @Id
    @Column(name = "account_Number")
    private Long accountNumber;
    @Column(name = "account_Type")
    private String accountType;
    @Column(name = "branch_Address")
    private String branchAddress;
}
