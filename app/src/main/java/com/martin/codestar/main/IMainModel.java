package com.martin.codestar.main;

/**
 * Created by Martín on 23/04/2018
 *
 */

public interface IMainModel {
    void getUser(String username, IMainModelCallback callback);
}
