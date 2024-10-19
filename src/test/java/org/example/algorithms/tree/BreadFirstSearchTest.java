package org.example.algorithms.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BreadFirstSearchTest {

    private BreadthFirstSearch obj;

    @BeforeEach
    public void setup() {
        obj = new BreadthFirstSearch();
    }

    @Test
    public void testNoTree() {
        assertEquals(obj.bfs(null), "");
    }

    @Test
    public void testOneNode() {
        assertEquals(obj.bfs(new Node("First")), "First");
    }

    @Test
    public void testTwoLevels() {
        var root = new Node("First");
        root.setLeft(new Node("Second"));
        root.setRight(new Node("Third"));
        assertEquals(obj.bfs(root), "First,Second,Third");
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

        assertEquals("1,2,3,4,5,6", obj.bfs(n1));
    }
}

