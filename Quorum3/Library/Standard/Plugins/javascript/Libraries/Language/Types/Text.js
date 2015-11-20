function plugins_quorum_Libraries_Language_Types_Text_(optional) {
    this.text = false;
    
    if(optional === undefined) {
    } else {
        text = optional;
    }
    this.SetValueNative$quorum_text = function (value) {
        text = value;
    };
}