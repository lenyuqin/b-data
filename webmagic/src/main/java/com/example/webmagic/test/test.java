package com.example.webmagic.test;

// An highlighted block
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class test {
    public static String table="fZodR9XQDSUm21yCkr6zBqiveYah8bt4xsWpHnJE7jL5VG3guMTKNPAwcF";
    public static ArrayList<Map<String,Object>>  tr=new ArrayList();
    public static int s[] ={11,10,3,8,4,6};
    public static double xor=177451812.0;
    public static double add= 8728348608.0;


    public static void main(String[] args) {
        test test=new test();
        char a[]=table.toCharArray();
        for (int i=0;i<58;i++){
            Map<String,Object> map=new HashMap<>();
            map.put(String.valueOf(a[i]) ,i);
            tr.add(map);
        }
        test.dec("BV1kQ4y1P7Nm");
        test.enc("710932975");

    }
    //bv转av
    public double dec(String x){
        double r=0;
        char xe[]=x.toCharArray();
        for(int i=0;i<6;i++){
            for(Map c:tr){
                for(Object key : c.keySet()){
                    if(key.equals(String.valueOf(xe[s[i]])) ){
                        Object value = c.get(key);
                        r+=Integer.parseInt(value.toString())*Math.pow(58,i);
                    }

                }
            }
        }
        return (int)(r-add)^(int)(xor);
    }
    //av转bv
    public String enc(String x){
        int temp= Integer.valueOf(x);
        char a[]=table.toCharArray();
        char arr[]={'B','V','1',' ',' ','4',' ','1',' ','7',' ',' '};
        System.out.println((Integer.valueOf(x)^(int)(xor))+add);
        double r=(Integer.valueOf(x)^(int)(xor))+add;
        for(int i=0;i<6;i++){
            arr[s[i]]=a[(int) (Math.floor(r/(Math.pow(58,i))) %58)];
            int dd= (int) (Math.floor(r/(Math.pow(58,i))) %58);
            System.out.println(dd);
        }
        System.out.println(String.valueOf(arr));
        return String.valueOf(arr);
    }
}
