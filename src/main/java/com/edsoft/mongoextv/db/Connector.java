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
public interface Connector {

    /**
     *
     * @param methodName
     * @return
     */
    public String findAndRun(String methodName);
}
