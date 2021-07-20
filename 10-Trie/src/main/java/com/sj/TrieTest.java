package com.sj;

public class TrieTest {

    public static void main(String[] args) {
        Trie<Integer> trie = new Trie<>();
        trie.add("abc", 200);
        trie.add("abcd", 100);
        trie.remove("abcd");
        System.out.println(trie.startsWith("abcd"));
        System.out.println(trie.startsWith("abc"));
    }

}
