package org.example.algorithms.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepthFirstSearchTest {

    private DepthFirstSearch obj;

    @BeforeEach
    public void setup() {
        obj = new DepthFirstSearch();
    }

    @Test
    public void testNoTree() {
        assertEquals(obj.dfsPreOrder(null), "");
    }

    @Test
    public void testOneNode() {
        assertEquals(obj.dfsPreOrder(new Node("First")), "First");
    }

    @Test
    public void testTwoLevels() {
        var root = new Node("First");
        root.setLeft(new Node("Second"));
        root.setRight(new Node("Third"));
        assertEquals(obj.dfsPreOrder(root), "First,Second,Third");
    }

    @Test
    public void testThreeLevelsInComplete() {
        var n1 = new Node("1");
        var n2 = new Node("2");
        var n3 = new Node("3");
        var n4 = new Node("4");
        var n5 = new Node("5");
        var n6 = new Node("6");
        n1.setLeft(n2);
        n1.setRight(n3);
        n2.setLeft(n4);
        n2.setRight(n5);
        n3.setLeft(n6);

        assertEquals("1,2,4,5,3,6", obj.dfsPreOrder(n1));
        assertEquals("4,2,5,1,6,3", obj.dfsInOrder(n1));
        assertEquals("4,5,2,6,3,1", obj.dfsPostOrder(n1));
    }
}
