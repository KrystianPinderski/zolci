package com.project.manager;

import com.project.manager.registration.RegistrationServiceTest;
import com.project.manager.ui.sceneManager.TestLoginScene;
import com.project.manager.ui.sceneManager.TestRegistrationScene;
import com.project.manager.ui.sceneManager.TestSceneManager;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestSceneManager.class,
        TestLoginScene.class,
        TestRegistrationScene.class,
        RegistrationServiceTest.class})
public class ProjectManagerTestSuite {
}
