package org.example.dao;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.pojo.ChildJsonFile;
import org.example.pojo.ChildTransactions;
import org.example.pojo.ParentJsonFile;
import org.example.pojo.ParentTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Using a DAO for json file. Not using database connectivity
 */
@Getter
@Slf4j
@Component
public class TransactionDaoJsonFile implements TransactionInterfaceDao {

    private final JsonFileResourceReader jsonFileResourceReader;
    private Map<Integer, ParentTransaction> parentTransactionMap;
    private final  Map<Integer, List<ChildTransactions>> parentToChildTransactionsMap = new HashMap<>();

    @Autowired
    public TransactionDaoJsonFile(JsonFileResourceReader readJsonFileExample) throws IOException {
        this.jsonFileResourceReader = readJsonFileExample;

        String filePathParent = "Parent.json"; // Path to your JSON file in the resources folder
        ParentJsonFile myObject = readJsonFileExample.readFile(filePathParent, ParentJsonFile.class);
        this.parentTransactionMap = myObject.getParentTransactionsList()
                .stream()
                .collect(Collectors.toMap(ParentTransaction::getId, parent -> parent));

        String filePathChild = "Child.json";
        ChildJsonFile myobj = readJsonFileExample.readFile(filePathChild, ChildJsonFile.class);
        for (ChildTransactions c: myobj.getChildTransactionsList()) {
            ChildTransactions finalChildTxn = buildChildTxnFromParent(c);
            if (parentToChildTransactionsMap.containsKey(c.getParentId())) {
                parentToChildTransactionsMap.get(c.getParentId()).add(finalChildTxn);
            }else{
                List<ChildTransactions> tempList = new ArrayList<>();
                tempList.add(finalChildTxn);
                parentToChildTransactionsMap.put(c.getParentId(), tempList);
            }
        }
    }

    private ChildTransactions buildChildTxnFromParent(ChildTransactions c)
    {
        ChildTransactions finalChildTxn = new ChildTransactions();
        finalChildTxn.setId(c.getId());
        finalChildTxn.setParentId(c.getParentId());
        finalChildTxn.setPaidAmount(c.getPaidAmount());
        finalChildTxn.setSender(parentTransactionMap.get(c.getParentId()).getSender());
        finalChildTxn.setReceiver(parentTransactionMap.get(c.getParentId()).getReceiver());
        finalChildTxn.setTotalAmount(parentTransactionMap.get(c.getParentId()).getTotalAmount());

        return finalChildTxn;
    }

    /**
     * A function returning all parent transactions in parent.json file
     * @return a list of txns
     */
    @Override
    public List<ParentTransaction> getAllParentTransactions() {
        return new ArrayList<>(parentTransactionMap.values());
    }

    /**
     * A function returning all child transactions for a given parent.
     * @param parentId
     * @return list of child transactions of a given parent.
     */
    @Override
    public List<ChildTransactions> getAllChildTransactionsForParent(int parentId) {
        return parentToChildTransactionsMap.get(parentId);
    }
}
