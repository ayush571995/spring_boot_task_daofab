package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ChildJsonFile {

    @JsonProperty("data")
    private List<ChildTransactions> childTransactionsList;
}
