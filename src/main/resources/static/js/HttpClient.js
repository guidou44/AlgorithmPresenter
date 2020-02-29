

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

function sendGetRequest(url, callback) {
  $.ajax({
    url: url,
    type: "GET",
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: callback
  });
}

function sendGetRequestWithQueryParams(url, data, callback) {
  $.ajax({
    url: url,
    type: "GET",
    data: data,
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: callback,
  });
}
