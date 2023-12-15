package com.example.servicemodule;

import com.example.servicemodule.s.Parsing.ParseImageFromSite;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ParseImageFromSiteTest {

    @Test
    public void testGetModels() {
        ParseImageFromSite parser = new ParseImageFromSite();
        try {
            parser.getModels();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}