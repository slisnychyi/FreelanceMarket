package com.danit.server;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class DnsLookup {
    public static void main(String[] args) throws UnknownHostException {
        final InetAddress byName = InetAddress.getByName("google.com");
        System.out.println(byName);
        Arrays.stream(InetAddress.getAllByName("google.com")).forEach(System.out::println);
    }
}
