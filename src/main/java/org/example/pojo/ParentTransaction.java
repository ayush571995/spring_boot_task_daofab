package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParentTransaction {

    @JsonProperty("id")
    private int id;

    @JsonProperty("sender")
    private String sender;

    @JsonProperty("receiver")
    private String receiver;

    @JsonProperty("totalAmount")
    private float totalAmount;

    @JsonProperty("totalPaidAmount")
    private float totalPaidAmount;
}
