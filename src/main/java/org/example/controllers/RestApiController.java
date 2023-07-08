package org.example.controllers;

import org.example.pojo.*;
import org.example.service.PaginatedResponseBuilder;
import org.example.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * Controller class
 */
@RestController
public class RestApiController {

    private TransactionService transactionService;
    private PaginatedResponseBuilder paginatedResponseTransformer;

    @Value("${default.page.size.parent.txn}")
    private int defaultPageSizeForParentTxn;


    @Autowired
    public RestApiController(TransactionService transactionService, PaginatedResponseBuilder paginatedResponseTransformer) {
        this.transactionService = transactionService;
        this.paginatedResponseTransformer = paginatedResponseTransformer;
    }

    /**
     * Api for getting all parent transactions.
     * @return List of parent transactions wrapped in base response
     */
    @GetMapping(value = "/api/v1/get_parent_txn", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getParentTransactions(@RequestParam(name = "currentPage" , required = false, defaultValue = "0") int currentPage) {
        List<ParentTransaction> parentTransactionsList = transactionService.getAllParentTransactions();
        PaginatedResponse paginatedResponse = paginatedResponseTransformer.getPaginatedResponseForTransactions( defaultPageSizeForParentTxn,
                                                                                                                currentPage,
                                                                                                                parentTransactionsList);
        return new SuccessResponse(paginatedResponse);
    }

    /**
     * Api for getting list of all transactions of a given parentId of trasaction.
     * @param parentId
     * @return List of child transactions wrapped in baseResponse
     */
    @GetMapping(value = "/api/v1/get_txn_for_child", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getChildTransactionForParent(@RequestParam("parentId") int parentId ) {
        List<ChildTransactions> childTransactionsList = transactionService.getAllChildTransactionsForParent(parentId);
        if (childTransactionsList == null)
            return new SuccessResponse(Collections.emptyList());
        return new SuccessResponse(childTransactionsList);
    }
}
