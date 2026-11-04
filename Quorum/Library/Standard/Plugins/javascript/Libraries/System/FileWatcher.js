// FileWatcher in the browser: not supported. A web page can't watch folders
// on disk. IsSupportedNative returns false, Watch raises an Error (as
// File.js does for what the browser can't do), and there are never changes.
function plugins_quorum_Libraries_System_FileWatcher_() {
    this.settleTime = 200;

    this.IsSupportedNative = function () {
        return false;
    };

    this.WatchNative$quorum_text$quorum_boolean = function (path, recursive) {
        let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("Can not watch files while running Quorum in JavaScript mode.");
        throw exceptionInstance_;
    };

    this.UnwatchNative$quorum_text = function (path) {
    };

    this.StopNative = function () {
    };

    this.ExcludeFolderNameNative$quorum_text = function (name) {
    };

    this.IgnoreChangesToNative$quorum_text$quorum_integer = function (path, milliseconds) {
    };

    this.SetSettleTimeNative$quorum_integer = function (milliseconds) {
        this.settleTime = milliseconds < 0 ? 0 : milliseconds;
    };

    this.GetSettleTimeNative = function () {
        return this.settleTime;
    };

    this.NextChangeNative = function () {
        return false;
    };

    // The browser can't block, so this returns at once.
    this.WaitForChangeNative$quorum_integer = function (milliseconds) {
        return false;
    };

    this.GetChangePathNative = function () {
        return "";
    };

    this.GetChangeKindNative = function () {
        return 1;
    };

    this.GetChangeRootNative = function () {
        return "";
    };
}
