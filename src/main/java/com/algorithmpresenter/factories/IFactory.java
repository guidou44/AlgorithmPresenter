package com.algorithmpresenter.factories;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public interface IFactory {
  <T> T getChildInstance(String subClassName, Class<T> parentAbstractClass) throws Exception;

  <T> T getDirectInstance(Class<T> classType) throws Exception;
}
