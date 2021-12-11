function plugins_quorum_Libraries_System_File_() {
    this.defaultWorkingDirectory = window.location.hostname;
    this.path = "";
    //
    this.GetLastModifiedNative = function () {

    };

    this.GetDirectoryListingNative = function () {

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
        //check if the path contains any pre-prend
        var test = this.path;
        var levelUp = 0;
        while(test.startsWith("../")) {
            test = test.substring(3, test.length);
            levelUp++;
        }
        
        if(test.startsWith("./")) {
            test = test.substring(2, test.length);
        }
        
        //now if the level ups are greater than 0, change the working directory
        var newWorking = this.defaultWorkingDirectory;
        
        if (!newWorking.endsWith('/'))
            newWorking = newWorking + '/';
        
        return newWorking + test;
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
        xmlhttp.open("GET", url, false); //http://localhost:8383/HTML5Application/index.html/Library/Tests/SeparatedValue/Resources/Simple.csv
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
        
    };

    this.GetFileExtension = function () {

    };

    //throw exception
    this.GetFreeDiskSpace = function () {

    };

    //throw exception
    this.GetTotalDiskSpace = function () {

    };

    this.GetFileSize = function () {

    };

    //throw exception
    this.Delete = function () {

    };

    //throw exception
    this.CreateDirectory = function () {

    };

    //throw exception
    this.CreateDirectories = function () {

    };

    //throw exception
    this.Move$quorum_text = function (newPath) {

    };

    //throw exception
    this.Copy$quorum_Libraries_System_File = function (copy) {

    };
    
    this.SetExecutable$quorum_text = function(exec) {
        
    };
}