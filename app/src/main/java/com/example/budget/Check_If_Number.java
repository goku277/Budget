package com.example.budget;
import java.util.*;
import java.util.regex.*;
public class Check_If_Number {
    public static void main(String args[]) {
        Scanner sc= new Scanner(System.in);
        String s1= sc.nextLine();
        check_if_Numbers(s1);
    }
    public static boolean check_if_Numbers(String s1) {
        Pattern pattern= Pattern.compile("\\d+");
        Matcher matcher= pattern.matcher(s1);
        if (matcher.matches()) {
            System.out.println("The String has numbers:");
            return true;
        }
        else {
            System.out.println("The String doesnot contain numbers:");
            return false;
        }
    }
}