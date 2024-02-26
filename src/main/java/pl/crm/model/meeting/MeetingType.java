package pl.crm.model.meeting;

public enum MeetingType {
    IN_PERSON("IN_PERSON"),
    PHONE("PHONE");

    private final String transactionTypeName;

    MeetingType(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }
}
