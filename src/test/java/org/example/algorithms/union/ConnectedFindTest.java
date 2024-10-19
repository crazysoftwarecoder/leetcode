package org.example.algorithms.union;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConnectedFindTest {

    private ConnectedFind obj;

    @Test
    public void testNoConnections() {
        obj = new ConnectedFind(2);
        assertFalse(obj.isConnected(0,1));
        assertTrue(obj.isConnected(0,0));
    }

    @Test
    public void testOneConnection() {
        obj = new ConnectedFind(2);
        obj.union(0,1);
        assertTrue(obj.isConnected(0,1));
    }

    @Test
    public void testTwoConnections() {
        obj = new ConnectedFind(4);
        obj.union(0,1);
        obj.union(2,3);
        assertFalse(obj.isConnected(0,3));
        obj.union(1,2);
        assertTrue(obj.isConnected(0,3));
    }

    @Test
    public void testMultiConnection() {
        obj = new ConnectedFind(10);
        obj.union(0,1);
        obj.union(1,2);
        obj.union(2,3);
        obj.union(3,4);
        obj.union(4,5);
        // no 5 - 6
        obj.union(6,7);
        obj.union(7,8);
        obj.union(8,9);
        assertTrue(obj.isConnected(0,5));
        assertTrue(obj.isConnected(6,9));
        assertFalse(obj.isConnected(0,9));
        obj.union(5,6);
        assertTrue(obj.isConnected(0,9));
    }

    @Test
    public void testTwo() {
        String binary = "1111111111111111111111111111111";
        int integer = Integer.parseInt(binary, 2);
        System.out.println(integer);
        System.out.println(Integer.toBinaryString(integer >> 1));
    }

    @Test
    public void testCase() {
        String binaryString = "11111111111111111111111111111101";
        int intValue = Integer.parseUnsignedInt(binaryString, 2);
        if (binaryString.charAt(0) == '1') {
            intValue = -((1 << binaryString.length()) - intValue);
        }
        System.out.println("Binary String: " + binaryString);
        System.out.println(intValue);
        System.out.println(Integer.toBinaryString(intValue >>> 31));

    }
}
