package com.charniauski.training.horsesrace.services.testutil;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Created by Andre on 12.11.2016.
 */
public class XMLDB implements BaseCreator {
    @Override
    public void createRelationDB() {
    }

    @Override
    public void createXMLDB() {

        try {
            FileUtils.copyDirectoryToDirectory(new File("src/test/resources/xmlstorage"),new File("D://"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}