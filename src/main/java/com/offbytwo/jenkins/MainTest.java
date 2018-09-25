package com.offbytwo.jenkins;

import com.google.common.base.Optional;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.model.*;
import com.plus.util.JSONUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @author plus me
 * @date 2018/9/12
 */
public class MainTest {
    //http://10.0.12.21/service/jenkins/
    //http://10.0.12.33:10102/job/pf_test_alarm/
    public static void main(String args[]) throws URISyntaxException, IOException {
        URI uri = new URI("http://10.0.12.33:10102/");

        JenkinsHttpClient jenkinsHttpClient = new JenkinsHttpClient(uri);

        JenkinsServer jenkinsServer = new JenkinsServer(uri, "ts", "123456");

        Map<String, Job> jm = jenkinsServer.getJobs("platform-test");

//       / JobWithDetails jobWithDetails = jenkinsServer.getJob("pf_test_deploy_ops-auth-api");

        Job job = jm.get("pf_test_deploy_ops-auth-api");
       // job.setUrl(errUrl2okUrl("http://10.0.12.21/service/jenkins/",job.getUrl()));
        //QueueReference queueReference = job.build();

        JobWithDetails jobWithDetails = job.details();
        jobWithDetails.setUrl(errUrl2okUrl("http://10.0.12.21/service/jenkins/",jobWithDetails.getUrl()));
        Build build = jobWithDetails.getLastBuild();

        build.setUrl(errUrl2okUrl("http://10.0.12.21/service/jenkins/",build.getUrl()));
        TestResult testResult = build.getTestResult();
        Optional<FolderJob> optional = jenkinsServer.getFolderJob(job);
        //JSONUtils.beautyPrint(jm);
        optional.get().details();
        JSONUtils.beautyPrint(optional.get());
        //   JSONUtils.beautyPrint(jobWithDetails);

    }

    public static String errUrl2okUrl(String root, String errUrl) {
        String res = root;
        String[] ss = errUrl.split("/");
        for (int i = 3; i < ss.length; i++) {
            res = res + "/" + ss[i];
        }
        return res+"/";

    }
}
