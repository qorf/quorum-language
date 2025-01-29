function plugins_quorum_Libraries_Concurrency_Tasks_() {
   
   const run = (task) => {
      return new Promise((resolve, reject) => {
         task.Run();

         const success = true;
         if (success) resolve(""); // Success outcome
         else reject("");           // Failure outcome
      });
   };

   this.Do$quorum_Libraries_Concurrency_Task = function(task) {
      var status = new quorum_Libraries_Concurrency_TaskStatus_();
      status.SetTask$quorum_Libraries_Concurrency_Task(task);
  
      run(task).then(() => {
         task.Then();  // Manually invoke task's `Then` method

         }).catch(error => console.error("Rejected: " + error));
         return status;
   };

   this.Shutdown = function() {

   }
   this.ShutdownNow = function() {
       
   }
   this.IsFinished = function() {
       return false;
   }
   this.Wait = function() {
       return true;
   }
   this.Wait$quorum_number = function(milliseconds) {
       return true;
   }
   this.SetThreadPoolSize$quorum_integer = function(size) {
       
   }
   this.GetThreadPoolSize = function() {
       return 0;
   }

   
}
