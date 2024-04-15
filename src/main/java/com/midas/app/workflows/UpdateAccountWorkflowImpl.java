package com.midas.app.workflows;

import com.midas.app.activities.AccountActivity;
import com.midas.app.models.Account;
import io.temporal.activity.LocalActivityOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Workflow;
import java.time.Duration;
import java.util.UUID;

@WorkflowImpl(taskQueues = "create-account-workflow")
public class UpdateAccountWorkflowImpl implements UpdateAccountWorkflow {

  private final AccountActivity accountActivity =
      Workflow.newLocalActivityStub(
          AccountActivity.class,
          LocalActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(5)).build());

  @Override
  public Account updateAccount(UUID accountId, Account details) {
    details = this.accountActivity.updateAccount(accountId, details);
    this.accountActivity.updatePaymentAccount(details.getProviderId(), details);
    return details;
  }
}
