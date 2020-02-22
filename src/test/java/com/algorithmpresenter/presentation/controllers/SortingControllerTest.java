package com.algorithmpresenter.presentation.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.algorithmpresenter.domain.sorting.CollectionContainer;
import com.algorithmpresenter.presentation.controllers.SortingController;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Random;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SortingController.class)
@ComponentScan({"com.algorithmpresenter"})
public class SortingControllerTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @Test
  public void whenMakingARequestToSortingAlgorithm_thenItReturnsProperView() throws Exception {
    mockMvc
        .perform(get("/SortingAlgorithm"))
        .andExpect(status().isOk())
        .andExpect(view().name("SortingView"));
  }

  @Test
  public void whenSendingNewCollectionParameters_thenItReturnsCollectionWithProperLength()
      throws Exception {
    CollectionContainer collectionContainerToSend = getRandomCollectionDtoForPost();

    CollectionContainer collectionContainerReceived =
        getCollectionResultFromNewCollectionPost(collectionContainerToSend);

    assertEquals(
        collectionContainerToSend.getCollectionDimension(),
        collectionContainerReceived.getMainCollection().size());
  }

  @Test
  public void whenSendingConsecutiveCollectionParameters_thenItReturnsNewCollection()
      throws Exception {
    CollectionContainer domainCollectionToSend = getRandomCollectionDtoForPost();
    CollectionContainer firstDomainCollectionReceived =
        getCollectionResultFromNewCollectionPost(domainCollectionToSend);
    CollectionContainer secondDomainCollectionReceived =
        getCollectionResultFromNewCollectionPost(domainCollectionToSend);

    assertNotEquals(
        firstDomainCollectionReceived.getMainCollection(),
        secondDomainCollectionReceived.getMainCollection());
  }

  // region private methods

  private CollectionContainer getCollectionResultFromNewCollectionPost(
      CollectionContainer domainCollectionToSend) throws Exception {
    MvcResult result =
        mockMvc
            .perform(
                MockMvcRequestBuilders.post("/SortingAlgorithm/SetNewCollection")
                    .content(objectMapper.writeValueAsString(domainCollectionToSend))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.mainCollection").exists())
            .andReturn();
    String responseContent = result.getResponse().getContentAsString();
    return objectMapper.readValue(responseContent, CollectionContainer.class);
  }

  private CollectionContainer getRandomCollectionDtoForPost() {
    Random random = new Random();
    final int desiredCollectionLength = random.nextInt(98) + 3;
    CollectionContainer domainCollectionToSend = new CollectionContainer();
    domainCollectionToSend.setCollectionDimension(desiredCollectionLength);

    return domainCollectionToSend;
  }

  // endregion
}
