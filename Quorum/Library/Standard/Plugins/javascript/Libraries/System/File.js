function plugins_quorum_Libraries_System_File_() {
    // default to the root of the website
    this.defaultWorkingDirectory = window.location.protocol + "//" + window.location.hostname;
    this.path = "";
    //
    this.GetLastModifiedNative = function () {
        let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("Can not access last modified date while running Quorum in JavaScript mode.");
        throw exceptionInstance_;
    };

    this.GetDirectoryListingNative = function () {
        let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("Can not get directory listing while running Quorum in JavaScript mode.");
        throw exceptionInstance_;
    };

    this.GetParentDirectoryNative = function () 
    {
        var path = this.GetAbsolutePathNative();
        return path.substring(0, path.lastIndexOf('/'));
    };

    //
    this.GetSystemNewline = function () {
        return "\n";
    };

    this.GetPathNative = function () {
        return this.path;
    };

    this.SetPathNative$quorum_text = function (path) {
        this.path = path;
    };

    this.GetWorkingDirectoryNative = function () {
        return this.defaultWorkingDirectory;
    };

    this.SetWorkingDirectoryNative$quorum_text = function (path) {
        this.defaultWorkingDirectory = path;
    };

    this.GetAbsolutePathNative = function () {
        return new URL(this.path, this.defaultWorkingDirectory + "/").href
    };

    this.GetWorkingDirectoryFromPath$quorum_text = function (path) {

    };
    
    this.GetPathFromPath$quorum_text = function (path) {

    };

    //AJAX request with timeout
    this.Exists = function () {
        var xmlhttp = new XMLHttpRequest();
        var url = this.GetAbsolutePathNative();
        var isFound = false;
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                isFound = true;
            }
        };
        xmlhttp.open("HEAD", url, false);
        xmlhttp.send();
        return isFound;
    };

    //
    this.IsFile = function () {
        return true;
    };

    //
    this.IsDirectory = function () {
        return false;
    };

    this.IsHidden = function () {
        return false;
    };

    this.GetFileName = function () {
        return this.GetAbsolutePathNative().split("/").pop();
    };

    //throw exception
    this.GetFreeDiskSpace = function () {
        let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("Can not get disk space when running Quorum on the web.");
        throw exceptionInstance_;
    };

    //throw exception
    this.GetTotalDiskSpace = function () {
        let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("Can not get disk space when running Quorum on the web.");
        throw exceptionInstance_;
    };

    this.GetFileSize = function () {

    };

    //throw exception
    this.Delete = function () {
        let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("Can not delete files while running Quorum in JavaScript mode.");
        throw exceptionInstance_;
    };

    //throw exception
    this.CreateDirectory = function () {
        let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("Can not create directory while running Quorum in JavaScript mode.");
        throw exceptionInstance_;
    };

    //throw exception
    this.CreateDirectories = function () {
        let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("Can not create directories while running Quorum in JavaScript mode.");
        throw exceptionInstance_;
    };

    //throw exception
    this.Move$quorum_text = function (newPath) {
        let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("Can not move files while running Quorum in JavaScript mode.");
        throw exceptionInstance_;
    };

    //throw exception
    this.Copy$quorum_Libraries_System_File = function (copy) {
        let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("Can not copy files while running Quorum in JavaScript mode.");
        throw exceptionInstance_;
    };
    
    this.SetExecutable$quorum_text = function(exec) {
        let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("Can not set files to be executable while running Quorum in JavaScript mode.");
        throw exceptionInstance_;
    };

    /*
    Plugin only function. This converts the absolute path of this File to a URL that's relative to the directory
    that the webpage is running from.
    */
    this.ConvertAbsoluteToRelativeURL = function()
    {
        var source = document.URL.substring(0,document.URL.lastIndexOf('/'));
        var destination = this.GetAbsolutePathNative();

        if (destination.startsWith("http://"))
            destination = destination.substring(7);
        else if (destination.startsWith("https://"))
            destination = destination.substring(8);

        if (source.startsWith("http://"))
            source = source.substring(7);
        else if (source.startsWith("https://"))
            source = source.substring(8);

        destArray = destination.split("/");
        sourceArray = source.split("/");

        var result = "";

        // First, remove all common elements between the two paths.
        while (sourceArray.length > 0 && destArray.length > 0)
        {
            if (sourceArray[0] === destArray[0])
            {
                sourceArray.shift();
                destArray.shift();
            }
            else
            {
                break;
            }
        }

        /*
        Each remaining value in the source array is a directory we need to come up from.
        For each directory remaining in the source, we append "../" to the result.
        This may occur zero times (if the destination is within the source directory).
        */
        for (var i = 0; i < sourceArray.length; i++)
        {
            result += "../";
        }

        /*
        Now, append the remaining destination values to the result. Each value must be separated by a slash, with no
        slash following the final element.
        This can occur zero times (if the destination is a directory below the source directory, or the same directory
        as the source directory).
        */
        for (var i = 0; i < destArray.length; i++)
        {
            result += destArray[i];
            if (i !== (destArray.length - 1))
                result += "/";
        }

        return result;
    }
}