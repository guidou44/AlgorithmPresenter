function getNewCollection() {

  var collectionLength = parseInt(
      document.getElementById("collectionLength").value, 10);
  if (collectionLength >= 3) {
    console.log(collectionLength);
    const Url = "/SortingAlgorithm/CollectionParameters";
    const data = {
      collectionDimension: collectionLength,
      sortingMethod: "TEST"
    };

    $.ajax({
      url: Url,
      type: "POST",
      data: JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: function (responseData) {
        generateArrayFromResponse(responseData);
      }
    });
  }

  console.log(document.getElementById("mainContainer").offsetWidth);
}

function generateFixedArray() {
  var list = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15];
  var elementWidthPercent = Math.round((100 / list.length) - 0.5);
  var mainUl = document.getElementById("collection_container");

  generateArray(list, elementWidthPercent, mainUl);
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

function decrementCollectionSize() {
  var sizeInput = document.getElementById("collectionLength");
  if (sizeInput.value > 3) {
    sizeInput.value = parseInt(sizeInput.value, 10) - 1;
    getNewCollection();
  }
}

function incrementCollectionSize() {
  var sizeInput = document.getElementById("collectionLength");
  sizeInput.value = parseInt(sizeInput.value, 10) + 1;
  getNewCollection();
}
