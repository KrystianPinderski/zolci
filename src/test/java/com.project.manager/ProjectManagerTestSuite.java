package com.project.manager;

import com.project.manager.sceneManager.TestLoginScene;
import com.project.manager.sceneManager.TestRegistrationScene;
import com.project.manager.sceneManager.TestSceneManager;
import com.project.manager.services.RegistrationService;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestSceneManager.class,
        TestLoginScene.class,
        TestRegistrationScene.class})
public class ProjectManagerTestSuite {
}
