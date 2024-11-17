package dev.seffra.deeznuts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import dev.seffra.deeznuts.codec.Dns;
import dev.seffra.deeznuts.codec.UnknownResponseCodeException;

public class ResponseCodeTest {
    @Test
    public void responseCodeNoErrorCondition() throws UnknownResponseCodeException {
        Dns message = new Dns();
        assertEquals(0, message.getResponseCode().value);
    }

    @Test
    public void responseCodeFormatError() throws UnknownResponseCodeException {
        Dns message = new Dns();
        assertEquals(1, message.getResponseCode().value);
    }

    @Test
    public void responseCodeServerFailure() throws UnknownResponseCodeException {
        Dns message = new Dns();
        assertEquals(2, message.getResponseCode().value);
    }

    @Test
    public void responseCodeNameError() throws UnknownResponseCodeException {
        Dns message = new Dns();
        assertEquals(3, message.getResponseCode().value);
    }

    @Test
    public void responseCodeNotImplemented() throws UnknownResponseCodeException {
        Dns message = new Dns();
        assertEquals(4, message.getResponseCode().value);
    }

    @Test
    public void responseCodeRefused() throws UnknownResponseCodeException {
        Dns message = new Dns();
        assertEquals(5, message.getResponseCode().value);
    }

    @Test
    public void responseCodeThrows() {
        Dns message = new Dns();
        assertThrows(UnknownResponseCodeException.class, message::getResponseCode);
    }
}
