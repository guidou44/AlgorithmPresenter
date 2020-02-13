
function displayCharacterKoopaMagician(ms) {
  var divContainingCharacter = document.getElementById("CharacterContainer");
  console.log("setting visibility visible");
  divContainingCharacter.style.visibility = "visible";
  sleep(ms).then(() => {
    divContainingCharacter.style.visibility = "hidden";
    console.log("setting visibility hidden");
  });
}

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}
