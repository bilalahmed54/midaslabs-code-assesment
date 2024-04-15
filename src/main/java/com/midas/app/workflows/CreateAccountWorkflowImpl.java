package com.midas.app.workflows;

import com.midas.app.activities.AccountActivity;
import com.midas.app.enums.ProviderTypeEnum;
import com.midas.app.models.Account;
import io.temporal.activity.LocalActivityOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Workflow;
import java.time.Duration;

@WorkflowImpl(taskQueues = "create-account-workflow")
public class CreateAccountWorkflowImpl implements CreateAccountWorkflow {

  private final AccountActivity accountActivity =
      Workflow.newLocalActivityStub(
          AccountActivity.class,
          LocalActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(5)).build());

  @Override
  public Account createAccount(Account details) {
    details.setProviderType(ProviderTypeEnum.STRIPE);
    details = this.accountActivity.createPaymentAccount(details);
    return this.accountActivity.saveAccount(details);
  }
}
