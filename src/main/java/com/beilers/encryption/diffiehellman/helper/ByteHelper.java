package com.beilers.encryption.diffiehellman.helper;

import java.math.BigInteger;

/**
 * Utility class to convert byte arrays into and from Strings, in Hexadecimal form. Each byte will be represented by two
 * characters, thus the resulting string will be twice the length of the original byte array. Likewise, the byte array
 * will be half the size of the String when converted back.
 * 
 * @author sxupjb
 * 
 */
public class ByteHelper {

    private static final int ALL_BITS          = 0xff;
    private static final int INVERTER          = 256;
    private static final int MAX_POSITIVE_BTYE = 127;
    private static final int RADIX             = 16;

    /**
     * Turns array of bytes into string
     * 
     * @param buf
     *            Array of bytes to convert to hex string
     * @return Generated hex string
     */
    public String toHex(final byte[] buf) {
        final StringBuilder strbuf = new StringBuilder(buf.length * 2);

        for (final byte element : buf) {
            if ((element & ALL_BITS) < RADIX) {
                strbuf.append("0");
            }
            strbuf.append(Long.toString(element & ALL_BITS, RADIX));
        }

        return strbuf.toString();
    }

    /**
     * Turn a String of Hexadecimal numbers into a byte array.
     * 
     * @param hexString
     *            String to convert into a byte array.
     * @return Generated byte array.
     */
    public byte[] fromHex(final String hexString) {
        final int length = hexString.length();
        final byte[] rc = new byte[length / 2];
        for (int i = 0, j = 0; i < length; i += 2, j++) {
            final String hex = hexString.substring(i, i + 2);
            int value = new BigInteger(hex, RADIX).intValue();
            if (value > MAX_POSITIVE_BTYE) {
                value = value - INVERTER;
            }
            rc[j] = (byte) value;
        }
        return rc;
    }

}
