function plugins_quorum_Libraries_System_BinaryFileReader_(quorumReader) {

    var me_ = quorumReader;
    var buffer;

    this.ReadBytes = function () {
        var array = new quorum_Libraries_Containers_ByteArray_(false);
        var arrayPlugin = array.plugin_;
//        arrayPlugin.byteArray = buffer;
        arrayPlugin.byteArray = new Int8Array(buffer, 0, buffer.length);
        arrayPlugin.maxSize = buffer.length;
        return array;
    };
    
    this.OpenForReadNative$quorum_text = function (value) {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.responseType = "arraybuffer";
        var url = value;
        xmlhttp.onreadystatechange = function() {
            var arrayBuffer = xmlhttp.response; // Note: not oReq.responseText
            if (arrayBuffer) {
                this.buffer = arrayBuffer;
            }
        };
        xmlhttp.open("GET", url, false);
        xmlhttp.send();
    };
    
    this.OpenForRead$quorum_Libraries_System_File$quorum_Libraries_Interface_Events_FileLoadListener = function (file, listener)
    {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.responseType = "arraybuffer";
        var url = file.GetPath();
        
        xmlhttp.onload = function() {
            var arrayBuffer = xmlhttp.response; // Note: not oReq.responseText
            if (arrayBuffer) 
            {
                buffer = arrayBuffer;
            }
            
            var event = new quorum_Libraries_Interface_Events_FileLoadEvent_();
            event.Set$quorum_Libraries_System_File$quorum_Libraries_System_BinaryFileReader(file, me_);
            listener.OnLoad$quorum_Libraries_Interface_Events_FileLoadEvent(event);
        };
        xmlhttp.open("GET", url, true);
        xmlhttp.send();
    }
}