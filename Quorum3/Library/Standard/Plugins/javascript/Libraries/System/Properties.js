function plugins_quorum_Libraries_System_Properties_() {
    this.GetRunLocation = function () {
        return null;
    };
    
    this.SetPropertyNative$quorum_text$quorum_text = function (key, value) {
    };  
    
    this.GetPropertyNative$quorum_text = function (key) {
        if(key == "os.name") {
            return "Web Browser";
        }
    };  
    
    this.HasPropertyNative$quorum_text = function (key) {
        if(key == "os.name") {
            return true;
        }
    };
    
    this.GetEnvironmentVariableNative$quorum_text = function (key) {
        return "";
    };  
    
    this.GetEnvironmentVariableNative$quorum_text = function (key) {
        return false;
    };
}