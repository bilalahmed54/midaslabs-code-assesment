package com.midas.app.activities;

import com.midas.app.models.Account;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import java.util.UUID;

@ActivityInterface
public interface AccountActivity {
  /**
   * saveAccount saves an account in the data store.
   *
   * @param account is the account to be saved
   * @return Account
   */
  @ActivityMethod
  Account saveAccount(Account account);

  /**
   * updateAccount Updates an existing account in the system and provider.
   *
   * @param accountId is the id of the account to be updated
   * @param details is the modified details of the account to update.
   * @return Account
   */
  @ActivityMethod
  Account updateAccount(UUID accountId, Account details);

  /**
   * createPaymentAccount creates a payment account in the system or provider.
   *
   * @param account is the account to be created
   * @return Account
   */
  @ActivityMethod
  Account createPaymentAccount(Account account);

  /**
   * updatePaymentAccount update existing account in the payment provider.
   *
   * @param providerId is the id of the customer in Stripe system
   * @param account is the details of the account to be created.
   */
  @ActivityMethod
  void updatePaymentAccount(String providerId, Account account);
}
