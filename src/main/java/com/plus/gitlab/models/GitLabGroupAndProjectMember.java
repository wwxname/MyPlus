package com.plus.gitlab.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dongdong
 * @date 2018/5/21
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GitLabGroupAndProjectMember {

    public static final String URL = "/members";

    private Integer id;
    private String username;
    private String name;
    private String state;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("access_level")
    private Integer accessLevel;
    @JsonProperty("expires_at")
    private String expiresAt;

    public GitLabAccessLevel getAccessLevel() {
        return GitLabAccessLevel.fromAccessValue(accessLevel);
    }

}
