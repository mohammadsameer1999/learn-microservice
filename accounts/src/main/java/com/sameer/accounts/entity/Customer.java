package com.sameer.accounts.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    private String name;
    private String  email;
    @Column(name = "mobile_Number")
    private String mobileNumber;

}
