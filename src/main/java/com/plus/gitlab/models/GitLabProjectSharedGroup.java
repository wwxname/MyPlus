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
public class GitLabProjectSharedGroup {

    @JsonProperty("group_id")
    private int groupId;
    @JsonProperty("group_name")
    private String groupName;
    @JsonProperty("group_access_level")
    private int groupAccessLevel;

    public GitLabAccessLevel getGroupAccessLevel() {
        return GitLabAccessLevel.fromAccessValue(groupAccessLevel);
    }
}
