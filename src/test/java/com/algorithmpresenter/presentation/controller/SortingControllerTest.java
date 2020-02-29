package com.algorithmpresenter.presentation.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.algorithmpresenter.presentation.dto.CollectionDto;
import com.algorithmpresenter.presentation.dto.SortedCollectionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
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
  public void givenController_whenAskingForSortingView_thenItReturnsProperView() throws Exception {
    mockMvc
        .perform(get("/SortingAlgorithm"))
        .andExpect(status().isOk())
        .andExpect(view().name("SortingView"));
  }

  @Test
  public void givenWantedLength_whenSendingItAsCollectionDtoRequest_thenItReturnsWithProperLength()
      throws Exception {
    CollectionDto collectionContainerToSend = getRandomCollectionDtoForPost();

    CollectionDto collectionContainerReceived =
        performNewCollectionPostRequest(collectionContainerToSend);

    assertEquals(
        collectionContainerToSend.getCollectionDimension(),
        collectionContainerReceived.getMainCollection().size());
  }

  @Test
  public void givenRandomCollection_whenSendingConsecutiveRequest_thenItReturnsDifferentCollection()
      throws Exception {
    CollectionDto domainCollectionToSend = getRandomCollectionDtoForPost();
    CollectionDto firstDomainCollectionReceived =
        performNewCollectionPostRequest(domainCollectionToSend);
    CollectionDto secondDomainCollectionReceived =
        performNewCollectionPostRequest(domainCollectionToSend);

    assertCollectionsNotEquals(
        firstDomainCollectionReceived.getMainCollection(),
        secondDomainCollectionReceived.getMainCollection());
  }

  @Test
  public void givenBubbleAlgorithm_whenPollingSortCollection_thenItReturnsSortedCollection()
      throws Exception {
    CollectionDto collectionContainerToSend = getRandomCollectionDtoForPost(10);
    performNewCollectionPostRequest(collectionContainerToSend);

    SortedCollectionDto sortedCollectionReceived = null;

    for (int i = 0; i < 70; i++) {
      sortedCollectionReceived = performNextSortingIterationRequest();
    }

    assertTrue(sortedCollectionReceived.isSortingDone());
  }

  // region private methods

  private CollectionDto performNewCollectionPostRequest(CollectionDto domainCollectionToSend)
      throws Exception {
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
    return objectMapper.readValue(responseContent, CollectionDto.class);
  }

  private CollectionDto getRandomCollectionDtoForPost() {
    Random random = new Random();
    final int desiredCollectionLength = random.nextInt(98) + 3;
    CollectionDto domainCollectionToSend = new CollectionDto();
    domainCollectionToSend.setCollectionDimension(desiredCollectionLength);

    return domainCollectionToSend;
  }

  private CollectionDto getRandomCollectionDtoForPost(int collectionLength) {
    CollectionDto domainCollectionToSend = new CollectionDto();
    domainCollectionToSend.setCollectionDimension(collectionLength);
    return domainCollectionToSend;
  }

  private SortedCollectionDto performNextSortingIterationRequest() throws Exception {
    MvcResult result =
        mockMvc
            .perform(get("/SortingAlgorithm/Sort").param("algorithm", "bubble"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.mainCollection").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.sortingDone").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.currentSortingIndex").exists())
            .andReturn();

    String responseContent = result.getResponse().getContentAsString();
    return objectMapper.readValue(responseContent, SortedCollectionDto.class);
  }

  private void assertCollectionsNotEquals(List<Integer> expected, List<Integer> actual) {
    Collections.sort(expected);
    Collections.sort(actual);

    assertNotEquals(expected, actual);
  }

  // endregion
}
