package dev.seffra.deeznuts;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;

import dev.seffra.deeznuts.codec.Dns;
import dev.seffra.deeznuts.codec.OpCode;
import dev.seffra.deeznuts.codec.UnknownOpCodeException;

public class QueryTest {
    @Test
    public void standardQueryARecord() throws UnknownOpCodeException {
        byte expectedBytes[] = {
                (byte) 0xae, (byte) 0x0d, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x08, (byte) 0x61, (byte) 0x76, (byte) 0x65,
                (byte) 0x6b, (byte) 0x76, (byte) 0x69, (byte) 0x73, (byte) 0x74, (byte) 0x03, (byte) 0x63, (byte) 0x6f,
                (byte) 0x6d, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x01,
        };

        ByteBuffer buffer = ByteBuffer.wrap(expectedBytes);

        var message = Dns.decode(buffer);

        assertEquals(0xae0d, message.getId());
        assertTrue(message.isQuery());
        assertEquals(OpCode.Query, message.getOpCode());
        assertFalse(message.isTruncated());
        assertTrue(message.isRecursionDesired());
        assertEquals(1, message.getQuestionCount());
        assertEquals(0, message.getAnswerCount());
        assertEquals(0, message.getAdditionalRecords());

        // TODO: Add assertions for fields and existence
        // of data in the `message`.

        buffer = Dns.encode(message);

        byte[] actualBytes = new byte[expectedBytes.length];
        buffer.get(actualBytes, 0, expectedBytes.length);

        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    public void standardQueryAaaaRecord() throws UnknownOpCodeException {
        byte expectedBytes[] = {
                (byte) 0x5d, (byte) 0xd9, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x04, (byte) 0x70, (byte) 0x69, (byte) 0x6e,
                (byte) 0x67, (byte) 0x09, (byte) 0x61, (byte) 0x72, (byte) 0x63, (byte) 0x68, (byte) 0x6c, (byte) 0x69,
                (byte) 0x6e, (byte) 0x75, (byte) 0x78, (byte) 0x03, (byte) 0x6f, (byte) 0x72, (byte) 0x67, (byte) 0x00,
                (byte) 0x00, (byte) 0x1c, (byte) 0x00, (byte) 0x01,
        };

        ByteBuffer buffer = ByteBuffer.wrap(expectedBytes);

        var message = Dns.decode(buffer);

        assertEquals(0x5dd9, message.getId());
        assertTrue(message.isQuery());
        assertEquals(OpCode.Query, message.getOpCode());
        assertFalse(message.isTruncated());
        assertTrue(message.isRecursionDesired());
        assertEquals(1, message.getQuestionCount());
        assertEquals(0, message.getAnswerCount());
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
