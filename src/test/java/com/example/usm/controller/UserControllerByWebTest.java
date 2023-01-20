package com.example.usm.controller;

import com.example.usm.dto.UserResponseDTO;
import com.example.usm.utility.UserMapping;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerByWebTest extends AbstractTest{

    @Test
    public void testGetAll() throws Exception {
//        String uri = "/users";
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        int content = mvcResult.getResponse().getContentLength();
//        System.out.println("contentttttttttttttt->>>>" + content);
//        //UserResponseDTO[] users = super.mapFromJson(content, UserResponseDTO[].class);
//        assertTrue(content > 0);

        mvc.perform(MockMvcRequestBuilders
                        .get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

    }

    @Test
    public void testFindBySerialNumber() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/users/1111-6543-8765-0953")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }


}
