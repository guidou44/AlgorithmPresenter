package com.algorithmpresenter.FactoriesTests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.algorithmpresenter.domain.DomainCollection;
import com.algorithmpresenter.factories.SimpleFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SimpleFactoryTests {

  @Autowired private SimpleFactory simpleFactory;

  @Test
  public void givenSimpleFactory_whenAskingForChildInstanceByName_thenItReturnsProperInstance()
      throws Exception {
    assertNotNull(simpleFactory.getChildInstance("SortedDomainCollection", DomainCollection.class));
  }

  @Test
  public void givenSimpleFactory_whenAskingForInvalidInstanceByName_thenItThrows()
      throws Exception {
    assertThrows(
        InstantiationException.class,
        () -> simpleFactory.getChildInstance("not a child", DomainCollection.class));
  }

  @Test
  public void givenSimpleFactory_whenAskingForDirectInstanceByName_thenReturnsProperInstance()
      throws Exception {
    assertNotNull(simpleFactory.getDirectInstance(DomainCollection.class));
  }
}
