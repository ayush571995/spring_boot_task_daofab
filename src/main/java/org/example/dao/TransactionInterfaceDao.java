package org.example.dao;

import org.example.pojo.ChildTransactions;
import org.example.pojo.ParentTransaction;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TransactionInterfaceDao {

    List<ParentTransaction> getAllParentTransactions();

    List<ChildTransactions> getAllChildTransactionsForParent(int parentId);
}
