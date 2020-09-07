package com.admin.springboot.lamdbd;

import com.admin.springboot.adminVO.Mask;
import com.admin.springboot.adminVO.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class LamdbaServer {

    private static Gson gson = new GsonBuilder().serializeNulls().create();


    public static void main(String[] args){

    System.out.println("************"+isInRange("10.3.19.66","10.2.16.0/20"));


    String[] books1 = new String[]{
        "book1",
        "book2",
        "book3"
    };

    String[] book2 = new String[5];

    String[] book3 = {
            "book1",
            "book1",
            "book3"
    };








//        List<Mask> maskList = new ArrayList<>();
//        maskList.add(new Mask("3M", "FFP2"));
//        maskList.add(new Mask("5M", "KN95"));
//        maskList.add(new Mask("4M", "N95"));
//
//        System.out.println("*************|||"+gson.toJson(maskList));
//
//
////        maskList.sort((Mask mask1,Mask mask2)->{return mask1.getBrand().compareTo(mask2.getBrand());});
//
//        maskList.sort((mask1,mask2)->{return mask1.getBrand().compareTo(mask2.getBrand());});
//
//
//        System.out.println("*************>>>"+gson.toJson(maskList));
//
//        Runnable runable = ()->{System.out.println("|||||||||||||||||||||||");};
//        runable.run();


//        (maskList.get(0) mask)->System.out.println(maskList.get(0).getBrand());

//        maskList.sort(Mask mask1,Mask mask2)
//        MyLamdbaInterface mylambda =(s) ->System.out.println(s);
    }


    //核心代码，检索IP所属网段
    public static boolean isInRange(String ip, String network) {
        String[] ips = ip.split("\\.");
        int ipAddr = (Integer.parseInt(ips[0]) << 24)
                | (Integer.parseInt(ips[1]) << 16)
                | (Integer.parseInt(ips[2]) << 8)
                | Integer.parseInt(ips[3]);
        int type = Integer.parseInt(network.replaceAll(".*/", ""));
        int mask = 0xFFFFFFFF << (32 - type);
        String networkIp = network.replaceAll("/.*", "");
        String[] networkIps = networkIp.split("\\.");
        int networkIpAddr = (Integer.parseInt(networkIps[0]) << 24)
                | (Integer.parseInt(networkIps[1]) << 16)
                | (Integer.parseInt(networkIps[2]) << 8)
                | Integer.parseInt(networkIps[3]);
        return (ipAddr & mask) == (networkIpAddr & mask);
    }

//    public void println(String s){
//        System.out.println("*************"+s);
//    }


}
