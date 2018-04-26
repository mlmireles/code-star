package com.martin.codestar.injector;

import com.martin.codestar.main.IMainModel;
import com.martin.codestar.main.MainReposModel;
import com.martin.codestar.main.MainUserModel;

/**
 * Created by Martín on 23/04/2018
 *
 */

public class Injector {

    public static IMainModel.Users provideMainUserModel() {
        return new MainUserModel();
    }

    public static IMainModel.Repos provideMainReposModel() {
        return new MainReposModel();
    }
}
