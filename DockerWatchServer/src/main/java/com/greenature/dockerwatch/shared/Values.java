package com.greenature.dockerwatch.shared;

import com.greenature.dockerwatch.model.Host;

import java.util.HashSet;
import java.util.Set;

public class Values {
    public static Set<Host> allHosts = new HashSet<>();
//    static {
//        Host localhost = new Host("tcp://localhost:2375/", true);
//        allHosts.add(localhost);
//    }

    private static Values values;



}

