function plugins_quorum_Libraries_System_File_() {
    this.defaultWorkingDirectory = window.location.href;
    this.defaultWorkingDirectory = this.defaultWorkingDirectory.substring(0, this.defaultWorkingDirectory.lastIndexOf( "/" ) + 1);
    this.path = "";
    //
    this.GetLastModifiedNative = function () {

    }

    this.GetDirectoryListingNative = function () {

    }

    this.GetParentDirectoryNative = function () {

    }

    //
    this.GetSystemNewline = function () {
        return "\n";
    }

    this.GetPathNative = function () {
        return this.path;
    }

    this.SetPathNative$quorum_text = function (path) {
        this.path = path;
    }

    this.GetWorkingDirectoryNative = function () {
        return this.defaultWorkingDirectory;
    }

    this.SetWorkingDirectoryNative = function (path) {
        this.defaultWorkingDirectory = path;
    }

    this.GetAbsolutePathNative = function () {
        return this.defaultWorkingDirectory + path;
    }

    this.GetWorkingDirectoryFromPath = function (path) {

    }
    
    this.GetPathFromPath = function (path) {

    }

    //AJAX request with timeout
    this.Exists = function () {

    }

    //
    this.IsFile = function () {

    }

    //
    this.IsDirectory = function () {

    }

    this.IsHidden = function () {
        return false;
    }

    this.GetFileName = function () {

    }

    this.GetFileExtension = function () {

    }

    //throw exception
    this.GetFreeDiskSpace = function () {

    }

    //throw exception
    this.GetTotalDiskSpace = function () {

    }

    this.GetFileSize = function () {

    }

    //throw exception
    this.Delete = function () {

    }

    //throw exception
    this.CreateDirectory = function () {

    }

    //throw exception
    this.CreateDirectories = function () {

    }

    //throw exception
    this.Move = function (newPath) {

    }

    //throw exception
    this.Copy = function (copy) {

    }
}