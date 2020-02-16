package com.algorithmpresenter.factories;

import java.util.Set;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "SimpleFactory")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SimpleFactory implements IFactory {

  private static final String packagePath = "com.algorithmpresenter";

  public <T> T getChildInstance(String subClassName, Class<T> parentAbstractClass)
      throws Exception {
    Set<Class<? extends T>> subClasses = getSubClassOfBaseAbstractClass(parentAbstractClass);

    Class<? extends T> targetSubClass =
        subClasses.stream()
            .filter(
                tsc -> {
                  String[] classNameArray = tsc.getName().split("\\.");
                  return classNameArray[classNameArray.length - 1].equalsIgnoreCase(subClassName);
                })
            .findFirst()
            .orElse(null);

    if (targetSubClass == null) {
      throw new InstantiationException("Could not find SubClass with matching name");
    }

    return generateInstanceWithNoParameterConstructor(targetSubClass);
  }

  public <T> T getDirectInstance(Class<T> classType) throws Exception {
    return generateInstanceWithNoParameterConstructor(classType);
  }

  private <T> T generateInstanceWithNoParameterConstructor(Class<T> classType) throws Exception {
    return classType.getDeclaredConstructor().newInstance();
  }

  private <T> Set<Class<? extends T>> getSubClassOfBaseAbstractClass(Class<T> parentAbstractClass) {
    Reflections reflections = new Reflections(packagePath, new SubTypesScanner(false));
    return reflections.getSubTypesOf(parentAbstractClass);
  }
}
