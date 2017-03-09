package behaviourPatterns.state;

/**
 * @author Семакин Виктор
 */
public enum DocumentStateEnum {
    LOAD,
    PREPARED,
    SIGNED_1,
    SENT,
    RECEIVED,
    SIGNED_2,
    ACCEPT,
    SENT_APPROVEMENT,
    RECEIVED_APPROVEMENT,
    ERROR_SIGNED,
    REJECTED;
}
