package com.greenature.dockerwatch.services;

import com.greenature.dockerwatch.constants.DockerCommands;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class DockerService {

    public String getDockerImages() {
        ProcessBuilder ps = new ProcessBuilder(DockerCommands.DOCKER_IMAGES);
        ps.redirectErrorStream(true);

        Process pr = null;
        try {
            pr = ps.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pr != null;
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        StringBuilder op = new StringBuilder();
        String line;
        while (true) {
            try {
                if ((line = in.readLine()) == null) break;
                op.append(line);
                pr.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return op.toString();
    }
}
