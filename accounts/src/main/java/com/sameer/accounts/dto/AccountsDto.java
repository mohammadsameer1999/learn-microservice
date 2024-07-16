package com.sameer.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {


    @Schema(
            description = "Account Number of HeadsUp Bank account", example = "3454433243"
    )
    private Long accountNumber;

    @Schema(
            description = "Account type of HeadsUp Bank account", example = "Savings"
    )
    private String accountType;

    @Schema(
            description = "HeadsUp Bank branch address", example = "123 NewYork"
    )
    private String branchAddress;
}
