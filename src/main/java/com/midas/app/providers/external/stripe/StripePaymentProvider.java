package com.midas.app.providers.external.stripe;

import com.midas.app.enums.ProviderTypeEnum;
import com.midas.app.providers.payment.CreateAccount;
import com.midas.app.providers.payment.PaymentProvider;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerUpdateParams;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class StripePaymentProvider implements PaymentProvider {
  private final Logger logger = LoggerFactory.getLogger(StripePaymentProvider.class);

  private final StripeConfiguration configuration;

  /** providerName is the name of the payment provider */
  @Override
  public String providerName() {
    return ProviderTypeEnum.STRIPE.name().toLowerCase();
  }

  /**
   * createAccount creates a new account in the payment provider.
   *
   * @param details is the details of the account to be created.
   * @return String
   */
  @Override
  public String createAccount(CreateAccount details) {
    Stripe.apiKey = configuration.getApiKey();
    CustomerCreateParams params =
        CustomerCreateParams.builder()
            .setName(String.format("%s %s", details.getFirstName(), details.getLastName()))
            .setEmail(details.getEmail())
            .build();
    try {
      Customer customer = Customer.create(params);
      return customer.getId();
    } catch (StripeException stripeException) {
      throw new IllegalArgumentException("stripe account not created");
    }
  }

  /**
   * updateAccount update existing account in the payment provider.
   *
   * @param providerId is the id of the customer in Stripe system.
   * @param details is the details of the account to be created.
   */
  @Override
  public void updateAccount(String providerId, CreateAccount details) {
    Stripe.apiKey = configuration.getApiKey();

    try {
      Customer resource = Customer.retrieve(providerId);

      CustomerUpdateParams params =
          CustomerUpdateParams.builder()
              .setName(String.format("%s %s", details.getFirstName(), details.getLastName()))
              .setEmail(details.getEmail())
              .build();

      resource.update(params);
    } catch (StripeException stripeException) {
      throw new IllegalArgumentException("stripe account not created");
    }
  }
}
