//package com.project.manager.config;
//
//import com.project.manager.Main;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//
//@Configuration
//public class ApplicationContextProvider {
////    private
//    private AnnotationConfigApplicationContext context;
//
//    @Autowired
//    public ApplicationContextProvider(AnnotationConfigApplicationContext annotationConfigApplicationContext) {
//        this.context = annotationConfigApplicationContext;
//        if(this.context == null){
//            annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Main.class);
//        }
//    }
//
//    @Bean("context")
//    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
//    public AnnotationConfigApplicationContext getContext() {
//        return this.context;
//    }
//}
