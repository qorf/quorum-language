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
        ReturnResponse("Post Response: " + request.GetRequestType());
        var http = new XMLHttpRequest();
        
        request.ResetHeaderIterator();
        while (request.HasNextHeader()) {
            var key = request.GetNextHeaderKey();
            var value = request.GetNextHeaderValue(key);
            http.setRequestHeader(key, value);
        }
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
    
    function ReturnResponse(responseText) {
        response = connection.GetNewResponseEvent();
        response.SetWebAddress$quorum_text("url");
        response.SetStatusCode$quorum_integer(200);
        response.SetStatusText$quorum_text("OK");
        response.SetEncoding$quorum_text("encoding type");
        response.SetContentType$quorum_text("content type");
        response.SetResponseMessage$quorum_text("response message");
        response.SetResponseText$quorum_text(responseText);
        connection.SetResponse$quorum_Libraries_Network_NetworkResponseEvent(response);
        
    }
}

