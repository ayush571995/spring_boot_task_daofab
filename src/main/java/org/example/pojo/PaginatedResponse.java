package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaginatedResponse {

    @JsonProperty("pageSize")
    private int pageSize;

    @JsonProperty("currentPage")
    private int currentPage;

    @JsonProperty("data")
    private Object data;

    @JsonProperty("isLastPage")
    private boolean isLastPage;
}
