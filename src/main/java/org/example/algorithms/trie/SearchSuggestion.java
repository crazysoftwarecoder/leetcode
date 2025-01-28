//package org.example.algorithms.trie;
//
//
//import org.example.algorithms.searching.Search;
//
//import java.util.*;
//
//class SearchSuggestion {
//
//    private static class TrieNode {
//        char data;
//        Map<Character, TrieNode> children;
//        boolean isWordEnd;
//
//        TrieNode(char data) {
//            this.data = data;
//            this.children = new HashMap<>();
//            this.isWordEnd = false;
//        }
//
//        @Override
//        public String toString() {
//            return "Data: = " + data + " WordEnd: " + isWordEnd + " Children [" + children + "]";
//        }
//    }
//
//    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
//        List<List<String>> res = new ArrayList<>();
//
//        TrieNode root = new TrieNode('-');
//        for (String product : products) {
//            insertProduct(root, product, 0);
//        }
//
//        for (int i=1;i<=searchWord.length();i++) {
//            String substr = searchWord.substring(0, i);
//            List<String> entries = searchProducts(root, substr);
//            Collections.sort(entries);
//            if (entries.size() > 3)
//                res.add(entries.subList(0, 3));
//            else
//                res.add(entries);
//        }
//
//        return res;
//    }
//
////    private List<String> searchProducts(TrieNode root, String searchWord) {
////        List<String> res = new ArrayList<>();
////        TrieNode finalNode = root;
////        int i = 0;
////        while (true) {
////            char ch = searchWord.charAt(i);
////            TrieNode child = finalNodes.children.get(ch);
////            if (child == null) return res;
////            finalNode = child;
////            i++;
////            if (i == searchWord.length()) break;
////        }
////
////        System.out.println(finalNode.data);
////        _searchProducts(finalNode, res, searchWord, "");
////        return res;
////    }
//
//    private static void _searchProducts(
//            TrieNode curr,
//            List<String> res,
//            String searchWord,
//            String currStr) {
//        if (curr == null || curr.isWordEnd) {
//            res.add(searchWord + currStr);
//            return;
//        }
//
//        for (TrieNode child : curr.children.values()) {
//            _searchProducts(child, res, searchWord, currStr + child.data);
//        }
//    }
//
//
//
//    private void insertProduct(TrieNode root, String product, int index) {
//        if (index >= product.length()) {
//            root.isWordEnd = true;
//            return;
//        }
//
//        char ch = product.charAt(index);
//
//        root.children.putIfAbsent(ch, new TrieNode(ch));
//        insertProduct(root.children.get(ch), product, index + 1);
//    }
//
//    public static void main(String[] args) {
//        new SearchSuggestion().suggestedProducts(new String[]{"mobile","mouse","moneypot","monitor","mousepad"}, "mouse");
//    }
//}