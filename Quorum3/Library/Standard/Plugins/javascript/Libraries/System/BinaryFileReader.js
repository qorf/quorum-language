function plugins_quorum_Libraries_System_BinaryFileReader_() {
    var buffer = null;
    this.ReadBytes = function () {
        var array = new quorum_Libraries_Containers_ByteArray_(false);
        var arrayPlugin = array.plugin_;
        arrayPlugin.byteArray = buffer;
        return array;
    };
    
    this.OpenForReadNative$quorum_text = function (value) {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.responseType = "arraybuffer";
        var url = value;
        xmlhttp.onreadystatechange = function() {
            var arrayBuffer = xmlhttp.response; // Note: not oReq.responseText
            if (arrayBuffer) {
                this.buffer = new Int32Array(arrayBuffer);
            }
        };
        xmlhttp.open("GET", url, false);
        xmlhttp.send();
    };
}