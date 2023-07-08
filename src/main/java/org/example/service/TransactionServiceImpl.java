package org.example.service;

import org.example.dao.TransactionInterfaceDao;
import org.example.pojo.ChildTransactions;
import org.example.pojo.ParentTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    private TransactionInterfaceDao transactionInterfaceDao;

    @Autowired
    public TransactionServiceImpl(TransactionInterfaceDao transactionInterfaceDao) {
        this.transactionInterfaceDao = transactionInterfaceDao;
    }

    @Override
    public List<ParentTransaction> getAllParentTransactions() {
        return transactionInterfaceDao.getAllParentTransactions();
    }

    @Override
    public List<ChildTransactions> getAllChildTransactionsForParent(int parentId) {
        return transactionInterfaceDao.getAllChildTransactionsForParent(parentId);
    }
}
