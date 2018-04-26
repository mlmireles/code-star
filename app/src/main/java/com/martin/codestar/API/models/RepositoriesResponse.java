package com.martin.codestar.API.models;

import java.util.List;

public class RepositoriesResponse {

    private List<Repository> repositories;

    public List<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }
}
