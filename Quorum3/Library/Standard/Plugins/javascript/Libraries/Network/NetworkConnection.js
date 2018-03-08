function plugins_quorum_Libraries_Network_NetworkConnection_(quorumConnection) {
    this.me_ = quorumConnection;
    
    this.Request = function (url) {
        request = this.me_.GetNetworkRequest();
        console.log(request.GetRequestType());
        
        response = this.me_.GetNewResponseEvent();
        response.SetResponseText$quorum_text("This is from JavaScript\n" + request.GetRequestType());
        this.me_.SetResponse$quorum_Libraries_Network_NetworkResponseEvent(response);
    };
}

