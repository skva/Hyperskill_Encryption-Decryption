package com.gridu;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    Main main = new Main();

    @org.junit.jupiter.api.Test
    void main() {
    }

    @org.junit.jupiter.api.Test
    void encryptUnicode() {
        assertEquals("bbb", main.encryptUnicode("aaa", 1));
    }

    @org.junit.jupiter.api.Test
    void decryptUnicode() {
        assertEquals("aaa", main.decryptUnicode("bbb", 1));
    }

    @org.junit.jupiter.api.Test
    void encryptShift() {
        assertEquals("ccc", main.encryptShift("bbb", 1));
    }

    @org.junit.jupiter.api.Test
    void decryptShift() {
        assertEquals("aaa", main.decryptShift("bbb", 1));
    }

    @org.junit.jupiter.api.Test
    void readFile() {
        assertEquals("Data", main.readFile("in_test.txt"));
    }

    @org.junit.jupiter.api.Test
    void writeFile() {
    }
}