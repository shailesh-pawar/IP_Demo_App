package com.techlearn.ip_es.springbootapp;

import com.techlearn.ip_es.springbootapp.repository.IpAddressRepository;
import com.techlearn.ip_es.springbootapp.rest.IpAddressController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class IPAddressTest {

    @MockBean
    private IpAddressRepository ipAddressRepository;

    @Autowired
    IpAddressController ipAddressController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createRecordTest() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8);
        String ipAddress = "{\"type\": \"IPv4\", \"value\" : \"1.1.1.1\", \"firstSeen\" : 2022-06-13T12:10:11, \"totalCount\" : 10}";
        mockMvc.perform(MockMvcRequestBuilders.post("/")
                .content(ipAddress)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(textPlainUtf8));
    }

    @Test
    public void getRecordTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/1.1.1.1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

}
