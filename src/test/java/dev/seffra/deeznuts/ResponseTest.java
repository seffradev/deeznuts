package dev.seffra.deeznuts;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;

import dev.seffra.deeznuts.codec.Dns;
import dev.seffra.deeznuts.codec.OpCode;
import dev.seffra.deeznuts.codec.ResponseCode;
import dev.seffra.deeznuts.codec.UnknownOpCodeException;
import dev.seffra.deeznuts.codec.UnknownResponseCodeException;

public class ResponseTest {
    @Test
    public void standardQueryARecord() throws UnknownOpCodeException, UnknownResponseCodeException {
        byte expectedBytes[] = {
                (byte) 0xae, (byte) 0x0d, (byte) 0x81, (byte) 0x80, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x02,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x08, (byte) 0x61, (byte) 0x76, (byte) 0x65,
                (byte) 0x6b, (byte) 0x76, (byte) 0x69, (byte) 0x73, (byte) 0x74, (byte) 0x03, (byte) 0x63, (byte) 0x6f,
                (byte) 0x6d, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0xc0, (byte) 0x0c,
                (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x2c,
                (byte) 0x00, (byte) 0x04, (byte) 0xac, (byte) 0x43, (byte) 0xcc, (byte) 0xa9, (byte) 0xc0, (byte) 0x0c,
                (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x2c,
                (byte) 0x00, (byte) 0x04, (byte) 0x68, (byte) 0x15, (byte) 0x4d, (byte) 0x34,
        };

        ByteBuffer buffer = ByteBuffer.wrap(expectedBytes);

        var message = Dns.decode(buffer);

        assertEquals(0xae0d, message.getId());
        assertTrue(message.isResponse());
        assertEquals(OpCode.Query, message.getOpCode());
        assertFalse(message.isTruncated());
        assertTrue(message.isRecursionDesired());
        assertTrue(message.isRecursionAvailable());
        assertEquals(ResponseCode.NoError, message.getResponseCode());
        assertEquals(1, message.getQuestionCount());
        assertEquals(2, message.getAnswerCount());
        assertEquals(0, message.getNameServerCount());
        assertEquals(0, message.getAdditionalRecords());

        // TODO: Add assertions for fields and existence
        // of data in the `message`.

        buffer = Dns.encode(message);

        byte[] actualBytes = new byte[expectedBytes.length];
        buffer.get(actualBytes, 0, expectedBytes.length);

        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    public void standardQueryAaaaRecord() throws UnknownOpCodeException, UnknownResponseCodeException {
        byte expectedBytes[] = {
                (byte) 0x5d, (byte) 0xd9, (byte) 0x81, (byte) 0x80, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x02,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x04, (byte) 0x70, (byte) 0x69, (byte) 0x6e,
                (byte) 0x67, (byte) 0x09, (byte) 0x61, (byte) 0x72, (byte) 0x63, (byte) 0x68, (byte) 0x6c, (byte) 0x69,
                (byte) 0x6e, (byte) 0x75, (byte) 0x78, (byte) 0x03, (byte) 0x6f, (byte) 0x72, (byte) 0x67, (byte) 0x00,
                (byte) 0x00, (byte) 0x1c, (byte) 0x00, (byte) 0x01, (byte) 0xc0, (byte) 0x0c, (byte) 0x00, (byte) 0x05,
                (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x0c, (byte) 0x85, (byte) 0x00, (byte) 0x18,
                (byte) 0x08, (byte) 0x72, (byte) 0x65, (byte) 0x64, (byte) 0x69, (byte) 0x72, (byte) 0x65, (byte) 0x63,
                (byte) 0x74, (byte) 0x09, (byte) 0x61, (byte) 0x72, (byte) 0x63, (byte) 0x68, (byte) 0x6c, (byte) 0x69,
                (byte) 0x6e, (byte) 0x75, (byte) 0x78, (byte) 0x03, (byte) 0x6f, (byte) 0x72, (byte) 0x67, (byte) 0x00,
                (byte) 0xc0, (byte) 0x30, (byte) 0x00, (byte) 0x1c, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00,
                (byte) 0x01, (byte) 0xf9, (byte) 0x00, (byte) 0x10, (byte) 0x2a, (byte) 0x01, (byte) 0x04, (byte) 0xf9,
                (byte) 0xc0, (byte) 0x10, (byte) 0x26, (byte) 0x36, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01,
        };

        ByteBuffer buffer = ByteBuffer.wrap(expectedBytes);

        var message = Dns.decode(buffer);

        assertEquals(0xae0d, message.getId());
        assertTrue(message.isResponse());
        assertEquals(OpCode.Query, message.getOpCode());
        assertFalse(message.isAuthoritativeAnswer());
        assertFalse(message.isTruncated());
        assertTrue(message.isRecursionDesired());
        assertTrue(message.isRecursionAvailable());
        assertEquals(ResponseCode.NoError, message.getResponseCode());
        assertEquals(1, message.getQuestionCount());
        assertEquals(2, message.getAnswerCount());
        assertEquals(0, message.getNameServerCount());
        assertEquals(0, message.getAdditionalRecords());

        // TODO: Add assertions for fields and existence
        // of data in the `message`.

        buffer = Dns.encode(message);

        byte[] actualBytes = new byte[expectedBytes.length];
        buffer.get(actualBytes, 0, expectedBytes.length);

        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    public void standardQueryCnameRecord() {
        byte expectedBytes[] = {};

        ByteBuffer buffer = ByteBuffer.wrap(expectedBytes);

        var message = Dns.decode(buffer);

        // TODO: Add assertions for fields and existence
        // of data in the `message`.

        buffer = Dns.encode(message);

        byte[] actualBytes = new byte[expectedBytes.length];
        buffer.get(actualBytes, 0, expectedBytes.length);

        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    public void standardQueryMxRecord() {
        byte expectedBytes[] = {};

        ByteBuffer buffer = ByteBuffer.wrap(expectedBytes);

        var message = Dns.decode(buffer);

        // TODO: Add assertions for fields and existence
        // of data in the `message`.

        buffer = Dns.encode(message);

        byte[] actualBytes = new byte[expectedBytes.length];
        buffer.get(actualBytes, 0, expectedBytes.length);

        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    public void standardQuerySrvRecord() {
        byte expectedBytes[] = {};

        ByteBuffer buffer = ByteBuffer.wrap(expectedBytes);

        var message = Dns.decode(buffer);

        // TODO: Add assertions for fields and existence
        // of data in the `message`.

        buffer = Dns.encode(message);

        byte[] actualBytes = new byte[expectedBytes.length];
        buffer.get(actualBytes, 0, expectedBytes.length);

        assertArrayEquals(expectedBytes, actualBytes);
    }
}
