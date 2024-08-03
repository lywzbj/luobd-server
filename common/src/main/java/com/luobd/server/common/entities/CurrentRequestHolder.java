package com.luobd.server.common.entities;

public class CurrentRequestHolder {

   private static final  ThreadLocal<CurrentUserInfo> local  = new ThreadLocal<>();

   public static CurrentUserInfo get() {
       return local.get();
   }


   public static void set(CurrentUserInfo userInfo) {
       local.set(userInfo);
   }

   public static void remove() {
       local.remove();
   }


}
