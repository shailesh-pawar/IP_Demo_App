package com.techlearn.ip_es.springbootapp;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IPAddressTest {

    @Test
    public void testParseUserName() {
        String ipAddress = "1.1.1.1";
        assertEquals("1.1.1.1", ipAddress);
    }

}
