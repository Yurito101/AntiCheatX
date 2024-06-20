package me.yurito.anticheatx.utils;

public class ReachUtils {

    public static byte[] Reach(Object object) {
        return object.toString().getBytes();
    }
}