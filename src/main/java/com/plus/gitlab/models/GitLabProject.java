package com.plus.gitlab.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author dongdong
 * @date 2018/5/21
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GitLabProject {

    public static final String URL = "/projects";

    private Integer id;
    private String description;
    @JsonProperty("default_branch")
    private String defaultBranch;
    @JsonProperty("visibility")
    private String visibility;
    @JsonProperty("ssh_url_to_repo")
    private String sshUrlToRepo;
    @JsonProperty("http_url_to_repo")
    private String httpUrlToRepo;
    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("tag_list")
    private List<String> tagList;
    private GitLabUser owner;
    private String name;
    @JsonProperty("name_with_namespace")
    private String nameWithNamespace;
    private String path;
    @JsonProperty("path_with_namespace")
    private String pathWithNamespace;
    @JsonProperty("issues_enabled")
    private Boolean issuesEnabled;
    @JsonProperty("open_issues_count")
    private Integer openIssuesCount;
    @JsonProperty("merge_requests_enabled")
    private Boolean mergeRequestsEnabled;
    @JsonProperty("jobs_enabled")
    private Boolean jobsEnabled;
    @JsonProperty("wiki_enabled")
    private Boolean wikiEnabled;
    @JsonProperty("snippets_enabled")
    private Boolean snippetsEnabled;
    @JsonProperty("resolve_outdated_diff_discussions")
    private Boolean resolveOutdatedDiffDiscussions;
    @JsonProperty("container_registry_enabled")
    private Boolean containerRegistryEnabled;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("last_activity_at")
    private String lastActivityAt;
    @JsonProperty("creator_id")
    private Integer creatorId;
    private GitLabNamespace namespace;
    @JsonProperty("import_status")
    private String importStatus;
    @JsonProperty("archived")
    private Boolean archived;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("shared_runners_enabled")
    private Boolean sharedRunnersEnabled;
    @JsonProperty("forks_count")
    private Integer forksCount;
    @JsonProperty("star_count")
    private Integer starCount;
    @JsonProperty("runners_token")
    private String runnersToken;
    @JsonProperty("public_jobs")
    private Boolean publicJobs;
    @JsonProperty("shared_with_groups")
    private List<GitLabProjectSharedGroup> sharedWithGroups;
    @JsonProperty("only_allow_merge_if_pipeline_succeeds")
    private Boolean onlyAllowMergeIfPipelineSucceeds;
    @JsonProperty("only_allow_merge_if_all_discussions_are_resolved")
    private Boolean onlyAllowMergeIfAllDiscussionsAreResolved;
    @JsonProperty("request_access_enabled")
    private Boolean requestAccessEnabled;
    @JsonProperty("merge_method")
    private String mergeMethod;

}

