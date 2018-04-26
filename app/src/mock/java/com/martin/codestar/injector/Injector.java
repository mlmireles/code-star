package com.martin.codestar.injector;

import com.martin.codestar.main.IMainModel;
import com.martin.codestar.mainMock.MainReposModelMock;

/**
 * Created by Martín on 25/04/2018
 *
 */

public class Injector {

    public static IMainModel.Repos provideMainReposModel() {
        return new MainReposModelMock();
    }
}
