package com.bzfar.util;

import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreeUtil {

    public static <T extends Tree> List<T> getTree(List<T> childs){
        List<T> result = new ArrayList<>();
        List<T> parents = childs.stream().filter(item -> item.getParentId().equals(-1)).collect(Collectors.toList());
        childs.removeAll(parents);
        parents.forEach(root -> result.add(getChild(childs , root)));
        return result;
    }

    private static <T extends Tree> T getChild(List<T> childs , T parent){
        List<T> childList = getChildList(childs, parent);
        if(!ObjectUtils.isEmpty(childList)){
            childs.removeAll(childList);
            for (T child : childList){
                if(parent.getChild() == null){
                    parent.setChild(new ArrayList<>());
                }
                parent.getChild().add(getChild(childs , child));
            }
        }
        return parent;
    }

    private static <T extends Tree> List<T> getChildList(List<T> childs , T parent){
        List<T> list = childs.stream().filter(item -> item.getParentId().equals(parent.getId())).collect(Collectors.toList());
        return list;
    }
}
