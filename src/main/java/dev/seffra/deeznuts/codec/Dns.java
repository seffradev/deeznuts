package dev.seffra.deeznuts.codec;

import java.nio.ByteBuffer;

/// A codec implementation for DNS packets.
public class Dns {
    private Header header;

    /// Takes a `ByteBuffer` and converts it into a `Dns` message.
    public static Dns decode(ByteBuffer buffer) {
        throw new UnsupportedOperationException("Unimplemented method 'decode'");
    }

    /// Takes a `Dns` message and converts it into byte-form,
    /// stored in a `ByteBuffer`.
    public static ByteBuffer encode(Dns message) {
        throw new UnsupportedOperationException("Unimplemented method 'encode'");
    }

    /// Returns the ID of this message.
    public short getId() {
        return header.getId();
    }

    /// Returns the question count of this message.
    public short getQuestionCount() {
        return header.getQuestionCount();
    }

    /// Returns the answer count of this message.
    public short getAnswerCount() {
        return header.getAnswerCount();
    }

    /// Returns the name server count of this message.
    public short getNameServerCount() {
        return header.getNameServerCount();
    }

    /// Returns the addition records count of this message.
    public short getAdditionalRecords() {
        return header.getAdditionalRecords();
    }

    /// Denotes if the message is a query.
    public boolean isQuery() {
        return header.isQuery();
    }

    /// Denotes if the message is a response.
    public boolean isResponse() {
        return header.isResponse();
    }

    /// Returns the `OpCode` of this message.
    public OpCode getOpCode() throws UnknownOpCodeException {
        return header.getOpCode();
    }

    /// Denotes if this message is an authoritative answer.
    public boolean isAuthoritativeAnswer() {
        return header.isAuthoritativeAnswer();
    }

    /// Denotes if this message is truncated.
    public boolean isTruncated() {
        return header.isTruncated();
    }

    /// Denotes if recursion is desired in this message.
    public boolean isRecursionDesired() {
        return header.isRecursionDesired();
    }

    /// Denotes if recursion is available in this message.
    public boolean isRecursionAvailable() {
        return header.isRecursionAvailable();
    }

    /// Returns the `ResponseCode` of this message.
    public ResponseCode getResponseCode() throws UnknownResponseCodeException {
        return header.getResponseCode();
    }

    /// Denotes if the message has any invalid bits.
    private boolean hasInvalidZeroBits() {
        return header.hasInvalidZeroBits();
    }
}
