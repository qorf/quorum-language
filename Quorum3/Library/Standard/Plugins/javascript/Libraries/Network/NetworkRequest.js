function plugins_quorum_Libraries_Network_NetworkRequest_(quorumRequest) {
    this.me_ = quorumRequest;
    
    this.Get$quorum_text = function (url) {
        console.log(url);
        this.me_.SetResponse$quorum_text("Response Get(url):\n" + url);
    };
    this.Get$quorum_text$quorum_text = function (url, params) {
        console.log(url);
        console.log(params);
        this.me_.SetResponse$quorum_text("Response Get(url, params):\n" + url + "\n" + params);
    };    
}
