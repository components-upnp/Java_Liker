package com.irit.main;

import com.irit.upnp.LikerServer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        new Thread(new LikerServer()).run();
    }
}
