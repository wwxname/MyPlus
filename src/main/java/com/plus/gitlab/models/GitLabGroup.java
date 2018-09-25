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
public class GitLabGroup {

    public static final String URL = "/groups";

    private Integer id;
    private String name;
    private String path;
    private String description;
    private String visibility;
    @JsonProperty("lfs_enabled")
    private Boolean lfsEnabled;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("request_access_enabled")
    private Boolean requestAccessEnabled;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("full_path")
    private String fullPath;
    @JsonProperty("parent_id")
    private Integer parentId;

}

