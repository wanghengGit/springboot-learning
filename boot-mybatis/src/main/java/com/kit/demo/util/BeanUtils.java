//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kit.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BeanUtils {
    private static Logger logger = LoggerFactory.getLogger(BeanUtils.class);

    public BeanUtils() {
    }

    public static List<Field> getAllDeclaredFields(Class<?> clz) {
        List<Field> ret = new ArrayList();
        if (clz != null) {
            for(Class clazz = clz; clazz != Object.class; clazz = clazz.getSuperclass()) {
                try {
                    Field[] fields = clazz.getDeclaredFields();
                    if (fields != null) {
                        ret.addAll(Arrays.asList(fields));
                    }
                } catch (Exception var4) {
                    logger.warn("failed get delcared fields for:{}", clz.getName(), var4);
                }
            }
        }

        return ret;
    }

    public static List<Field> getAllFields(Class<?> clz) {
        List<Field> ret = new ArrayList();
        if (clz != null) {
            for(Class clazz = clz; clazz != Object.class; clazz = clazz.getSuperclass()) {
                try {
                    Field[] fields = clazz.getFields();
                    if (fields != null) {
                        ret.addAll(Arrays.asList(fields));
                    }
                } catch (Exception var4) {
                    logger.warn("failed get fields for:{}", clz.getName(), var4);
                }
            }
        }

        return ret;
    }

    public static Field getDeclaredField(Class<?> clz, String fieldName) throws Exception {
        Field ret = null;
        if (clz != null && StringUtils.hasText(fieldName)) {
            String[] propArr = StringUtils.tokenizeToStringArray(fieldName, "\\.");
            Class<?> curClz = clz;
            Field curField = null;
            String[] var6 = propArr;
            int var7 = propArr.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                for(String targetPropName = var6[var8]; curClz != Object.class; curClz = curClz.getSuperclass()) {
                    try {
                        curField = curClz.getDeclaredField(targetPropName);
                    } catch (Exception var11) {
                    }

                    if (curField != null) {
                        break;
                    }
                }

                if (curField == null) {
                    break;
                }

                curClz = curField.getType();
                ret = curField;
            }
        }

        return ret;
    }

    public static Field getField(Class<?> clz, String fieldName) throws Exception {
        Field ret = null;
        if (clz != null && StringUtils.hasText(fieldName)) {
            String[] propArr = StringUtils.tokenizeToStringArray(fieldName, "\\.");
            Class<?> curClz = clz;
            Field curField = null;
            String[] var6 = propArr;
            int var7 = propArr.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                for(String targetPropName = var6[var8]; curClz != Object.class; curClz = curClz.getSuperclass()) {
                    try {
                        curField = curClz.getField(targetPropName);
                    } catch (Exception var11) {
                    }

                    if (curField != null) {
                        break;
                    }
                }

                if (curField == null) {
                    break;
                }

                curClz = curField.getType();
            }
        }

        return (Field)ret;
    }

    public static Object getFieldValue(Object obj, String fieldName) throws Exception {
        return getPropertyValue(obj, fieldName);
    }

    public static Field getTargetField(Object targetObj, String propName) throws Exception {
        String[] propArr = StringUtils.tokenizeToStringArray(propName, "\\.");
        Class<? extends Object> localTargetClass = targetObj.getClass();
        Field targetField = null;
        String[] var5 = propArr;
        int var6 = propArr.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            String targetPropName = var5[var7];
            targetField = localTargetClass.getDeclaredField(targetPropName);
            localTargetClass = targetField.getType();
        }

        return targetField;
    }

    public static Object checkTargetObj(Object targetObj, String propName) throws Exception {
        String[] propArr = StringUtils.tokenizeToStringArray(propName, "\\.");
        Object lowestTargetObj = targetObj;
        if (propArr.length > 1) {
            for(int i = 0; i < propArr.length - 1; ++i) {
                Field tmpField = lowestTargetObj.getClass().getDeclaredField(propArr[i]);
                tmpField.setAccessible(true);
                Object propObj = tmpField.get(lowestTargetObj);
                if (propObj == null) {
                    propObj = tmpField.getType().newInstance();
                    tmpField.set(targetObj, propObj);
                }

                lowestTargetObj = propObj;
            }
        }

        return lowestTargetObj;
    }

    public static boolean isBasicType(Object obj) {
        boolean ret = obj != null && (obj instanceof Number || obj instanceof String || obj instanceof Date || obj instanceof Boolean);
        return ret;
    }

    public static Object getPropertyValue(Object targetObj, String propName) throws Exception {
        if (targetObj == null) {
            return null;
        } else {
            String[] propArr = StringUtils.tokenizeToStringArray(propName, "\\.");
            if (!(targetObj instanceof Collection)) {
                Object tmpTargetObj = targetObj;

                for(int i = 0; i < propArr.length; ++i) {
                    if (tmpTargetObj instanceof Collection) {
                        tmpTargetObj = getPropertyValue(tmpTargetObj, propArr[i]);
                    } else {
                        try {
                            Method mtd = tmpTargetObj.getClass().getMethod(generateGetMethodName(propArr[i]));
                            tmpTargetObj = mtd.invoke(tmpTargetObj);
                        } catch (Exception var7) {
                            Field tmpField = tmpTargetObj.getClass().getDeclaredField(propArr[i]);
                            tmpField.setAccessible(true);
                            tmpTargetObj = tmpField.get(tmpTargetObj);
                        }
                    }

                    if (tmpTargetObj == null) {
                        return null;
                    }
                }

                return tmpTargetObj;
            } else {
                List<Object> ret = new ArrayList();
                Iterator var4 = ((Collection)targetObj).iterator();

                while(var4.hasNext()) {
                    Object obj = var4.next();
                    ret.add(getPropertyValue(obj, propName));
                }

                return ret;
            }
        }
    }

    private static String generateGetMethodName(String fieldName) {
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    public static Map<?, ?> transferMap(Collection<?> coll, String matchKey) {
        Map<Object, Object> map = new HashMap(100);
        if (coll != null) {
            Iterator var3 = coll.iterator();

            while(var3.hasNext()) {
                Object obj = var3.next();

                try {
                    Field field = obj.getClass().getDeclaredField(matchKey);
                    field.setAccessible(true);
                    Object valueObj = field.get(obj);
                    if (valueObj != null) {
                        map.put(valueObj, obj);
                    }
                } catch (Exception var7) {
                    logger.warn("failed to get value for field:{} of class:{}", matchKey, obj.getClass().getName());
                }
            }
        }

        return map;
    }

    public static Integer getEnumOrdinal(Class<?> enumCls, String displayFieldName, Object value) {
        Integer ret = null;

        try {
            if (StringUtils.isEmpty(value) || enumCls == null) {
                return null;
            }

            Method mtd = enumCls.getDeclaredMethod("values");
            if (mtd != null) {
                mtd.setAccessible(true);
                Object[] items = (Object[])((Object[])mtd.invoke((Object)null));
                Field displayField = null;
                if (StringUtils.hasText(displayFieldName)) {
                    displayField = enumCls.getDeclaredField(displayFieldName);
                    displayField.setAccessible(true);
                }

                for(int i = 0; i < items.length; ++i) {
                    Object itemDisplayValue = null;
                    if (displayField != null) {
                        itemDisplayValue = displayField.get(items[i]);
                    } else {
                        itemDisplayValue = ((Enum)items[i]).name();
                    }

                    if (value.equals(itemDisplayValue)) {
                        ret = i;
                        break;
                    }
                }
            }
        } catch (Exception var9) {
            logger.warn("failed to get ordinal from class:{} for value:{}", enumCls.getName(), value);
        }

        return ret;
    }

    public static Object getEnumValueByDisplayValue(Class<?> enumCls, String displayFieldName, String valueFieldName, Object value) {
        Object ret = null;

        try {
            if (StringUtils.isEmpty(valueFieldName) || StringUtils.isEmpty(value) || enumCls == null) {
                return null;
            }

            Method mtd = enumCls.getDeclaredMethod("values");
            if (mtd != null) {
                mtd.setAccessible(true);
                Object[] items = (Object[])((Object[])mtd.invoke((Object)null));
                Field displayField = null;
                if (StringUtils.hasText(displayFieldName)) {
                    displayField = enumCls.getDeclaredField(displayFieldName);
                    displayField.setAccessible(true);
                }

                Object matchItem = null;
                Object[] var9 = items;
                int var10 = items.length;

                for(int var11 = 0; var11 < var10; ++var11) {
                    Object item = var9[var11];
                    Object itemDisplayValue = null;
                    if (displayField != null) {
                        itemDisplayValue = displayField.get(item);
                    } else {
                        itemDisplayValue = ((Enum)item).name();
                    }

                    if (value.equals(itemDisplayValue)) {
                        matchItem = item;
                        break;
                    }
                }

                if (matchItem != null) {
                    Field valueField = enumCls.getDeclaredField(valueFieldName);
                    valueField.setAccessible(true);
                    ret = valueField.get(matchItem);
                }
            }
        } catch (Exception var14) {
            logger.warn("failed to get ordinal from class:{} for value:{}", enumCls.getName(), value);
        }

        return ret;
    }

    public static Object getEnumDisplayValue(Class<?> enumCls, String displayFieldName, String valueFieldName, Object value) {
        String ret = null;

        try {
            if (StringUtils.isEmpty(valueFieldName) || StringUtils.isEmpty(value) || enumCls == null) {
                return null;
            }

            Method mtd = enumCls.getDeclaredMethod("values");
            if (mtd != null) {
                mtd.setAccessible(true);
                Object[] items = (Object[])((Object[])mtd.invoke((Object)null));
                Field valueField = enumCls.getDeclaredField(valueFieldName);
                valueField.setAccessible(true);
                Object matchItem = null;
                Object[] var9 = items;
                int var10 = items.length;

                for(int var11 = 0; var11 < var10; ++var11) {
                    Object item = var9[var11];
                    if (value.equals(valueField.get(item))) {
                        matchItem = item;
                        break;
                    }
                }

                if (matchItem != null) {
                    if (StringUtils.isEmpty(displayFieldName)) {
                        ret = ((Enum)matchItem).name();
                    } else {
                        Field field = enumCls.getDeclaredField(displayFieldName);
                        field.setAccessible(true);
                        ret = (String)field.get(matchItem);
                    }
                }
            }
        } catch (Exception var13) {
            logger.warn("failed to get display from :{}.{} by {}:{}", new Object[]{enumCls.getName(), displayFieldName, valueFieldName, value});
        }

        return ret;
    }

    public static Object getEnumDisplayValueByOrdinal(Class<?> enumCls, String displayFieldName, int ordinal) {
        String ret = null;

        try {
            if (ordinal < 0 || enumCls == null) {
                return null;
            }

            Method mtd = enumCls.getDeclaredMethod("values");
            if (mtd != null) {
                mtd.setAccessible(true);
                Object item = ((Object[])((Object[])mtd.invoke((Object)null)))[ordinal];
                if (StringUtils.isEmpty(displayFieldName)) {
                    ret = ((Enum)item).name();
                } else {
                    Field field = enumCls.getDeclaredField(displayFieldName);
                    field.setAccessible(true);
                    ret = (String)field.get(item);
                }
            }
        } catch (Exception var7) {
            logger.warn("failed to get display from class:{} for ordinal:{}", enumCls.getName(), ordinal);
        }

        return ret;
    }

    public static Type getFieldGenericType(Field field) {
        if (field.getGenericType() instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType)field.getGenericType()).getActualTypeArguments();
            return types[0];
        } else {
            return field.getType();
        }
    }

    public static Class<?> getFieldGenericClass(Field field) {
        if (field.getGenericType() instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType)field.getGenericType()).getActualTypeArguments();
            return (Class)types[0];
        } else {
            return field.getType();
        }
    }

    public static void setFieldValue(Object obj, String fieldName, Object fieldValue) throws Exception {
        if (obj != null && StringUtils.hasText(fieldName)) {
            String[] propArr = StringUtils.tokenizeToStringArray(fieldName, "\\.");
            Object targetObj = obj;
            Field tmpField = getDeclaredField(obj.getClass(), propArr[0]);
            if (tmpField != null) {
                tmpField.setAccessible(true);

                for(int i = 0; i < propArr.length - 1; ++i) {
                    Object propObj = tmpField.get(targetObj);
                    if (propObj == null) {
                        propObj = tmpField.getType().newInstance();
                        tmpField.set(targetObj, propObj);
                    }

                    targetObj = propObj;
                    tmpField = propObj.getClass().getDeclaredField(propArr[i + 1]);
                    if (tmpField == null) {
                        return;
                    }

                    tmpField.setAccessible(true);
                }

                tmpField.set(targetObj, fieldValue);
            }
        }

    }

    public static List<?> sortList(List<?> list, String propName) {
        if (list != null) {
            list.sort((o1, o2) -> {
                try {
                    Object prop1 = getFieldValue(o1, propName);
                    Object prop2 = getFieldValue(o2, propName);
                    if (prop1 == null) {
                        return -1;
                    } else {
                        return prop2 == null ? 1 : ((Comparable)prop1).compareTo((Comparable)prop2);
                    }
                } catch (Exception var5) {
                    logger.warn("failed to compare object:{} and object:{} with property:{}", new Object[]{o1, o2, propName, var5});
                    return -1;
                }
            });
        }

        return list;
    }


    public static Object transferString2Obj(String str, Class<?> targetClz, String dateFormat) throws Exception {
        Object obj = null;
        if (null != str) {
            if (!Number.class.isAssignableFrom(targetClz) && !Boolean.class.isAssignableFrom(targetClz)) {
                if (String.class.equals(targetClz)) {
                    obj = str;
                } else {
                    if (!Date.class.equals(targetClz)) {
                        throw new Exception("type:" + targetClz + " not supported yet.");
                    }

                    if (!StringUtils.hasText(dateFormat)) {
                        throw new Exception("dateFormat is null for date String:" + str);
                    }

                    try {
                        DateFormat df = new SimpleDateFormat(dateFormat);
                        obj = df.parse(str);
                    } catch (ParseException var5) {
                        logger.warn("failed to parse date:{} with format:{}", str, dateFormat);
                        throw new Exception("can't parse date:" + str + " with format:" + dateFormat, var5);
                    }
                }
            } else {
                try {
                    obj = targetClz.getConstructor(String.class).newInstance(str);
                } catch (Exception var6) {
                    logger.warn("failed to transfer string:{} to type:{}", new Object[]{str, targetClz, var6});
                    throw new Exception("failed to construct param", var6);
                }
            }
        }

        return obj;
    }
}
