package org.example.service;

import org.example.pojo.ChildTransactions;
import org.example.pojo.ParentTransaction;

import java.util.List;

public interface TransactionService {

    public List<ParentTransaction> getAllParentTransactions();

    public List<ChildTransactions> getAllChildTransactionsForParent(int parentId);
}
