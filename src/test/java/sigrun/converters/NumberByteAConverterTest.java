package sigrun.converters;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static sigrun.converters.NumberByteAConverter.*;

public class NumberByteAConverterTest {
    @Test
    public void testIntToByteA() throws Exception {
        int testCaseSource1 = 0x7F000000;
        byte[] testCaseResult1 = new byte[]{0x7F, 0x00, 0x00, 0x00};
        int testCaseSource2 = 0x0FF00000;
        byte[] testCaseResult2 = new byte[]{0x0F, (byte) 0xF0, 0x00, 0x00};
        assertArrayEquals(testCaseResult1, intToByteA(testCaseSource1));
        assertArrayEquals(testCaseResult2, intToByteA(testCaseSource2));
    }

    @Test
    public void testWriteIntToByteA() throws Exception {
        int testCaseSource1 = 0x7F00007F;
        byte[] testCaseDestination1 = new byte[]{0x00, 0x00, 0x00, 0x00, 0x00};
        byte[] testCaseResult1 = new byte[]{0x7F, 0x00, 0x00, 0x7F, 0x00};
        int testCaseSource2 = 0x7F00007F;
        byte[] testCaseDestination2 = new byte[]{0x01, 0x00, 0x00, 0x00, 0x00};
        byte[] testCaseResult2 = new byte[]{0x01, 0x7F, 0x00, 0x00, 0x7F};

        assertArrayEquals(testCaseResult1, writeIntToByteA(testCaseSource1, testCaseDestination1, 0));
        assertArrayEquals(testCaseResult2, writeIntToByteA(testCaseSource2, testCaseDestination2, 1));

    }

    @Test
    public void testShortToByteA() throws Exception {
        short testCaseSource1 = 0x7F00;
        byte[] testCaseResult1 = new byte[]{0x7F, 0x00};
        short testCaseSource2 = 0x7FFF;
        byte[] testCaseResult2 = new byte[]{0x7F, (byte) 0xFF};

        assertArrayEquals(testCaseResult1, shortToByteA(testCaseSource1));
        assertArrayEquals(testCaseResult2, shortToByteA(testCaseSource2));
    }

    @Test
    public void testWriteShortToByteA() throws Exception {
        short testCaseSource1 = 0x7F00;
        byte[] testCaseResult1 = new byte[]{0x7F, 0x00, (byte) 0xFF};
        byte[] testCaseDestination1 = new byte[]{0x00, 0x00, (byte) 0xFF};
        short testCaseSource2 = 0x7FFF;
        byte[] testCaseResult2 = new byte[]{(byte) 0xFF, 0x7F, (byte) 0xFF};
        byte[] testCaseDestination2 = new byte[]{(byte) 0xFF, 0x00, 0x00};

        assertArrayEquals(testCaseResult1, writeShortToByteA(testCaseSource1, testCaseDestination1, 0));
        assertArrayEquals(testCaseResult2, writeShortToByteA(testCaseSource2, testCaseDestination2, 1));
    }
}
