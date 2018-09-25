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
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GitLabNamespace {

    public static final String URL = "/namespaces";

    private Integer id;
    private String name;
    private String path;
    private String kind;
    @JsonProperty("full_path")
    private String fullPath;
    @JsonProperty("parent_id")
    private Integer parentId;
    @JsonProperty("members_count_with_descendants")
    private Integer membersCountWithDescendants;

}

