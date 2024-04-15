package com.midas.app.workflows;

import com.midas.app.models.Account;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import java.util.UUID;

@WorkflowInterface
public interface UpdateAccountWorkflow {
  String QUEUE_NAME = "create-account-workflow";

  /**
   * updateAccount Updates an existing account in the system and provider.
   *
   * @param accountId is the id of the account to be updated
   * @param details is the modified details of the account to update.
   * @return Account
   */
  @WorkflowMethod
  Account updateAccount(UUID accountId, Account details);
}
