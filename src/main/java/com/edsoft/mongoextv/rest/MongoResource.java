/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edsoft.mongoextv.rest;

import com.edsoft.mongoextv.db.DBConnect;
import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * REST Web Service
 *
 * @author Yusuf ÖNDER
 */
@Path("mongo")
public class MongoResource {

    private final DBConnect con;

    /**
     * Creates a new instance of MongoResource
     */
    public MongoResource() {
        con = new DBConnect();
    }

    /**
     *
     * @return
     */
    @GET
    @Path("listShards")
    @Produces("application/json")
    public String getListShards() {
        return con.findAndRun("getListShards");
    }

    /**
     *
     * @return @throws UnknownHostException
     */
    @GET
    @Path("listDatabases")
    @Produces("application/json")
    public String getListDatabases() throws UnknownHostException {
        return con.findAndRun("getListDatabases");
    }

    /**
     *
     * @return @throws UnknownHostException
     */
    @GET
    @Path("buildInfo")
    @Produces("application/json")
    public String getBuildInfo() throws UnknownHostException {
        return con.findAndRun("getBuildInfo");
    }

    /**
     *
     * @return @throws UnknownHostException
     */
    @GET
    @Path("connPoolStats")
    @Produces("application/json")
    public String getConnPoolStats() throws UnknownHostException {
        return con.findAndRun("getConnPoolStats");
    }

    /**
     *
     * @return @throws UnknownHostException
     */
    @GET
    @Path("stats")//Shardların Durumu
    @Produces("application/json")
    public String getServerStats() throws UnknownHostException {
        return con.findAndRun("getDBStatus");
    }

    /**
     *
     * @return @throws UnknownHostException
     */
    @GET
    @Path("netstat")
    @Produces("application/json")
    public String getNetstat() throws UnknownHostException {
        return con.findAndRun("getNetstat");
    }

    /**
     *
     * @return @throws UnknownHostException
     */
    @GET
    @Path("whatsmyuri")
    @Produces("application/json")
    public String getwhatsmyuri() throws UnknownHostException {
        return con.findAndRun("getwhatsmyuri");
    }

    /**
     *
     * @return @throws UnknownHostException
     */
    @GET
    @Path("hostInfo")
    @Produces("application/json")
    public String getHostInfo() throws UnknownHostException {
        return con.findAndRun("getHostInfo");
    }

    /**
     *
     * @return @throws UnknownHostException
     */
    @GET
    @Path("asserts")
    @Produces("application/json")
    public String getAsserts() throws UnknownHostException {
        return con.findAndRun("getAsserts");
    }

    /**
     *
     * @return @throws UnknownHostException
     */
    @GET
    @Path("serverStatus")
    @Produces("application/json")
    public String getServerStatus() throws UnknownHostException {
        return con.findAndRun("getServerStatus");
    }

    /**
     *
     * @return @throws UnknownHostException
     */
    @GET
    @Path("mem")
    @Produces("application/json")
    public String getMem() throws UnknownHostException {
        return con.findAndRun("getMem");
    }

    /**
     *
     * @return
     */
    @GET
    @Path("methods")
    @Produces("application/json")
    public List<Method> getMethod() {
        return con.listMethods();
    }
}
