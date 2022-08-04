function plugins_quorum_Libraries_Compute_Statistics_Loaders_WebDataFrameLoader_(webDataFrameLoader) {
    this.me_ = webDataFrameLoader;
    this.NativeLoad$quorum_Libraries_Compute_Statistics_DataFrame$quorum_Libraries_System_File = function(frame, file) {
        var location = this.me_.GetLocation()
        var attribute = this.me_.GetAttribute()
        var element = document.getElementById(location);
        var result;
        if(attribute != null) {
            result = element.getAttribute(attribute);
        } else {
            result = element.value;
        }
        frame.LoadFromCommaSeparatedValue$quorum_text(result);
    };
    
    this.CanLoad = function() {
        var location = this.me_.GetLocation()
        var attribute = this.me_.GetAttribute()
        var element = document.getElementById(location);
        if(element == null) { //only ensure the element is there.
            return false;
        } else {
            return true;
        }
    }
}
