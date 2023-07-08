package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildTransactions extends ParentTransaction {

    @JsonProperty("parentId")
    private int parentId;
    @JsonProperty("paidAmount")
    private float paidAmount;
}
