function plugins_quorum_Libraries_Language_Support_TextBuilder_() {
    this.text = "";
    // The buffer size, as Java's StringBuilder capacity: SetMaxSize raises
    // it (never lowers it), and it always covers the text appended.
    this.maxSize = 16;

    this.Append$quorum_text = function (value) {
        this.text = this.text + value;
    };

    this.GetMaxSize = function () {
        return Math.max(this.maxSize, this.text.length);
    };

    this.SetMaxSize$quorum_integer = function (value) {
        if (value > this.maxSize) {
            this.maxSize = value;
        }
    };

    // The number of characters appended so far, as Java's GetSize.
    this.GetSize = function () {
        return this.text.length;
    };

    this.ToText = function () {
        return this.text;
    };
}