function plugins_quorum_Libraries_Concurrency_TaskStatus_() {
   this.IsFinished = function() {
       return true;
   };
   this.Get = function() {
    return true;
   };
   this.Get$quorum_number = function(milliseconds) {
       return true;
   };
}
