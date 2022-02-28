package com.bzfar.io;

import com.bzfar.config.FileDefinition;

/**
 * @author Ethons
 * @date 2021-7-6 17:26
 */
public class UrlFileDefinition extends FileDefinitionMethod{


    public UrlFileDefinition(String url, String newName) {
        super(url, newName);
    }

    @Override
    FileDefinition transformDefinition() {
        return null;
    }
}
