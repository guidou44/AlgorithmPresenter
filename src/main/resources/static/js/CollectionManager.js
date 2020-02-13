

function getNewCollection() {
  var collectionLength = getDesiredCollectionLengthAdjusted();
  disableIncrementalButtonIfNeeded(collectionLength);

  const url = "/SortingAlgorithm/CollectionParameters";
  const data = {
    collectionDimension: collectionLength,
    sortingMethod: "TEST"
  };

  sendPostRequest(url, data,   function (responseData) {
    generateArrayFromResponse(responseData);
  })
}

function getDesiredCollectionLengthAdjusted() {
  var inputBox = document.getElementById("CollectionLength");
  var collectionLength = parseInt(inputBox.value, 10);
  if (collectionLength < 3) {
    inputBox.value = 3;
    collectionLength = 3;
  } else if (collectionLength > 100) {
    inputBox.value = 100;
    collectionLength = 100;
  }

  return collectionLength;
}

function disableIncrementalButtonIfNeeded(collectionLength) {
  var incrementButton = document.getElementById("incrementButton");
  var decrementButton = document.getElementById("decrementButton");

  if (collectionLength === 100) {
    decrementButton.disabled = false;
    incrementButton.disabled = true;
  } else if (collectionLength === 3) {
    decrementButton.disabled = true;
    incrementButton.disabled = false;
  } else {
    decrementButton.disabled = false;
    incrementButton.disabled = false;
  }
}

function generateArrayFromResponse(list) {
  console.log(list);
  var elementWidthPercent = Math.round((100 / list.length) - 0.5);
  var mainUl = document.getElementById('collection_container');
  mainUl.innerHTML = "";

  generateArray(list, elementWidthPercent, mainUl);
}

function generateArray(list, elementWidthPercent, mainUl) {
  for (var j = 0; j < list.length; j++) {
    var childLi = document.createElement("li");
    var childDiv = document.createElement("div");
    childLi.style.width = elementWidthPercent.toString() + "%";
    childDiv.style.width = childLi.style.width;

    for (var i = 0; i < list[j]; i++) {
      var _img = document.createElement("img");
      _img.src = "../images/marioEndTile.png";
      _img.alt = "not found";
      childDiv.appendChild(_img);
    }

    childLi.appendChild(childDiv);
    mainUl.appendChild(childLi);
  }
}

