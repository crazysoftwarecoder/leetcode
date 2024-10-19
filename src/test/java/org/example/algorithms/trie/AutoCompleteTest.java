package org.example.algorithms.trie;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoCompleteTest {

    private AutoComplete autoComplete = new AutoComplete();

    @Test
    public void test1() {
        autoComplete.addWord("apple");
        autoComplete.addWord("appian");
        autoComplete.addWord("applan");

        var words = autoComplete.autoComplete("app");
        assertEquals(3, words.size());
        assertThat(List.of("applan", "apple", "appian"), containsInAnyOrder(words.toArray()));
    }
}
