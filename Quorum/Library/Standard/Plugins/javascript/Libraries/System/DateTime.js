function plugins_quorum_Libraries_System_DateTime_() {
    
    this.date = new Date();
    this.now = true

    this.GetYear = function () {
        if(this.now) {
            this.date = new Date();
        }
        return date.getFullYear();
    };  

    this.GetMonth = function () {
        if(this.now) {
            this.date = new Date();
        }
        return date.getMonth() + 1;
    };  
    
    this.GetDayOfMonth = function () {
        if(this.now) {
            this.date = new Date();
        }
        return date.getDate();
    };  
    
    this.GetDayOfWeek = function () {
        // this is keep it to the ISO 8601 standard
        // Days go from 1-7 where 1 is Monday and 7 is Sunday
        if(this.now) {
            this.date = new Date();
        }
        let day = date.getDay();
        if(day == 0) {
            day = 7;
        }
        return day;
    };  
    
    this.GetHour = function () {
        if(this.now) {
            this.date = new Date();
        }
        return date.getHour();
    };  
    
    this.GetMinute = function () {
        if(this.now) {
            this.date = new Date();
        }
        return date.getMinute();
    };  
    
    this.GetSecond = function () {
        if(this.now) {
            this.date = new Date();
        }
        return date.getSecond();
    };  
    
    this.GetTimeZone = function () {
        if(this.now) {
            this.date = new Date();
        }
        return date.getTimezoneOffset();
    };  
    
    this.IsDaylightSavings = function () {
        if(this.now) {
            this.date = new Date();
        }
        // Jan and July are never in the same timezone if DST is observed
        let jan = new Date(d.getFullYear(), 0, 1).getTimezoneOffset();
        let jul = new Date(d.getFullYear(), 6, 1).getTimezoneOffset();
        return Math.max(jan, jul) !== this.date.getTimezoneOffset();   
    };  
    
    this.GetEpochTime = function () {
        if(this.now) {
            this.date = new Date();
        }
        return this.date.getTime();
    };  
    
    this.SetEpochTime$quorum_number = function (value) {
        this.now = false;
        this.date.setTime(value);
    };  
    
    this.SetTimeZone$quorum_integer = function (value) {
        //not supported in JS
    };
    
    this.Now = function () {
        return Date.now();
    };
    
    this.GetTimestamp = function () {
        return this.date.toISOString();
    };

    this.ToText = function () {
        return this.date.toString();
    };

    this.DateToText = function () {
        return this.date.toLocaleDateString();
    };

    this.TimeToText = function () {
        return this.date.toTimeString();
    };

    this.GetMonthName = function () {
        return this.date.toLocaleString('default', { month : 'long' });
    };

    this.GetShortMonthName = function () {
        return this.date.toLocaleString('default', { month : 'short' });
    };

    this.GetDayOfWeekName = function () {
        return this.date.toLocaleString('default', { weekday : 'long'});
    }

    this.GetShortDayOfWeekName = function () {
        return this.date.toLocaleString('default', { weekday : 'short'});
    }
    // Parsing
    // parse is implementation specific so it might not work
    // as expected or even on different browsers 
    this.ParseTimestamp$quorum_text = function (value) {
        this.now = false;
        value = value.trim();
        value = value.replace(' ', 'T');
        this.date = new Date(Date.parse(value));
        if(isNaN(this.date))
        {
            let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("Could not parse '"+value+"' as a timestamp.");
            throw exceptionInstance_;
        }
       	// check further
        let originalDate = value.substring(0,10);
        let arr = originalDate.split('-');
        if(arr.length != 3)
        {
        	let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("Could not parse '"+value+"' as a timestamp.");
            throw exceptionInstance_;
        }
        let originalTime = value.substring(11,20);
        arr = originalTime.split(':');
        if(arr.length != 3)
        {
        	let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("Could not parse '"+value+"' as a timestamp.");
            throw exceptionInstance_;
        }
    };

    this.ParseDate$quorum_text = function (value) {
        this.now = false;
        value = value.trim();
        this.date = new Date(Date.parse(value));

        if(isNaN(this.date))
        {
            let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("Could not parse '"+value+"' as a date.");
            throw exceptionInstance_;
        }
        // check further
        let arr = value.split('-');
        if(arr.length != 3 && value.length != 10)
        {
        	let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("Could not parse '"+value+"' as a date.");
            throw exceptionInstance_;
        }
    };

    this.ParseAmericanDate$quorum_text = function (value) {
        this.now = false;
        value = value.trim();
        this.date = new Date(Date.parse(value));

        if(isNaN(this.date))
        {
            let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("Could not parse '"+value+"' as a date.");
            throw exceptionInstance_;
        }
        // check further
        let arr = value.split('/');
        if(arr.length != 3 && value.length > 10)
        {
            let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
            exceptionInstance_.SetErrorMessage$quorum_text("Could not parse '"+value+"' as a date.");
            throw exceptionInstance_;
        }
    };

    this.ParseTime$quorum_text = function (value, keepDate) {
        this.now = false;
        // Not supported
        let exceptionInstance_ = new quorum_Libraries_Language_Errors_Error_();
        exceptionInstance_.SetErrorMessage$quorum_text("Quorum running in Javascript does not support parsing a time alone.");
        throw exceptionInstance_;
    };

    this.IsBefore$quorum_Libraries_System_DateTime_ = function (value) {
        return date < value.plugin_.date;
    };

    this.IsAfter$quorum_Libraries_System_DateTime_ = function () {
        return date > value.plugin_.date;
    };

    // Math functions

    // JS Date doesn't have math functions but the Set functions account
    // for overflow and negative numbers so they are used instead

    this.AddYears$quorum_integer = function(value) {
        this.now = false;
        this.date.setFullYear(date.getFullYear() + value);
    };

    this.AddMonths$quorum_integer = function(value) {
        this.now = false;
        this.date.setMonth(this.date.getMonth() + value);
    };

    this.AddWeeks$quorum_integer = function(value) {
        this.now = false;
        this.date.setDay(this.date.getDay() + (value * 7));
    };

    this.AddDays$quorum_integer = function(value) {
        this.now = false;
        this.date.setDay(this.date.getDay() + value);
    };

    this.AddHours$quorum_integer = function(value) {
        this.now = false;
        this.date.setHours(this.date.getHours() + value);
    };

    this.AddMinutes$quorum_integer = function(value) {
        this.now = false;
        this.date.setMinutes(this.date.getMinutes() + value);
    };

    this.AddSeconds$quorum_integer = function(value) {
        this.now = false;
        this.date.setSeconds(this.date.getSeconds() + value, 0);
    };

    this.SubtractYears$quorum_integer = function(value) {
        this.now = false;
        this.date.setFullYear(date.getFullYear() - value);
    };

    this.SubtractMonths$quorum_integer = function(value) {
        this.now = false;
        this.date.setMonth(this.date.getMonth() - value);
    };

    this.SubtractWeeks$quorum_integer = function(value) {
        this.now = false;
        this.date.setDay(this.date.getDay() - (value * 7));
    };

    this.SubtractDays$quorum_integer = function(value) {
        this.now = false;
        this.date.setDay(this.date.getDay() - value);
    };

    this.SubtractHours$quorum_integer = function(value) {
        this.now = false;
        this.date.setHours(this.date.getHours() - value);
    };

    this.SubtractMinutes$quorum_integer = function(value) {
        this.now = false;
        this.date.setMinutes(this.date.getMinutes() - value);
    };

    this.SubtractSeconds$quorum_integer = function(value) {
        this.now = false;
        this.date.setSeconds(this.date.getSeconds() - value, 0);
    };

}

