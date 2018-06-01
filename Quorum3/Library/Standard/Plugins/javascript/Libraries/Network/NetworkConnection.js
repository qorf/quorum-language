function plugins_quorum_Libraries_Network_NetworkConnection_(quorumConnection) {
    var connection = quorumConnection;
    
    this.Request = function () {
        request = connection.GetNetworkRequest();
        if (request.GetRequestType() === "POST") {
            Post(request);
        } else if (request.GetRequestType() === "GET") {
            Get(request);
        } else if (request.GetRequestType() === ("HEAD")) {
            Head(request);
        } else if (request.GetRequestType() === ("PATCH")) {
            Patch(request);
        } else if (request.GetRequestType() === ("DELETE")) {
            Delete(request);
        } else if (request.GetRequestType() === ("PUT")) {
            Put(request);
        }
    };
    
    function Post(request) {
        var http = new XMLHttpRequest();
        var url = request.GetWebAddress();
        http.timeout = request.GetReadTimeout();
        http.open(request.GetRequestType(), url, true);
        http.withCredentials = true;
        request.ResetHeaderIterator();
        while (request.HasNextHeader()) {
          var key = request.GetNextHeaderKey();
          var value = request.GetHeaderValue$quorum_text(key);
          if (key == "Accept-Encoding" || key == "User-Agent" || key == "Connection") {
            console.log("Skipping Header " + key + " : " + value);
          } else {
            console.log("Adding Header: " + key + " : " + value);
            http.setRequestHeader(key, value);
          }
        }
        
        http.onreadystatechange = function () {
//          if (http.readyState === 4 && http.status === 200) {
          if (http.readyState === 4) {
            ReturnResponse(http);
          }
        }
        http.send(request.GetBody());
    };
    
    function Get(request) {
        var http = new XMLHttpRequest();
        var url = request.GetWebAddress();
        http.timeout = request.GetReadTimeout();
        http.open(request.GetRequestType(), url, true);
        request.ResetHeaderIterator();
        while (request.HasNextHeader()) {
          var key = request.GetNextHeaderKey();
          var value = request.GetHeaderValue$quorum_text(key);
          if (key == "Accept-Encoding" || key == "User-Agent" || key == "Connection") {
            console.log("Skipping Header " + key + " : " + value);
          } else {
            console.log("Adding Header: " + key + " : " + value);
            http.setRequestHeader(key, value);
          }
        }
        
        http.onreadystatechange = function () {
          if (http.readyState === 4) {
            ReturnResponse(http);
          }
        }
        http.send(request.GetBody());
    };
    
    function Head(request) {
    };
    
    function Patch(request) {
    };
    
    function Delete(request) {
    };
    
    function Put(request) {
    };
    
    function ReturnResponse(http) {
        response = connection.GetNewResponseEvent();
        response.SetWebAddress$quorum_text(http.responseURL);
        response.SetStatusCode$quorum_integer(http.status);
        response.SetStatusText$quorum_text(http.statusText);
//        response.SetEncoding$quorum_text(http.getResponseHeader('content-encoding'));
//        response.SetContentLength$quorum_integer(http.getResponseHeader('content-length'));
        response.SetContentType$quorum_text(http.getResponseHeader('content-type'));
        response.SetResponseText$quorum_text(http.responseText);
        
        var headers = http.getAllResponseHeaders();
        var headersArray = headers.trim().split(/[\r\n]+/);
        headersArray.forEach(function (headerLine) {
          var line = headerLine.split(': ');
          var key = line.shift();
          var value = line.join(': ');
          response.AddHeader$quorum_text$quorum_text(key, value);
        });

      connection.SetResponse$quorum_Libraries_Network_NetworkResponseEvent(response);
        
    }
}

