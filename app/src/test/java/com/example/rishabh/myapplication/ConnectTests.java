package com.example.rishabh.myapplication;

import android.support.annotation.NonNull;

import static junit.framework.Assert.assertEquals;

import org.apache.http.NameValuePair;

import org.json.JSONObject;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.*;

import static org.mockito.Mockito.*;
import org.junit.Test;

import org.apache.http.message.BasicNameValuePair;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

//@RunWith(MockitoJUnitRunner.class)
public class ConnectTests {
/*
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void loginValidReturnsSuccess() throws Exception {

        JSONObject fakeJson = new JSONObject("{\"success\":1,\"message\":\"Password correct\"}");


        final JSONParser mock = mock(JSONParser.class);

        when(mock.makeHttpRequest("http://ec2-54-200-47-19.us-west-2.compute.amazonaws.com/login.php", "POST", (List<NameValuePair>) new ArrayList<NameValuePair>(Arrays.asList(new BasicNameValuePair("sjsuid", "009100078"), new BasicNameValuePair("password", "password")))))
                .thenReturn(fakeJson);

        assertEquals(1, Connect.login("0091000787","password"));
        verify(mock).makeHttpRequest();
    }*/
}