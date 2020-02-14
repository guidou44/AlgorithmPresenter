package com.algorithmpresenter.application.controllertests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.algorithmpresenter.application.controllers.SortingController;
import com.algorithmpresenter.application.models.dtos.CollectionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@ComponentScan({"com.algorithmpresenter.application"})
public class SortingControllerTests {

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
    final int desiredCollectionLength = 10;
    CollectionDto collectionDto = new CollectionDto();
    collectionDto.setCollectionDimension(desiredCollectionLength);

    MvcResult result = getMvcResultFromNewCollectionPost(collectionDto);

    String responseContent = result.getResponse().getContentAsString();
    assertEquals(desiredCollectionLength,
        objectMapper.readValue(responseContent, CollectionDto.class).getMainCollection().size());
  }

  // region private methods

  private MvcResult getMvcResultFromNewCollectionPost(CollectionDto collectionDto)
      throws Exception {
    return mockMvc
        .perform(
            MockMvcRequestBuilders.post("/SortingAlgorithm/SetNewCollection")
                .content(objectMapper.writeValueAsString(collectionDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.mainCollection").exists())
        .andReturn();
  }

  // endregion
}
