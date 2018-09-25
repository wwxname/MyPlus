package com.plus.gitlab.models;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author dongdong
 * @date 2018/5/21
 */
public enum GitLabVisibility {

    /**
     * private: ProjectAssetApi access must be granted explicitly for each user.
     * internal: The project can be cloned by any logged in user.
     * public: The project can be cloned without any authentication
     */
    PRIVATE,
    INTERNAL,
    PUBLIC;

    @JsonValue
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
