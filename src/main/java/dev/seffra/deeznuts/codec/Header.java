package dev.seffra.deeznuts.codec;

/// DNS packet header.
class Header {
    private short id;
    private short properties;
    private short qdcount;
    private short ancount;
    private short nscount;
    private short arcount;

    /// Returns the ID of this message header.
    short getId() {
        return id;
    }

    short getQuestionCount() {
        return qdcount;
    }

    short getAnswerCount() {
        return ancount;
    }

    short getNameServerCount() {
        return nscount;
    }

    short getAdditionalRecords() {
        return arcount;
    }

    boolean isQuery() {
        return (properties & QR_BIT) == 0;
    }

    boolean isResponse() {
        return !isQuery();
    }

    OpCode getOpCode() throws UnknownOpCodeException {
        try {
            return OpCode.values()[(properties & OPCODE_BITS)];
        } catch (IndexOutOfBoundsException e) {
            throw new UnknownOpCodeException();
        }
    }

    boolean isAuthoritativeAnswer() {
        return (properties & AA_BIT) == 1;
    }

    boolean isTruncated() {
        return (properties & TC_BIT) == 1;
    }

    boolean isRecursionDesired() {
        return (properties & RD_BIT) == 1;
    }

    boolean isRecursionAvailable() {
        return (properties & RA_BIT) == 1;
    }

    boolean hasInvalidZeroBits() {
        return (properties & Z_BITS) != 0;
    }

    ResponseCode getResponseCode() throws UnknownResponseCodeException {
        try {
            return ResponseCode.values()[(properties & RCODE_BITS)];
        } catch (IndexOutOfBoundsException e) {
            throw new UnknownResponseCodeException();
        }
    }

    private static final short QR_BIT = (short) 0b1000000000000000;
    private static final short OPCODE_BITS = (short) 0b0111100000000000;
    private static final short AA_BIT = (short) 0b0000010000000000;
    private static final short TC_BIT = (short) 0b0000001000000000;
    private static final short RD_BIT = (short) 0b0000000100000000;
    private static final short RA_BIT = (short) 0b0000000010000000;
    private static final short Z_BITS = (short) 0b0000000001110000;
    private static final short RCODE_BITS = (short) 0b0000000000001111;
}
