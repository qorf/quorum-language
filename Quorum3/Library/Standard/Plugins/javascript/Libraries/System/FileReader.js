function plugins_quorum_Libraries_System_FileReader_() {
    this.response = null;
    this.path = "";
    this.xmlhttp = null;
    this.OpenForReadNative$quorum_text = function (path) {
        this.xmlhttp = new XMLHttpRequest();
        var url = path;

        this.xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                //this.response = this.xmlhttp.responseText;//JSON.parse(xmlhttp.responseText);
            }
        };
        this.xmlhttp.open("GET", url, false);
        this.xmlhttp.send();
    };
    this.ReadNative = function () {
        this.response = this.xmlhttp.responseText;
        this.xmlhttp = null;
        return this.response;
    };
    
    this.ReadNative$quorum_integer = function (numberOfBytes) {
        
    };
    this.ReadLineNative = function () {
        
    };
    this.GetSystemNewline = function () {
        
    };
    this.ReadLinesNative = function () {
        
    };
    this.IsAtEndOfFile = function () {
        
    };
    this.OpenForRead$quorum_Libraries_System_File = function (file) {
        
    };
    this.Close = function () {
        
    };
}