package com.example.webmagic.test;

import java.util.HashMap;

public class testApi {
    private static String table = "fZodR9XQDSUm21yCkr6zBqiveYah8bt4xsWpHnJE7jL5VG3guMTKNPAwcF";
    private static HashMap<String, Integer> mp = new HashMap<>();
    private static HashMap<Integer, String> mp2 = new HashMap<>();
    static int ss[] = {11, 10, 3, 8, 4, 6, 2, 9, 5, 7};
    static long xor = 177451812;
    static long add = 8728348608l;

    public static void main(String[] args) {
        //av710932975 BV1kQ4y1P7Nm
        System.out.println(b2v("BV1kQ4y1P7Nm"));
        System.out.println(v2b("av71093297"));
    }


    public static long power(int a, int b) {
        long power = 1;
        for (int c = 0; c < b; c++) {
            power *= a;
        }
        return power;
    }

    public static String b2v(String s) {
        long r = 0;
        for (int i = 0; i < 58; i++) {
            String s1 = table.substring(i, i + 1);
            mp.put(s1, i);
        }
        for (int i = 0; i < 6; i++) {
            r = r + mp.get(s.substring(ss[i], ss[i] + 1)) * power(58, i);
        }
        return "av" + ((r - add) ^ xor);
    }

    public static String v2b(String st) {
        long s = Long.valueOf(st.split("av")[1]);
        StringBuffer sb = new StringBuffer("BV1  4 1 7  ");
        s = (s ^ xor) + add;
        for (int i = 0; i < 58; i++) {
            String s1 = table.substring(i, i + 1);
            mp2.put(i, s1);
        }
        for (int i = 0; i < 6; i++) {
            String r = mp2.get((int) (s / power(58, i) % 58));
            sb.replace(ss[i], ss[i] + 1, r);
        }
        return sb.toString();
    }

}
