package org.example.service;

import org.example.pojo.PaginatedResponse;
import org.example.pojo.ParentTransaction;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PaginatedResponseBuilder {

    public PaginatedResponse getPaginatedResponseForTransactions(int pageSize, int currentPage, List<ParentTransaction> txnList){
        // default page i.e. it's the first call
        List<ParentTransaction> finalResponseList;
        if (currentPage == 0)
        {
            finalResponseList = txnList.subList(0, pageSize);
        }
        else{
            int elementsProcessed = pageSize * currentPage;

            if (elementsProcessed > txnList.size())
                return new PaginatedResponse(pageSize, currentPage, Collections.emptyList(), true);

            finalResponseList = txnList.subList(elementsProcessed, Integer.min(elementsProcessed+ pageSize, txnList.size()));
        }

        return new PaginatedResponse(pageSize, currentPage+1, finalResponseList, false);
    }
}
