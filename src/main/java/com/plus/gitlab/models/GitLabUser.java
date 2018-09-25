package com.plus.gitlab.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

/**
 * @author dongdong
 * @date 2018/5/21
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GitLabUser {

    public static final String URL = "/users";

    private Integer id;
    private String username;
    private String email;
    private String name;
    private String state;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("is_admin")
    private Boolean isAdmin;

    private String skype;
    @JsonProperty("linkedin")
    private String linkedin;
    @JsonProperty("twitter")
    private String twitter;
    @JsonProperty("website_url")
    private String websiteUrl;
    private String organization;
    @JsonProperty("last_sign_in_at")
    private String lastSignInAt;
    @JsonProperty("confirmed_at")
    private String confirmedAt;
    @JsonProperty("theme_id")
    private Integer themeId;
    @JsonProperty("last_activity_on")
    private String lastActivityOn;
    @JsonProperty("color_scheme_id")
    private Integer colorSchemeId;
    @JsonProperty("projects_limit")
    private Integer projectsLimit;
    @JsonProperty("current_sign_in_at")
    private String currentSignInAt;
    private List<GitLabProjectIdentity> identities;
    @JsonProperty("can_create_group")
    private Boolean canCreateGroup;
    @JsonProperty("can_create_project")
    private Boolean canCreateProject;
    @JsonProperty("two_factor_enabled")
    private Boolean twoFactorEnabled;
    private Boolean external;

    public static String getURL() {
        return URL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getLastSignInAt() {
        return lastSignInAt;
    }

    public void setLastSignInAt(String lastSignInAt) {
        this.lastSignInAt = lastSignInAt;
    }

    public String getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(String confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public String getLastActivityOn() {
        return lastActivityOn;
    }

    public void setLastActivityOn(String lastActivityOn) {
        this.lastActivityOn = lastActivityOn;
    }

    public Integer getColorSchemeId() {
        return colorSchemeId;
    }

    public void setColorSchemeId(Integer colorSchemeId) {
        this.colorSchemeId = colorSchemeId;
    }

    public Integer getProjectsLimit() {
        return projectsLimit;
    }

    public void setProjectsLimit(Integer projectsLimit) {
        this.projectsLimit = projectsLimit;
    }

    public String getCurrentSignInAt() {
        return currentSignInAt;
    }

    public void setCurrentSignInAt(String currentSignInAt) {
        this.currentSignInAt = currentSignInAt;
    }

    public List<GitLabProjectIdentity> getIdentities() {
        return identities;
    }

    public void setIdentities(List<GitLabProjectIdentity> identities) {
        this.identities = identities;
    }

    public Boolean getCanCreateGroup() {
        return canCreateGroup;
    }

    public void setCanCreateGroup(Boolean canCreateGroup) {
        this.canCreateGroup = canCreateGroup;
    }

    public Boolean getCanCreateProject() {
        return canCreateProject;
    }

    public void setCanCreateProject(Boolean canCreateProject) {
        this.canCreateProject = canCreateProject;
    }

    public Boolean getTwoFactorEnabled() {
        return twoFactorEnabled;
    }

    public void setTwoFactorEnabled(Boolean twoFactorEnabled) {
        this.twoFactorEnabled = twoFactorEnabled;
    }

    public Boolean getExternal() {
        return external;
    }

    public void setExternal(Boolean external) {
        this.external = external;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GitLabUser that = (GitLabUser) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(email, that.email) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, email, name);
    }

    @Override
    public String toString() {
        return "GitLabUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", isAdmin=" + isAdmin +
                ", skype='" + skype + '\'' +
                ", linkedin='" + linkedin + '\'' +
                ", twitter='" + twitter + '\'' +
                ", websiteUrl='" + websiteUrl + '\'' +
                ", organization='" + organization + '\'' +
                ", lastSignInAt='" + lastSignInAt + '\'' +
                ", confirmedAt='" + confirmedAt + '\'' +
                ", themeId=" + themeId +
                ", lastActivityOn='" + lastActivityOn + '\'' +
                ", colorSchemeId=" + colorSchemeId +
                ", projectsLimit=" + projectsLimit +
                ", currentSignInAt='" + currentSignInAt + '\'' +
                ", identities=" + identities +
                ", canCreateGroup=" + canCreateGroup +
                ", canCreateProject=" + canCreateProject +
                ", twoFactorEnabled=" + twoFactorEnabled +
                ", external=" + external +
                '}';
    }
}
