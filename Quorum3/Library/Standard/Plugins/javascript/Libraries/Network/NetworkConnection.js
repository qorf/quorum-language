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
        http.timeout(request.GetReadTimeout());
        http.open(request.GetRequestType(), url, true);
//        http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        
        request.ResetHeaderIterator();
        while (request.HasNextHeader()) {
          var key = request.GetNextHeaderKey();
          var value = request.GetNextHeaderValue(key);
          http.setRequestHeader(key, value);
        }
        
        http.onreadystatechange = function () {
          if (http.readyState == 4 && http.status == 200) {
            ReturnResponse(http);
          }
        }
        http.send(request.GetBody());
    };
    
    function Get(request) {
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
        response.SetEncoding$quorum_text(http.encoding);
        response.SetContentType$quorum_text(http.responseType);
        response.SetResponseMessage$quorum_text("response message");
        response.SetResponseText$quorum_text("http.response");
//        response.SetResponseText$quorum_text(http.responseText);
        connection.SetResponse$quorum_Libraries_Network_NetworkResponseEvent(response);
        
    }
}

