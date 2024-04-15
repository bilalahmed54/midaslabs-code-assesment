package com.midas.app.providers.payment;

public interface PaymentProvider {
  /** providerName is the name of the payment provider */
  String providerName();

  /**
   * createAccount creates a new account in the payment provider.
   *
   * @param details is the details of the account to be created.
   * @return String
   */
  String createAccount(CreateAccount details);

  /**
   * updateAccount update existing account in the payment provider.
   *
   * @param providerId is the id of the customer in Stripe system
   * @param details is the details of the account to be created.
   */
  void updateAccount(String providerId, CreateAccount details);
}
