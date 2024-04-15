package com.midas.app.mappers;

import com.midas.app.models.Account;
import com.midas.app.providers.payment.CreateAccount;
import com.midas.generated.model.AccountDto;
import lombok.NonNull;

public class Mapper {
  // Prevent instantiation
  private Mapper() {}

  /**
   * toAccountDto maps an account to an account dto.
   *
   * @param account is the account to be mapped
   * @return AccountDto
   */
  public static AccountDto toAccountDto(@NonNull Account account) {
    var accountDto = new AccountDto();

    accountDto
        .id(account.getId())
        .firstName(account.getFirstName())
        .lastName(account.getLastName())
        .email(account.getEmail())
        .providerType(account.getProviderType().name())
        .providerId(account.getProviderId())
        .createdAt(account.getCreatedAt())
        .updatedAt(account.getUpdatedAt());

    return accountDto;
  }

  public static CreateAccount toCreateAccount(@NonNull Account account) {
    CreateAccount createAccount = new CreateAccount();

    createAccount.setFirstName(account.getFirstName());
    createAccount.setLastName(account.getLastName());
    createAccount.setEmail(account.getEmail());

    return createAccount;
  }
}
