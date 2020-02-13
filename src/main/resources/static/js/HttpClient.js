

function sendPostRequest(url, data, callback) {
  $.ajax({
    url: url,
    type: "POST",
    data: JSON.stringify(data),
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: callback
  });
}
