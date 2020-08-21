package com.admin.springboot.lamdbd;

import com.admin.springboot.adminVO.Mask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class LamdbaServer {

    private static Gson gson = new GsonBuilder().serializeNulls().create();


    public static void main(String[] args){
        List<Mask> maskList = new ArrayList<>();
        maskList.add(new Mask("3M", "FFP2"));
        maskList.add(new Mask("5M", "KN95"));
        maskList.add(new Mask("4M", "N95"));

        System.out.println("*************|||"+gson.toJson(maskList));


//        maskList.sort((Mask mask1,Mask mask2)->{return mask1.getBrand().compareTo(mask2.getBrand());});

        maskList.sort((mask1,mask2)->{return mask1.getBrand().compareTo(mask2.getBrand());});


        System.out.println("*************>>>"+gson.toJson(maskList));

        Runnable runable = ()->{System.out.println("|||||||||||||||||||||||");};
        runable.run();


//        (maskList.get(0) mask)->System.out.println(maskList.get(0).getBrand());

//        maskList.sort(Mask mask1,Mask mask2)
//        MyLamdbaInterface mylambda =(s) ->System.out.println(s);
    }
//    public void println(String s){
//        System.out.println("*************"+s);
//    }


}
