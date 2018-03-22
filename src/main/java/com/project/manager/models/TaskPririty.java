package com.project.manager.models;

public enum TaskPririty {
    LOW {
        @Override
        int getPriority() {
            return 1;
        }
    }, MEDIUM {
        @Override
        int getPriority() {
            return 2;
        }
    }, HIGHT {
        @Override
        int getPriority() {
            return 3;
        }
    };

    abstract int getPriority();
}
