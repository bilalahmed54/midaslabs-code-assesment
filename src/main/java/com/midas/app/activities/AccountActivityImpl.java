package com.midas.app.activities;

import com.midas.app.exceptions.ResourceNotFoundException;
import com.midas.app.mappers.Mapper;
import com.midas.app.models.Account;
import com.midas.app.providers.payment.PaymentProvider;
import com.midas.app.repositories.AccountRepository;
import io.temporal.spring.boot.ActivityImpl;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
@ActivityImpl(taskQueues = "create-account-workflow")
public class AccountActivityImpl implements AccountActivity {
  private final PaymentProvider paymentProvider;

  private final AccountRepository accountRepository;

  public AccountActivityImpl(PaymentProvider paymentProvider, AccountRepository accountRepository) {
    this.paymentProvider = paymentProvider;
    this.accountRepository = accountRepository;
  }

  @Override
  public Account saveAccount(Account account) {
    return this.accountRepository.save(account);
  }

  @Override
  public Account updateAccount(UUID accountId, Account details) {
    Optional<Account> accountObj = this.accountRepository.findById(accountId);

    if (accountObj.isPresent()) {
      Account account = accountObj.get();

      account.setFirstName(details.getFirstName());
      account.setLastName(details.getLastName());
      account.setEmail(details.getEmail());

      return this.accountRepository.save(account);
    } else {
      throw new ResourceNotFoundException("account record not found to update");
    }
  }

  @Override
  public Account createPaymentAccount(Account account) {
    account.setProviderId(this.paymentProvider.createAccount(Mapper.toCreateAccount(account)));
    return account;
  }

  @Override
  public void updatePaymentAccount(String providerId, Account account) {
    this.paymentProvider.updateAccount(providerId, Mapper.toCreateAccount(account));
  }
}
