function plugins_quorum_Libraries_System_FileWriter_() {
    var eof = false;
    this.path = null;
    this.writing = null;
    this.OpenForWriteNative$quorum_text = function (path) {
        this.path = path;
        this.writing = "";
    };
    
    this.OpenForWriteAppendNative$quorum_text = function (path) {
        this.path = path;
        this.writing = "";
    };
    
    this.WriteNative$quorum_text = function (textToWrite) {
        this.writing = textToWrite;
    };
    
    this.WriteLineNative$quorum_text = function (textToWrite) {
        this.writing += textToWrite + this.GetSystemNewline();
    };
    
    this.GetSystemNewline = function () {
        return '\n';
    };
    
    this.PushToDisk = function () {
        
    };
   
    this.Close = function () {
        if (this.writing == null) {
            return;
        }
        var link = document.createElement('a');
        
        var extension = null;
        //generate a name from the path
        if(this.path != null) {
            link.download = this.path;
            extension = this.path.split('.').pop()
        } else {
            link.download = "Download.txt";
            extension = "txt"
        }
        var blob = new Blob([this.writing], { type: this.GetTypeFromExtension(extension) });
        var url = URL.createObjectURL(blob);
        link.href = url;
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
    };
    
    this.GetTypeFromExtension = function(extension) {
        if(extension == null) {
            return 'text/plain';
        } else if (extension === "json") {
            return 'application/json';
        } else if (extension === "csv") {
            return 'text/csv';
        }else if (extension === "txt") {
            return 'text/plain';
        } else if(extension === "svg") {
            return 'image/svg+xml';
        } else {
            return 'text/plain';
        }
    }
}
