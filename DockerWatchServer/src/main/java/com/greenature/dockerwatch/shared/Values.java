package com.greenature.dockerwatch.shared;

import com.greenature.dockerwatch.model.BaseHost;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Values {

    static BaseHost baseHost = new BaseHost();
    static {
        baseHost.setName("Localhost");
        baseHost.setAddress("tcp://localhost:2375");
    }

    public static Set<BaseHost> allHosts = new LinkedHashSet<>(Collections.singletonList(baseHost));


}

