function plugins_quorum_Libraries_Compute_Statistics_Loaders_WebDataFrameLoader_(webDataFrameLoader) {
    this.me_ = webDataFrameLoader;
    this.NativeLoad$quorum_Libraries_Compute_Statistics_DataFrame$quorum_Libraries_System_File = function(frame, file) {
        var location = this.me_.GetLocation()
        var result = document.getElementById(location).value;
        frame.LoadFromCommaSeparatedValue$quorum_text(result);
    };
}
