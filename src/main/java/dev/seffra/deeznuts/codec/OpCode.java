package dev.seffra.deeznuts.codec;

public enum OpCode {
    /// A standard query (QUERY).
    Query((short) 0),

    /// An inverse query (IQUERY).
    InverseQuery((short) 1),

    /// A server status request (STATUS).
    Status((short) 2);

    public final short value;

    private OpCode(short value) {
        this.value = value;
    }
}
