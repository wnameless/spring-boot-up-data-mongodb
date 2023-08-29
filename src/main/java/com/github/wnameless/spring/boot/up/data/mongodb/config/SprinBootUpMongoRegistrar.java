package com.github.wnameless.spring.boot.up.data.mongodb.config;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class SprinBootUpMongoRegistrar implements ImportBeanDefinitionRegistrar {

  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
      BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
    GenericBeanDefinition springBootUpMongoEventListener = new GenericBeanDefinition();
    springBootUpMongoEventListener.setBeanClass(SpringBootUpMongoEventListener.class);
    registry.registerBeanDefinition(
        importBeanNameGenerator.generateBeanName(springBootUpMongoEventListener, registry),
        springBootUpMongoEventListener);
  }

}
