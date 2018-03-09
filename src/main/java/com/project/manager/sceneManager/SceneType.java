package com.project.manager.sceneManager;

public enum SceneType {
    LOGIN {
        @Override
        Integer getId() {
            return 0;
        }
    }, REGISTRATION {
        @Override
        Integer getId() {
            return 1;
        }
    };

    abstract Integer getId();
}
