package com.bzfar.io;

import com.bzfar.config.FileDefinition;
import com.bzfar.enums.FileOrigin;

/**
 * @author Ethons
 * @date 2021-7-6 17:20
 */
public abstract class FileDefinitionMethod {

   protected FileDefinition fileDefinition;

   protected final String PATH = "src/main/resources/";

   private FileDefinition getFileDefinition(){
       if(null == fileDefinition){
           FileDefinition fileDefinition = new FileDefinition();
           this.fileDefinition = fileDefinition;
       }
       return this.fileDefinition;
   }

    public FileDefinitionMethod(String address){
        FileDefinition fileDefinition = getFileDefinition();
        fileDefinition.setOrigin(FileOrigin.ADDRESS);
    }

    public FileDefinitionMethod (String url , String newName){

    }

   public void transform(){
       FileDefinition fileDefinition = transformDefinition();
       this.fileDefinition = fileDefinition;
   }



   abstract FileDefinition transformDefinition();
}
