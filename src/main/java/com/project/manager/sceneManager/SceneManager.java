package com.project.manager.sceneManager;

import com.project.manager.sceneManager.scenes.LoginScene;
import com.project.manager.sceneManager.scenes.RegistrationScene;
import com.project.manager.sceneManager.scenes.system.CustomScene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;

public class SceneManager {
    private static SceneManager instance;
    private Stage primaryStage;
    private LoginScene loginScene;
    private RegistrationScene registrationScene;
    private HashMap<Integer, CustomScene> scenes;

    private SceneManager() {
    }

    public static SceneManager getInstance(){
        if(instance == null){
            instance = new SceneManager();
        }
        return instance;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initializeScenes() {
        this.loginScene = new LoginScene(primaryStage);
        this.registrationScene = new RegistrationScene(primaryStage);

        scenes = new HashMap<Integer, CustomScene>() {
            {
                put(SceneType.LOGIN.getId(), loginScene);
                put(SceneType.REGISTRATION.getId(), registrationScene);
            }
        };
    }
    public int getSize(){
        return scenes.size();
    }
    public void showScene(SceneType type) {
        scenes.get(type.getId()).show();
    }

    public void showScene(int sceneID) {
        scenes.get(sceneID).show();
    }
    public void hideScene(SceneType type) {
        scenes.get(type.getId()).hide();
    }

    public void hideScene(int sceneID) {
        scenes.get(sceneID).hide();
    }

    public void closeScene(SceneType type) {
        scenes.get(type.getId()).close();
    }

    public void closeScene(int sceneID) {
        scenes.get(sceneID).close();
    }

    public void showInNewWindow(SceneType type) {
        scenes.get(type.getId()).showInNewScene();
    }

    public void showInNewWindow(int sceneID) {
        scenes.get(sceneID).showInNewScene();
    }

    public void hideNewWindow(SceneType type) {
        scenes.get(type.getId()).hideNewScene();
    }

    public void hideNewWindow(int sceneID) {
        scenes.get(sceneID).hideNewScene();
    }

    public void closeNewWindow(SceneType type) {
        scenes.get(type.getId()).closeNewScene();
    }

    public void closeNewWindow(int sceneID) {
        scenes.get(sceneID).closeNewScene();
    }
}


