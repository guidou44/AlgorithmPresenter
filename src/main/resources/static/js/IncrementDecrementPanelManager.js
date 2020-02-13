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
