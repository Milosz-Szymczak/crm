package pl.crm.model.transaction;

import lombok.Getter;

@Getter
public enum TransactionType {
    BUY("BUY"),
    SELL("SELL");

    private final String transactionTypeName;

    TransactionType(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }

}
