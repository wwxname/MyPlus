package com.plus.gitlab;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.plus.gitlab.impl.GitLabApiImpl;
import com.plus.gitlab.models.GitLabGroup;
import com.plus.gitlab.models.GitLabUser;
import com.plus.gitlab.util.TokenType;
import com.plus.util.JSONUtils;

import java.util.List;

/**
 * @author plus me
 * @date 2018/9/13
 */
public class MainTest {
    public static void main(String args[]) throws JsonProcessingException {
        GitLabApiImpl gitLabApi = new GitLabApiImpl("http://192.168.3.37:31053", "ssxxQ_PcF6L8yaxoVFar", TokenType.ACCESS_TOKEN, null);

        //List<GitLabUser> list = gitLabApi.findUsers();
        //GitLabUser user = gitLabApi.findUserById(1);

        List<GitLabGroup> list = gitLabApi.findGroups(true,true);
        JSONUtils.beautyPrint(list);
    }
}
