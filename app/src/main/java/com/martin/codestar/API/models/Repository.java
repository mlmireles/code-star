package com.martin.codestar.API.models;

import android.support.annotation.NonNull;

import java.util.Comparator;

public class Repository implements Comparable<Repository> {

    private String name;
    private String full_name;
    private String language;
    private int stargazers_count;

    public static final Comparator<Repository> DESCENDING_COMPARATOR = new Comparator<Repository>() {
        @Override
        public int compare(Repository repository, Repository t1) {
            return t1.stargazers_count - repository.stargazers_count;
        }
    };

    public String getName() {
        return name;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getLanguage() {
        return language;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    @Override
    public int compareTo(@NonNull Repository repository) {
        return 0;
    }
}
