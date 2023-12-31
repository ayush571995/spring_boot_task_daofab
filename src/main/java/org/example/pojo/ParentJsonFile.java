package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ParentJsonFile {
    @JsonProperty("data")
    private List<ParentTransaction> parentTransactionsList;
}
