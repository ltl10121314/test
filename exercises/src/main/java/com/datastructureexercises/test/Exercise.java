package com.datastructureexercises.test;


import java.util.ArrayList;

public class Exercise {
    public static void main(String[] args) {
        String txdate = getTxdate("20200109", "shjs", "gjs");
        System.out.println(txdate);
    }
    public static String getTxdate(String dates, String jys, String ofi) {
        ArrayList hlist = new ArrayList();
        hlist.add("shjs");
        hlist.add("gjs");
        int hlisindex = hlist.indexOf(jys);
        String[] timesa = { "20200109:1,1", "20200110:1,0", "20200111:0,1",
                "20200112:1,1", "20200113:1,1"};
        String dateout = "99999999";
        int ii = 0;
        int find = 0;
        for(String str : timesa) {
            String[] parts = str.split(":");
            String ifjy = parts[1].split(",")[hlisindex];
            if ( parts[0].equals(dates) ) {
                find = 1;
            }

            if (find == 1 && ifjy.equals("1") )
            {
                if (Integer.toString(ii).equals(ofi)){
                    dateout = parts[0];
                    break;
                }
                ii += 1 ;
            }
        }
        return dateout;
    }

}
