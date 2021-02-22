package com.itzyq.stock.util;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * @className: IdempotentUtil
 * @description: 根据类名和xid确定唯一value值
 * @author: zyq
 * @date: 2021/2/22 14:34
 * @Version: 1.0
 */
public class IdempotentUtil {

    private static Table<Class<?>,String,String> map= HashBasedTable.create();

    public static void addMarker(Class<?> clazz,String xid,String marker){
        map.put(clazz,xid,marker);
    }

    public static String getMarker(Class<?> clazz,String xid){
        return map.get(clazz,xid);
    }

    public static void removeMarker(Class<?> clazz,String xid){
        map.remove(clazz,xid);
    }
}
