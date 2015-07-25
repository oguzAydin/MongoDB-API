/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edsoft.mongoextv.db;

import com.edsoft.mongoextv.rest.MongoResource;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 *
 * @author edsoft
 */
public class DBConnect implements Connector {

    private static final File RESOURCE_FILE;
    private StringBuilder build;
    private static Object instance;
    private static final String PATH;
    private static final SAXReader OPEN_XML;
    private static final String FIRST_PATH;
    private static final File CONFIG_XML;

    static {
        FIRST_PATH = "/home/edsoft/Downloads/SourceCode/path-mapping.xml";
        OPEN_XML = new SAXReader();
        PATH = loadMap();
        RESOURCE_FILE = new File(PATH);
        CONFIG_XML = new File(PATH + "/" + "config.xml");
    }

    /**
     *
     * @param methodName
     * @return
     */
    @Override
    public String findAndRun(String methodName) {
        return runJar(methodName);
    }

    private static String loadMap() {
        String hosts = null;
        try {
            File config = new File(FIRST_PATH);
            Document doc = OPEN_XML.read(config);
            List<Node> nodeList = doc.selectNodes("/resources/resource");
            for (Node n : nodeList) {
                if (n.selectSingleNode("DB").getText().equals("Mongo")) {
                    hosts = n.selectSingleNode("path").getText();
                }
            }
        } catch (DocumentException ex) {
            Logger.getLogger(MongoResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hosts;
    }

    private void initVariable() {
        build = new StringBuilder();
    }

    private static List<String> loadConfig() {
        List<String> hosts = null;
        try {
            hosts = new ArrayList<>();
            Document doc = OPEN_XML.read(CONFIG_XML);
            List<Node> nodeList = doc.selectNodes("/shards/shard");
            for (Node n : nodeList) {
                hosts.add(n.selectSingleNode("host").getText());
            }
        } catch (DocumentException ex) {
            Logger.getLogger(MongoResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hosts;
    }

    private String runJar(String text) {
        configuration();
        Method m = null;
        try {
            m = instance.getClass().getDeclaredMethod(text/*, String.class*/);
            build.append(m.invoke(instance));
            /*List<String> hosts = loadConfig();
             for (String a : hosts) {
             build.append(m.invoke(instance, a));
             }*/
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(MongoResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return build.toString();
    }

    /**
     *
     * @return
     */
    public List<Method> listMethods() {
        configuration();
        Method[] methods = instance.getClass().getDeclaredMethods();
        return Arrays.asList(methods);
    }

    private void configuration() {
        initVariable();
        File jarFile = null;
        for (File f : RESOURCE_FILE.listFiles()) {
            if (f.getName().endsWith(".jar")) {
                try {
                    jarFile = new File(f.getCanonicalPath());
                } catch (IOException ex) {
                    Logger.getLogger(MongoResource.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        URLClassLoader urlClassLoader = null;
        try {
            urlClassLoader = new URLClassLoader(new URL[]{jarFile.toPath().toUri().toURL()});
        } catch (MalformedURLException ex) {
            Logger.getLogger(MongoResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        Class gsonClass = null;
        try {
            gsonClass = urlClassLoader.loadClass("newpackage.Main");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MongoResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            instance = gsonClass.getConstructor().newInstance();
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(MongoResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
