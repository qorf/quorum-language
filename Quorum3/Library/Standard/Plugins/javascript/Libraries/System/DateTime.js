function plugins_quorum_Libraries_System_DateTime_() {
    this.date = new Date();
    
    this.GetYear = function () {
        return date.getFullYear();
    };  

    this.GetMonth = function () {
        return date.getMonth();
    };  
    
    this.GetDayOfMonth = function () {
        return date.getDate();
    };  
    
    this.GetDayOfWeek = function () {
        return date.getDay();
    };  
    
    this.GetHour = function () {
        return date.getHour();
    };  
    
    this.GetMinute = function () {
        return date.getMinute();
    };  
    
    this.GetSecond = function () {
        return date.getSecond();
    };  
    
    this.GetTimeZone = function () {
        return date.getTimezoneOffset();
    };  
    
    this.IsDaylightSavings = function () {
        return date.getTimezoneOffset();
    };  
    
    this.GetEpochTime = function () {
        return Date.now().getUTCMilliseconds();
    };  
    
    this.SetEpochTime$quorum_number = function (value) {
        date.setUTCMilliseconds(value);
    };  
    
    this.SetTimeZone$quorum_integer = function (value) {
        //not supported in JS
    };  
}

