package com.ruoyi.asset.exception;

/**
 * Exception thrown when an invalid state transition is attempted
 */
public class InvalidStateTransitionException extends RuntimeException {

    private final String fromStatus;
    private final String toStatus;

    public InvalidStateTransitionException(String fromStatus, String toStatus) {
        super(String.format("Invalid state transition: cannot transition from %s to %s", fromStatus, toStatus));
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
    }

    public InvalidStateTransitionException(String fromStatus, String toStatus, String message) {
        super(message);
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
    }

    public String getFromStatus() {
        return fromStatus;
    }

    public String getToStatus() {
        return toStatus;
    }
}
