package com.midas.app.services;

import com.midas.app.models.Account;
import java.util.List;
import java.util.UUID;

public interface AccountService {
  /**
   * createAccount creates a new account in the system or provider.
   *
   * @param details is the details of the account to be created.
   * @return Account
   */
  Account createAccount(Account details);

  /**
   * updateAccount Updates an existing account in the system and provider.
   *
   * @param accountId is the id of the account to be updated
   * @param details is the modified details of the account to update.
   * @return Account
   */
  Account updateAccount(UUID accountId, Account details);

  /**
   * getAccounts returns a list of accounts.
   *
   * @return List<Account>
   */
  List<Account> getAccounts();
}
