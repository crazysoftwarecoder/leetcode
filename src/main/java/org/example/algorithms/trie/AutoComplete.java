package org.example.algorithms.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();

    boolean isEnd;

    TrieNode() {
        isEnd = false;
    }
}

public class AutoComplete {

    private TrieNode root;

    public AutoComplete() {
        this.root = new TrieNode();
    }

    public void addWord(String word) {
        if (word==null || word.length()==0) return;

        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            curr.children.putIfAbsent(ch, new TrieNode());
            curr = curr.children.get(ch);
        }

        curr.isEnd = true;
    }

    private void _helper(List<String> results, TrieNode curr, String word) {
        if (curr.isEnd) {
            results.add(word);
        }

        for (Map.Entry<Character, TrieNode> child : curr.children.entrySet()) {
            _helper(results, child.getValue(), word + child.getKey());
        }
    }

    public List<String> autoComplete(String prefix) {
        TrieNode curr = this.root;
        for (char ch : prefix.toCharArray()) {
            if (!curr.children.containsKey(ch)) {
                return new ArrayList<>();
            }
            curr = curr.children.get(ch);
        }

        List<String> results = new ArrayList<>();
        _helper(results, curr, prefix);
        return results;
    }
}
