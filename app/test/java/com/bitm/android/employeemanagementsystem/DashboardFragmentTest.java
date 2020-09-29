package com.bitm.android.employeemanagementsystem;

import com.bitm.android.employeemanagementsystem.controller.DashboardFragment;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DashboardFragmentTest extends TestCase {
    DashboardFragment d;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        d = new DashboardFragment();
    }

    @Test
    public void testButtonclick() {
        Assert.assertEquals(true, d.buttonclick());
    }

    @Test
    public void testOnBonus(){
        Assert.assertEquals(20000, d.bonus(10000,2));
    }

    @Test
    public void testOnAge(){
        Assert.assertEquals(20, d.age(2000, 2020));
    }

    @Test
    public void testOnlist(){
        Assert.assertEquals("b", d.list(1));
    }

    @Test
    public void testOnempty(){
        Assert.assertEquals("Not Empty", d.empty(true));
    }


}