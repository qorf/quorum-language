// ZipWriter in JavaScript mode: not supported (like Decompresser). Open raises
// an Error, so nothing else is reached; Close does nothing.
function plugins_quorum_Libraries_Data_Compression_ZipWriter_() {
    this.Open$quorum_text = function (path) {
        let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("Can not write ZIP archives while running Quorum in JavaScript mode.");
        throw exceptionInstance_;
    };

    this.AddText$quorum_text$quorum_text = function (entryName, content) {
        let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("Can not write ZIP archives while running Quorum in JavaScript mode.");
        throw exceptionInstance_;
    };

    this.BeginImage$quorum_integer$quorum_integer = function (width, height) {
        let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("Can not write ZIP archives while running Quorum in JavaScript mode.");
        throw exceptionInstance_;
    };

    this.SetPixel$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer$quorum_integer = function (x, y, red, green, blue, alpha) {
        let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("Can not write ZIP archives while running Quorum in JavaScript mode.");
        throw exceptionInstance_;
    };

    this.AddImage$quorum_text = function (entryName) {
        let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("Can not write ZIP archives while running Quorum in JavaScript mode.");
        throw exceptionInstance_;
    };

    this.AddCopy$quorum_text$quorum_text = function (entryName, existingEntryName) {
        let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("Can not write ZIP archives while running Quorum in JavaScript mode.");
        throw exceptionInstance_;
    };

    this.AddMd5$quorum_text$quorum_text = function (entryName, content) {
        let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("Can not write ZIP archives while running Quorum in JavaScript mode.");
        throw exceptionInstance_;
    };

    this.Close = function () {
        return;
    };
}
