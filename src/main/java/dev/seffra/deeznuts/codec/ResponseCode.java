package dev.seffra.deeznuts.codec;

public enum ResponseCode {
    NoError((short) 0),
    FormatError((short) 1),
    ServerFailure((short) 2),
    NameError((short) 3),
    NotImplemented((short) 4),
    Refused((short) 5);

    public final short value;

    private ResponseCode(short value) {
        this.value = value;
    }
}
