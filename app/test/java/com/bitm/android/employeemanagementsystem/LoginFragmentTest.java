package com.bitm.android.employeemanagementsystem;

import androidx.annotation.ContentView;

import com.bitm.android.employeemanagementsystem.controller.LoginFragment;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginFragmentTest extends TestCase {


        LoginFragment L;

        @Before
        public void setUp() throws Exception {
            L = new LoginFragment();
        }
        @Test



    public void testIdentityE() {
            Assert.assertEquals("admin", L.identityE());
    }

    @Test
    public void testIdentityP() {
            Assert.assertEquals("admin", L.identityP() );

    }
}