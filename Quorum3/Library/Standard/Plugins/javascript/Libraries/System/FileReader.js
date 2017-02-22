function plugins_quorum_Libraries_System_FileReader_() {
    var response = null;
    var readPosition = 0;
    this.path = "";
    this.xmlhttp = null;
    this.OpenForReadNative$quorum_text = function (path) {
        this.xmlhttp = new XMLHttpRequest();
        var url = path;

        this.xmlhttp.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                response = this.responseText;
            }
        };
        this.xmlhttp.open("GET", url, false);
        this.xmlhttp.send();
    };
    this.ReadNative = function () {
        response = this.xmlhttp.responseText;
        this.xmlhttp = null;
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
        
        var newPosition = response.indexOf('\n', readPosition + 1);
        if (newPosition < 0)
            newPosition = response.length;
        var line = response.substring(readPosition, newPosition);
        readPosition = newPosition;
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
        return lines;
    };
    this.IsAtEndOfFile = function () {
        return response === null || response === undefined || readPosition >= response.length;
    };
    this.OpenForRead$quorum_Libraries_System_File = function (file) {
        
    };
    this.Close = function () {
        response = null;
        readPosition = 0;
    };
}