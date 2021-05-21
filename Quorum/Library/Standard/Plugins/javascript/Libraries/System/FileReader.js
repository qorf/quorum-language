function plugins_quorum_Libraries_System_FileReader_() {
    var response = null;
    var readPosition = 0;
    var eof = false;
    this.path = "";
    this.xmlhttp = null;
    this.OpenForReadNative$quorum_text = function (path) {
        this.xmlhttp = new XMLHttpRequest();
        var url = path;

        this.xmlhttp.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                response = this.responseText;
                if (response.length == 0)
                    eof = true;
            }
        };
        this.xmlhttp.open("GET", url, false);
        this.xmlhttp.send();
    };
    this.ReadNative = function () {
        response = this.xmlhttp.responseText;
        this.xmlhttp = null;
        eof = true;
        return response;
    };
    
    this.ReadNative$quorum_integer = function (numberOfBytes) {
        
    };
    this.ReadLineNative = function () {
        if (this.IsAtEndOfFile())
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("I could not read from the file because the end of file was already reached.");
            throw exceptionInstance_;
        }
        if (response === null || response === undefined)
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("There were no file contents available for reading.");
            throw exceptionInstance_;
        }
        
        if (readPosition >= response.length)
        {
            eof = true;
            return "";
        }
        
        var newPosition = response.indexOf('\n', readPosition);
        if (newPosition < 0)
            newPosition = response.length;
        var line = response.substring(readPosition, newPosition);
        readPosition = newPosition + 1;
        if (line === "\n")
            return "";
        return line;
    };
    this.GetSystemNewline = function () {
        return '\n';
    };
    this.ReadLinesNative = function () {
        if (this.IsAtEndOfFile())
        {
            var exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("I could not read from the file because the end of file was already reached.");
            throw exceptionInstance_;
        }
        
        var lines = response.substring(readPosition, response.length);
        readPosition = response.length;
        eof = true;
        return lines;
    };
    this.IsAtEndOfFile = function () {
        return eof;
    };
    this.OpenForReadNative$quorum_Libraries_System_File = function (file) {
    	this.xmlhttp = new XMLHttpRequest();
        var url = file.GetAbsolutePath();

        this.xmlhttp.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                response = this.responseText;
                if (response.length == 0)
                    eof = true;
            }
        };
        this.xmlhttp.open("GET", url, false);
        this.xmlhttp.send();
    };
    this.Close = function () {
        response = null;
        readPosition = 0;
    };
}