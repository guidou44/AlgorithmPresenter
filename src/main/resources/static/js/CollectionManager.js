let IS_COLLECTION_SORTED = false;

function initializeComponents() {
  getNewCollection();
  getAvailableSortingAlgorithms();
}

function getAvailableSortingAlgorithms() {
  const url = "/SortingAlgorithm/AvailableAlgorithms";

  sendGetRequest(url, function (responseData) {
    setAvailableSortingAlgorithms(responseData);
  });
}

function setAvailableSortingAlgorithms(nameList) {
  console.log(nameList);
  const algorithmSources = document.getElementById("AlgorithmSources");

  for (let i = 0; i < nameList.length; i++) {
    const algorithm = nameList[i];
    const createdOption = document.createElement("option");
    createdOption.textContent = algorithm;
    createdOption.value = algorithm;
    algorithmSources.appendChild(createdOption);
  }
}

function getNewCollection() {
  const collectionLength = getDesiredCollectionLengthAdjusted();
  disableIncrementalButtonIfNeeded(collectionLength);

  const url = "/SortingAlgorithm/SetNewCollection";
  const data = {
    collectionDimension: collectionLength,
  };

  sendPostRequest(url, data, function (responseData) {
    generateArrayFromResponse(responseData['mainCollection']);
  });

  IS_COLLECTION_SORTED = false;
}

function sort() {
  let counter = 0;
  // displayCharacterKoopaMagician(1300);

  sortCollection();
}

function sortCollection() {
  const selectBox = document.getElementById("AlgorithmSources");
  const selectedAlgorithm = selectBox.options[selectBox.selectedIndex].text;

  const url = "/SortingAlgorithm/Sort";
  const data = {
    algorithm : selectedAlgorithm
  };

  sendGetRequestWithQueryParams(url, data, function (responseData, textStatus, xhr) {
    manageSortedCollection(responseData);
  });

  if (!IS_COLLECTION_SORTED) {
    setTimeout(sortCollection, 100)
  }
}

function manageSortedCollection(responseData) {
  IS_COLLECTION_SORTED = responseData['sortingDone'];
  generateArrayFromResponse(responseData['mainCollection'], responseData['currentSortingIndex']);
}

function getDesiredCollectionLengthAdjusted() {
  const inputBox = document.getElementById("CollectionLength");
  let collectionLength = parseInt(inputBox.value, 10);
  if (collectionLength < 3) {
    inputBox.value = 3;
    collectionLength = 3;
  } else if (collectionLength > 100) {
    inputBox.value = 100;
    collectionLength = 100;
  }

  return collectionLength;
}

function generateArrayFromResponse(list, index) {
  const elementWidthPercent = Math.round((100 / list.length) - 0.5);
  const mainUl = document.getElementById('collection_container');
  mainUl.innerHTML = "";
  generateArray(list, elementWidthPercent, mainUl, index);
}

function generateArray(list, elementWidthPercent, mainUl, highlightIndex) {
  for (let j = 0; j < list.length; j++) {
    const childLi = document.createElement("li");
    const childDiv = document.createElement("div");
    childLi.style.width = elementWidthPercent.toString() + "%";
    childDiv.style.width = childLi.style.width;

    for (let i = 0; i < list[j]; i++) {

      const _img = document.createElement("img");
      _img.src = "../images/marioEndTile.png";
      _img.alt = "not found";

      if (highlightIndex != null && j === highlightIndex && !IS_COLLECTION_SORTED) {
        _img.style.filter = "invert(100%)";
      }
      childDiv.appendChild(_img);
    }

    childLi.appendChild(childDiv);
    mainUl.appendChild(childLi);
  }
}

