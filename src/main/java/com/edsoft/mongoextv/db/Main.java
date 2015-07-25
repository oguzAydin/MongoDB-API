/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edsoft.mongoextv.db;

/**
 *
 * @author edsoft
 */
public class Main {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        
        DBConnect db = new DBConnect();
        long start = System.nanoTime();
        for(int i = 0; i < 1000; i++) {
            System.out.println(i);
            db.findAndRun("getListShards");
        }
        long end = System.nanoTime();
        System.out.println((end - start) / 1000000);
    }
}

