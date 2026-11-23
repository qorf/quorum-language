function plugins_quorum_Libraries_Language_Support_TextBuilder_() {
    this.text = "";

    this.Append$quorum_text = function (value) {
        this.text = this.text + value;
    };

    this.GetMaxSize = function () {
        return this.text.length;
    };

    this.SetMaxSize$quorum_integer = function (value) {
    };

    // The number of characters appended so far, as Java's GetSize.
    this.GetSize = function () {
        return this.text.length;
    };

    this.ToText = function () {
        return this.text;
    };
}