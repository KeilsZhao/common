package com.bzfar.util;

import com.bzfar.exception.DataException;
import io.swagger.annotations.ApiModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * @author 阿希
 * 断言工具,如果匹配上则抛出
 * @see DataException 异常，可以交由c层捕获错误信息
 * 减少if else
 */
@ApiModel("断言工具")
@Slf4j
public class AssertUtil {

    private static void expression(boolean expression , String message){
        if(expression){
            throw new DataException(message);
        }
    }

    private static void expression(boolean expression , String message, Object...params){
        if(expression){
            String[] split = message.split("\\{\\}");
            String result = "";
            for(int i = 0; i < split.length ; i++){
                String splitstr = split[i];
                if(params.length > i){
                    splitstr = splitstr.replaceAll("=" , "= " + params[i]);
                }
                result = result + splitstr;
            }
            throw new DataException(result);
        }
    }

    /**
     * 当条件允许才会被断言
     * @param bAssert
     * @param object
     * @param message
     */
    public static void assertNull(boolean bAssert, Object object , String message){
        if(bAssert){
            expression(ObjectUtils.isEmpty(object) , message);
        }
    }

    /**
     * 断言为空
     * @param object
     * @param message
     */
    public static void assertNull(Object object , String message){
        assertNull(true , object , message);
    }

    /**
     * 断言为空
     * @param object
     * @param message
     */
    public static void assertNull(Object object , String message , Object...params){
        expression(ObjectUtils.isEmpty(object) , message , params);
    }

    /**
     * 断言为空
     * @param collection
     * @param message
     */
    public static void assertNull(Collection<?> collection, String message){
        expression(CollectionUtils.isEmpty(collection) , message);
    }

    /**
     * 断言为空
     * @param map
     * @param message
     */
    public static void assertNull(Map<? , ?> map, String message){
        expression(CollectionUtils.isEmpty(map) , message);
    }

    /**
     * 条件允许断言为空或者空串
     * @param str
     * @param message
     */
    public static void assertEmpty(boolean bAssert, String str , String message){
        if(bAssert){
            expression(ObjectUtils.isEmpty(str) , message);
        }
    }

    /**
     * 断言为空或者空串
     * @param str
     * @param message
     */
    public static void assertEmpty(String str , String message){
        assertEmpty(true , str , message);
    }

    public static void assertEmpty(Collection<?> collection, String message) {
        expression(CollectionUtils.isEmpty(collection) , message);
    }

    public static void assertEmpty(Map<?, ?> map, String message) {
        expression(CollectionUtils.isEmpty(map) , message);
    }

    public static void assertEmpty(Object[] array, String message) {
        expression(null == array , message);
    }

    public static void assertEquals(String str1 , String str2, String message){
        expression(str1.equals(str2) , message);
    }

    public static void assertNotEquals(String str1 , String str2, String message){
        expression(!str1.equals(str2) , message);
    }

    public static void assertNotEquals(Object o1 , Object o2, String message){
        expression(!o1.equals(o2) , message);
    }

    public static void assertEquals(Integer i1 , Integer i2, String message){
        expression(i1.equals(i2) , message);
    }

    public static void assertEquals(boolean b1 , boolean b2, String message){
        expression(b1== b2 , message);
    }

    public static void assertNotEquals(Integer i1 , Integer i2, String message){
        expression(!i1.equals(i2) , message);
    }

    public static void assertEquals(Object[] o1 , Object[] o2, String message){
        if(null == o1 && null == o2){
            expression(true , "数组不能为空");
        }
        expression(Arrays.equals(o1 , o2) , message);
    }

    public static void assertEquals(Collection<?> c1 , Collection<?> c2 , String message){
        if(null == c1 && null == c2){
            expression(true , "数组不能为空");
        }
        Object[] objects1 = c1.toArray();
        Object[] objects2 = c2.toArray();
        expression(Arrays.equals(objects1 , objects2) , message);
    }

    public static void assertCollectionHaveIn(Collection<?> c1  , Object o1 , String message){
        if(null == c1 || null == o1){
            expression(true , "数组或对象不能为空");
        }
        expression(c1.contains(o1) , message);
    }


    public static void assertCollectionNotHaveIn(Collection<?> c1  , Object o1 , String message){
        if(null == c1 || null == o1){
            expression(true , "数组或对象不能为空");
        }
        expression(!c1.contains(o1) , message);
    }
}
