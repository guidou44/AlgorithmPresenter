//Mange increment and decrement button panel

function decrementCollectionSize() {
  var sizeInput = document.getElementById("CollectionLength");
  var incrementButton = document.getElementById("incrementButton");
  sizeInput.value = parseInt(sizeInput.value, 10) - 1;
  if (sizeInput.value < 3)
  {
    sizeInput.value = 3;
    var decrementButton = document.getElementById("decrementButton");
    decrementButton.disabled = true;
  }

  if (incrementButton.disabled)
    incrementButton.disabled = false;
  getNewCollection();

}

function incrementCollectionSize() {
  var sizeInput = document.getElementById("CollectionLength");
  var decrementButton = document.getElementById("decrementButton");
  sizeInput.value = parseInt(sizeInput.value, 10) + 1;
  if (sizeInput.value > 100) {
    sizeInput.value = 100;
    var incrementButton = document.getElementById("incrementButton");
    incrementButton.disabled = true;
  }

  if (decrementButton.disabled)
    decrementButton.disabled = false;
  getNewCollection();
}

function disableIncrementalButtonIfNeeded(collectionLength) {
  const incrementButton = document.getElementById("incrementButton");
  const decrementButton = document.getElementById("decrementButton");

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
