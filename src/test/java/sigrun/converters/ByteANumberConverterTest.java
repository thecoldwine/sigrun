package sigrun.converters;

import org.junit.Test;

import static org.junit.Assert.*;
import static sigrun.converters.ByteANumberConverter.*;

public class ByteANumberConverterTest {
    @Test
    public void testReadUnsignedShort_WOEx() {
        byte[] firstCase = {0x00, (byte) 0xFF};
        byte[] secondCase = {(byte) 0xFF, 0x00};
        byte[] thirdCase = {(byte) 0xFF, (byte) 0xFE, (byte) 0xFD};

        try {
            int res1 = byteAToUnsignedShort(firstCase, 0);
            assertEquals(0x00FF, res1);

            int res2 = byteAToUnsignedShort(secondCase, 0);
            assertEquals(0xFF00, res2);

            int res3 = byteAToUnsignedShort(thirdCase, 0);
            assertEquals(0xFFFE, res3);

            int res4 = byteAToUnsignedShort(thirdCase, 1);
            assertEquals(0xFEFD, res4);
        } catch (IndexOutOfBoundsException ex) {
            fail("There are shouldn't be an exception");
        }
    }

    @Test
    public void testReadUnsignedShort_Ex() {
        byte[] firstCase = {0x00, (byte) 0xFF};
        byte[] secondCase = {(byte) 0xFF, 0x00};
        byte[] thirdCase = {(byte) 0xFF, (byte) 0xFE, (byte) 0xFD};

        try {
            byteAToUnsignedShort(firstCase, 1);
            fail("There are should be an exception");
        } catch (IndexOutOfBoundsException ex) {
            assertTrue(true);
        }

        try {
            byteAToUnsignedShort(secondCase, 2);
            fail("There are should be an exception");
        } catch (IndexOutOfBoundsException ex) {
            assertTrue(true);
        }

        try {
            byteAToUnsignedShort(thirdCase, 1);
            assertTrue(true);
        } catch (IndexOutOfBoundsException ex) {
            fail("There are shouldn't be an exception");
        }
    }

    @Test
    public void testReadUnsignedInt_WOEx() {
        byte[] firstCase = {0x00, 0x00, 0x00, (byte) 0xFF};
        byte[] secondCase = {(byte) 0xFF, 0x00, (byte) 0xA0, 0x00};
        byte[] thirdCase = {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0x00};

        try {
            long res1 = byteAToUnsignedInt(firstCase, 0);
            assertEquals(0x000000FFL, res1);

            long res2 = byteAToUnsignedInt(secondCase, 0);
            assertEquals(0xFF00A000L, res2);

            long res3 = byteAToUnsignedInt(thirdCase, 0);
            assertEquals(0xFFFFFFFFL, res3);

            long res4 = byteAToUnsignedInt(thirdCase, 1);
            assertEquals(0xFFFFFF00L, res4);
        } catch (IndexOutOfBoundsException ex) {
            fail("There are shouldn't be an exception.");
        }
    }

    @Test
    public void testReadUnsignedInt_Ex() {
        byte[] firstCase = {0x00, 0x00, 0x00, (byte) 0xFF};
        byte[] secondCase = {(byte) 0xFF, 0x00, (byte) 0xA0, 0x00};
        byte[] thirdCase = {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0x00};

        try {
            byteAToInt(firstCase, 1);
            fail("There are should be an exception.");
        } catch (IndexOutOfBoundsException ex) {
            assertTrue(true);
        }

        try {
            byteAToInt(secondCase, 4);
            fail("There are should be an exception.");
        } catch (IndexOutOfBoundsException ex) {
            assertTrue(true);
        }

        try {
            byteAToInt(thirdCase, 2);
            fail("There are should be an exception.");
        } catch (IndexOutOfBoundsException ex) {
            assertTrue(true);
        }

        try {
            byteAToInt(thirdCase, 1);
            assertTrue(true);
        } catch (IndexOutOfBoundsException ex) {
            fail("There are shouldn't be an exception.");
        }
    }
}