function plugins_quorum_Libraries_Interface_Accessibility_WebAccessibility_(accessibility) {
    var me_ = accessibility;
    /*
     * These variables are old and may not be relevant. I'm leaving them in for now, 
     * but presume they will be modified or go away as we develop the library.
     */
    var type = "";      //specifies the input type of the element
    var elementType = "DIV";   //specifies the type of element DEFAULT is DIV right now for testing
    var elementList = [];   // array using the item's hashCode value as an index and the item as the value 
    var controlElementList = new Map();
    // the controls whose plain tooltip is showing, by the control's element id;
    // while it shows, the control's label leaves out its description, which the
    // tooltip's element gives through aria-describedby
    var plainTooltipOwners = new Set();
    // the lists combo boxes show their choices in: the list's element id
    // maps to its combo box, so the list and its rows are a listbox of options
    var comboBoxLists = new Map();
    // the elements given an aria-activedescendant for an item's active part,
    // by element id; each maps to the part's element id when it was added to
    // the element's aria-owns so the part counts as its own, or null
    var activeDescendantOwners = new Map();
    var currentFocus = null;
    let root = null;
    let controlElementRoot = null;
    let focusButton = null;
    let blurDelayedCall = null;
    let removedFocusedElement = false;
    let thisGame = null;
    let textInputElements = null;
    let selectionListener = null;


    const GetWebOperatingSystem = function() {
        let userAgent = window.navigator.userAgent;
	    let platform = window.navigator.platform;
        let os = 'Unknown';
        if (/android/i.test(userAgent)) {
            return "Android";
        } else if (/iPad|iPhone|iPod/.test(userAgent) && !window.MSStream) {
            return "iOS";
        } else if ((('ontouchstart' in window) ||
                (navigator.maxTouchPoints > 0) ||
                (navigator.msMaxTouchPoints > 0))
                && (userAgent.indexOf("Linux") !== -1 || userAgent.indexOf("Mac") !== -1) ) {
                    return "Mobile";
        } else if (userAgent.indexOf('Win') != -1) {
            return 'Windows';
        } else if (userAgent.indexOf('Mac') != -1) {
            return 'Mac';
        } else if (userAgent.indexOf('Linux') != -1) {
            return 'Linux';
        } 
        if(typeof platform !== 'undefined') {
            if(platform.indexOf('Mac') != -1) return 'Mac';
            if(platform.indexOf('Win') != -1) return 'Windows';
            if(platform.indexOf('Linux') != -1) return 'Linux';
        }
        
        return os;
    };

    const addBlurListener = function(element) {
        if (GetWebOperatingSystem() === "Android" || GetWebOperatingSystem() === "iOS" ||GetWebOperatingSystem() === "Mobile") {
            return;
        }
        element.addEventListener("blur", () => {
            // Delay processing of this event until the next event cycle,
            // in case focus is being moved to another accessibility element.
            if (blurDelayedCall === null) {
                blurDelayedCall = setTimeout(() => {
                    if (plugins_quorum_Libraries_Game_WebInput_.IsFocused(plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().game)) {
                        return;
                    }
                    blurDelayedCall = null;
                    focusButton.hidden = false;
                    root.hidden = true;
                });
            }
        });
    };

    

    this.Setup = function() {
        let container = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().display.plugin_.GetContainer();
        let canvas = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().display.plugin_.GetCanvas();
        let config = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().application.plugin_.GetConfiguration();
        thisGame = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().game;
        let title = config.Get_Libraries_Game_WebConfiguration__title_();
        if (title == null) {
            title = container.dataset.title;
            if (title == null) {
                title = "Game";
            }
        }

        let focusButtonName = config.Get_Libraries_Game_WebConfiguration__focusButtonName_();
        if (focusButtonName == null) {
            focusButtonName = container.dataset.focusButtonName;
            if (focusButtonName == null) {
                // Revisit this if Quorum gets localization support.
                focusButtonName = `Enter ${title}`;
            }
        }

        root = document.createElement("div");
        root.setAttribute("aria-label", title);
        root.setAttribute("tabindex", "-1");
        root.setAttribute("role", "application");
        root.setAttribute("aria-modal", true);
        root.hidden = true;

        root.style.position = "absolute";
        root.style.left = 0;
        root.style.bottom = 0;
        root.style.width = "100%";
        root.style.height = "100%";
        root.style.overflow = "clip";

        // Ensure that bugs in the positioning of shadow elements
        // don't affect the visible layout.
        // root.style.overflow = "hidden";

        controlElementRoot = document.createElement("article");
        root.appendChild(controlElementRoot);
        addBlurListener(controlElementRoot);
        textInputElements = new Map();

        // The following style settings come from Flutter Web.
        // Make all semantics transparent. We use `filter` instead of `opacity`
        // attribute because `filter` is stronger. `opacity` does not apply to
        // some elements, particularly on iOS, such as the slider thumb and track.
        // We use transparency instead of "visibility:hidden" or "display:none"
        // so that a screen reader does not ignore these elements.
        root.style.filter = "opacity(0%)";
        // Make text explicitly transparent to signal to the browser that no
        // rasterization needs to be done.
        root.style.color = "rgba(0,0,0,0)";

        addBlurListener(root);

        // Accessibility elements must be inserted before the canvas, to ensure
        // that real mouse events get routed to the canvas while simulated
        // mouse events get routed to the accessibility elements.
        container.insertBefore(root, canvas);

        focusButton = document.createElement("button");
        focusButton.setAttribute("aria-label", focusButtonName);

        focusButton.style.position = "absolute";
        focusButton.style.left = 0;
        focusButton.style.bottom = 0;
        focusButton.style.width = "100%";
        focusButton.style.height = "100%";

        // The rationales for the following styles are the same as for the root.
        focusButton.style.filter = "opacity(0%)";
        focusButton.style.color = "rgba(0,0,0,0)";
        focusButton.className = "focusButton";

        focusButton.addEventListener("click", (event) => {
            plugins_quorum_Libraries_Game_WebInput_.TakeFocus(thisGame);
        });

        // Like the accessibility root, this element must be inserted before
        // the canvas.
        container.insertBefore(focusButton, canvas);
        selectionListener = function(event) {
            if (GetWebOperatingSystem() == 'Android' || GetWebOperatingSystem() == 'iOS') {
                const activeElement = document.activeElement;
                if(activeElement) {
                    let textElement = activeElement;
                    if (!('quorumItem' in textElement)) {
                        return;
                    }
                    if (textElement.quorumItem && textElement.quorumItem.SetText$quorum_text)
                        {
                            let quorumItem = textElement.quorumItem;
                            let selection = textElement.quorumItem.GetSelection();
                            let documentSelection = document.getSelection();
                            let startIndex = textElement.selectionStart;
                            let endIndex = textElement.selectionEnd;
                            if (quorumItem.SetCaretPosition$quorum_integer)
                            {
                                quorumItem.SetCaretPosition$quorum_integer(endIndex);
                            }
                        }
                }
            }
        };
        document.addEventListener("selectionchange", selectionListener);
    };

    this.GetRoot = function() {
        return root;
    };

    this.InternalTakeFocus = function() {
        let element;
        if (currentFocus) {
            let id = currentFocus.GetHashCode();
            element = getFocusTarget(document.getElementById(id));
        } else {
            element = root;
        }
        if (!element)
            return;

        root.hidden = false;
        element.focus();
        focusButton.hidden = true;
    };

    this.InternalReleaseFocus = function() {
        focusButton.hidden = false;
        focusButton.focus();
        root.hidden = true;
    };

    const setBounds = (element, item) => {
        if (global_InstanceOf(item,"Libraries.Interface.Item2D")) {
            let item2D = global_CheckCast(item, "Libraries.Interface.Item2D");
            let x = item2D.GetScreenX();
            let y = item2D.GetScreenY();

            if (!(isNaN(x) || isNaN(y))) {
                // Find the nearest ancestor element that corresponds to an item.
                // If there is one, we need to adjust this element's position
                // to be relative to that ancestor.
                let ancestor = element.parentElement;
                while (ancestor && (ancestor !== root)) {
                    let ancestorId = ancestor.id;
                    if (ancestorId && elementList[ancestorId]) {
                        let ancestorItem = elementList[ancestorId];
                        if (global_InstanceOf(ancestorItem,"Libraries.Interface.Item2D")) {
                            let ancestorItem2D = global_CheckCast(ancestorItem, "Libraries.Interface.Item2D");
                            let ancestorX = ancestorItem2D.GetScreenX();
                            let ancestorY = ancestorItem2D.GetScreenY();
                            if (!(isNaN(ancestorX) || isNaN(ancestorY))) {
                                x -= ancestorX;
                                y -= ancestorY;
                                break;
                            }
                        }
                    }
                    ancestor = ancestor.parentElement;
                }

                let game = plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().game;
                let maxWidth = game.GetScreenWidth() - x;
                let maxHeight = game.GetScreenHeight() - y;
                let width = item2D.GetWidth();
                let height = item2D.GetHeight();

                if (maxWidth < width)
                {
                    width = maxWidth;
                }
                if (maxHeight < height)
                {
                    height = maxHeight;
                }

                element.style.position = "absolute";
                element.style.left = `${x}px`;
                element.style.bottom = `${y}px`;
                element.style.width = `${width}px`;
                element.style.height = `${height}px`;
            }
        }
    };

    //The label needs the description as well, because not all browsers support this tag
    this.NameChanged$quorum_Libraries_Interface_Item = function(item) {
        var id = item.GetHashCode();
        if( elementList[id] != null ) {
            var element = document.getElementById(id);
            if (updateOwnLabel(item)) {
                return;
            }
            if (item.GetAccessibilityCode() == 29) { //labels
                var itemName = item.GetText()
                var itemDescription =item.GetDescription();
                if (plainTooltipOwners.has(id)) {
                    itemDescription = null;
                }
                var result = itemName;
                if(itemName != null && itemDescription != null && itemName.localeCompare(itemDescription) != 0) {
                    result = result + ", " + item.GetDescription()
                }
                element.setAttribute("aria-label", result);
            } else if (plainTooltipOwners.has(id)) {
                element.setAttribute("aria-label", item.GetName());
            } else {
                element.setAttribute("aria-label", item.GetName() + ", " + item.GetDescription());
            }
        }
    };

    //descriptions are not supported on all browsers, so shove everything into the label
    //https://a11ysupport.io/tech/aria/aria-description_attribute
    this.DescriptionChanged$quorum_Libraries_Interface_Item = function(item) {
        var id = item.GetHashCode();
        if( elementList[id] != null ) {
            var element = document.getElementById(id);
            if (updateOwnLabel(item)) {
                return;
            }
            if (item.GetAccessibilityCode() == 29) { //labels
                var itemName = item.GetText()
                var itemDescription =item.GetDescription();
                if (plainTooltipOwners.has(id)) {
                    itemDescription = null;
                }
                var result = itemName;
                if(itemName != null && itemDescription != null && itemName.localeCompare(itemDescription) != 0) {
                    result = result + ", " + item.GetDescription()
                }
                element.setAttribute("aria-label", result);
            } else if (plainTooltipOwners.has(id)) {
                element.setAttribute("aria-label", item.GetName());
            } else {
                element.setAttribute("aria-label", item.GetName() + ", " + item.GetDescription());
            }
        }
    };

    this.BoundsChanged$quorum_Libraries_Interface_Item = function(item) {
        var id = item.GetHashCode();
        if( elementList[id] != null ) {
            var element = document.getElementById(id);
            setBounds(element, item);
        }
    };
    
    this.TextFieldUpdatePassword$quorum_Libraries_Interface_Controls_TextField = function(field) {
        //console.log("Text field updated Changed");
    };

//  private system action TextSelectionChanged(TextBoxSelection selection)
this.TextSelectionChanged$quorum_Libraries_Interface_Selections_TextBoxSelection = function(selection) {
    
    var textBox = selection.GetTextBox();
    if (textBox == null){
        return;
    }else{
        var id = textBox.GetHashCode();
        var element = document.getElementById(id);
        if (element == null) {
            return;
        }
        element.selectionStart = selection.GetStartIndex();
        element.selectionEnd = selection.GetEndIndex();
    }
    
}

//  private system action TextSelectionChanged(TextBoxSelection selection)
this.TextSelectionChanged$quorum_Libraries_Interface_Selections_TextFieldSelection = function(selection) {
    
    var textField = selection.GetTextField();
    if (textField == null) {
        return;
    } else {
        var id = textField.GetHashCode();
        var element = document.getElementById(id);
        if (element == null) {
            return;
        }
        element.selectionStart = selection.GetStartIndex();
        element.selectionEnd = selection.GetEndIndex();
    }

    //console.log("TextField Selection Changed");
}
    
//    system action Update
//this is handled in Quorum for now might be added back if need be
    this.Update = function() {
        //removed
        ////console.log("Update called");
    };
    
//    system action ProgressBarValueChanged(ProgressBarValueChangedEvent progress)

    // aria-valuenow must lie between aria-valuemin and aria-valuemax; the bar
    // itself draws a value outside its range as empty or full.
    function clampProgress(bar, value) {
        let min = bar.GetMinimum();
        let max = bar.GetMaximum();
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    this.ProgressBarValueChanged$quorum_Libraries_Interface_Events_ProgressBarValueChangedEvent = function(event) {
        let bar = event.GetProgressBar();
        let progressbarID = bar.GetHashCode();
        if(elementList[progressbarID] != null) {
            let element = document.getElementById(progressbarID);
            if (element != null) {
                // The range may have changed too (SetMinimum, SetMaximum).
                element.setAttribute("aria-valuemin", bar.GetMinimum());
                element.setAttribute("aria-valuemax", bar.GetMaximum());
                element.setAttribute("aria-valuenow", clampProgress(bar, event.GetNewValue()));
            }
        }
        //console.log("Progress bar updated");
    };

//    system action SliderChanged(Slider slider)

    this.SliderChanged$quorum_Libraries_Interface_Controls_Slider = function(slider) {
        let sliderID = slider.GetHashCode();
        if(elementList[sliderID] != null) {
            let element = document.getElementById(sliderID);
            if (element) {
                element.setAttribute("aria-valuemin", slider.GetMinimum());
                element.setAttribute("aria-valuemax", slider.GetMaximum());
                element.setAttribute("aria-valuenow", slider.GetValue());
                element.setAttribute("aria-valuetext", slider.GetValueText());
            }
        }
    };

    // The element of a tooltip's control, or of its nearest accessible parent
    // when the control itself has no element.
    function getTooltipOwnerElement(tooltip) {
        let owner = tooltip.GetOwner();
        if (owner == null) {
            return null;
        }
        if (owner.GetAccessibilityCode() == -1) {
            owner = owner.GetAccessibleParent();
            if (owner == null) {
                return null;
            }
        }
        let ownerID = owner.GetHashCode();
        if (elementList[ownerID] == null) {
            return null;
        }
        return document.getElementById(ownerID);
    }

//    system action TooltipOpened(Tooltip tooltip)
    // The control's element is described by the tooltip's element. For a plain
    // tooltip, the control's label leaves out its description while it shows,
    // so the same words aren't read twice.
    this.TooltipOpened$quorum_Libraries_Interface_Controls_Tooltip = function(tooltip) {
        let tooltipID = tooltip.GetHashCode();
        if (elementList[tooltipID] == null) {
            return;
        }
        let ownerElement = getTooltipOwnerElement(tooltip);
        if (ownerElement == null) {
            return;
        }
        ownerElement.setAttribute("aria-describedby", tooltipID);
        if (tooltip.GetContent() == null) {
            let owner = elementList[parseInt(ownerElement.id)];
            plainTooltipOwners.add(parseInt(ownerElement.id));
            if (owner != null) {
                this.DescriptionChanged$quorum_Libraries_Interface_Item(owner);
            }
        }
    };

//    system action TooltipClosed(Tooltip tooltip)
    // The control's element is no longer described by the tooltip, and its
    // label gets its description back.
    this.TooltipClosed$quorum_Libraries_Interface_Controls_Tooltip = function(tooltip) {
        let ownerElement = getTooltipOwnerElement(tooltip);
        if (ownerElement == null) {
            return;
        }
        let ownerID = parseInt(ownerElement.id);
        if (ownerElement.getAttribute("aria-describedby") == String(tooltip.GetHashCode())) {
            ownerElement.removeAttribute("aria-describedby");
        }
        if (plainTooltipOwners.has(ownerID)) {
            plainTooltipOwners.delete(ownerID);
            let owner = elementList[ownerID];
            if (owner != null) {
                this.DescriptionChanged$quorum_Libraries_Interface_Item(owner);
            }
        }
    };

    // The element that takes the DOM focus for an item's element: a
    // calendar's current day cell, or the element itself.
    function getFocusTarget(element) {
        if (element == null) {
            return null;
        }
        if (element.quorumFocusTarget != null && element.contains(element.quorumFocusTarget)) {
            return element.quorumFocusTarget;
        }
        return element;
    }

    // Whether an item is the one Quorum has the focus on.
    function hasQuorumFocus(item) {
        return currentFocus != null && currentFocus.GetHashCode() == item.GetHashCode();
    }

    // Sets the label of a control whose label carries its value, and returns
    // true; returns false for other controls, which are labelled as usual.
    function updateOwnLabel(item) {
        let code = item.GetAccessibilityCode();
        if (code == 33 && global_InstanceOf(item, "Libraries.Interface.Controls.ColorWell")) {
            updateColorWell(global_CheckCast(item, "Libraries.Interface.Controls.ColorWell"));
            return true;
        }
        if (code == 34 && global_InstanceOf(item, "Libraries.Interface.Controls.Calendar")) {
            updateCalendar(global_CheckCast(item, "Libraries.Interface.Controls.Calendar"));
            return true;
        }
        if (code == 15 && global_InstanceOf(item, "Libraries.Interface.Controls.Spreadsheet")) {
            updateSpreadsheet(global_CheckCast(item, "Libraries.Interface.Controls.Spreadsheet"));
            return true;
        }
        return false;
    }

    // The List a combo box shows its choices in, or null if it can't be found.
    function getComboBoxList(comboBox) {
        if (typeof comboBox.GetChoiceList === "function") {
            return comboBox.GetChoiceList();
        }
        if (typeof comboBox.Get_Libraries_Interface_Controls_ComboBox__choiceList_ === "function") {
            return comboBox.Get_Libraries_Interface_Controls_ComboBox__choiceList_();
        }
        return null;
    }

    // Brings a combo box's element up to date: whether its list is open, the
    // list's element it controls, and the option the arrows are on, which
    // the focus stays off of.
    function updateComboBox(comboBox) {
        let id = comboBox.GetHashCode();
        if (elementList[id] == null) {
            return;
        }
        let element = document.getElementById(id);
        if (element == null) {
            return;
        }
        let open = comboBox.IsOpen();
        element.setAttribute("aria-expanded", open ? "true" : "false");

        let list = getComboBoxList(comboBox);
        let listElement = null;
        if (list != null) {
            let listID = list.GetHashCode();
            comboBoxLists.set(listID, comboBox);
            if (elementList[listID] != null) {
                listElement = document.getElementById(listID);
            }
        }

        let activeID = null;
        if (listElement != null) {
            element.setAttribute("aria-controls", listElement.id);
            listElement.setAttribute("role", "listbox");
            let name = comboBox.GetName();
            if (name != null) {
                listElement.setAttribute("aria-label", name);
            }
            let highlighted = comboBox.GetHighlightedIndex();
            let count = list.GetSize();
            for (let i = 0; i < count; i++) {
                let listItem = list.GetListItem$quorum_integer(i);
                if (listItem == null) {
                    continue;
                }
                let optionID = listItem.GetHashCode();
                if (elementList[optionID] == null) {
                    continue;
                }
                let option = document.getElementById(optionID);
                if (option == null) {
                    continue;
                }
                option.setAttribute("role", "option");
                option.setAttribute("aria-selected", (open && i == highlighted) ? "true" : "false");
                if (open && i == highlighted) {
                    activeID = optionID;
                }
            }
        } else {
            element.removeAttribute("aria-controls");
        }

        if (activeID != null) {
            element.setAttribute("aria-activedescendant", activeID);
        } else {
            element.removeAttribute("aria-activedescendant");
        }
    }

    // Brings a color well's element up to date: whether its picker is open,
    // and its label, the name and the color's words, since a button has no
    // separate value.
    function updateColorWell(well) {
        let id = well.GetHashCode();
        if (elementList[id] == null) {
            return;
        }
        let element = document.getElementById(id);
        if (element == null) {
            return;
        }
        element.setAttribute("aria-expanded", well.IsOpen() ? "true" : "false");
        let label = well.GetName();
        let colorText = well.GetColorText();
        if (label == null) {
            label = colorText;
        } else if (colorText != null) {
            label = label + ", " + colorText;
        }
        if (label != null) {
            element.setAttribute("aria-label", label);
        }
    }

    // Builds a calendar's grid inside its element: a header row of weekday
    // column headers and six rows of seven day cells. The cells are not
    // Quorum items; updateCalendar fills them in.
    // Places a calendar's weekday header or day cell over where Quorum draws
    // it, as a share of the calendar's size, as CalendarKit's DayBounds does
    // (Calendar.quorum: WIDTH 268, HEIGHT 292, PADDING 8, WEEKDAY_HEIGHT 24,
    // CELL_SIZE 36, six rows). Without this the cells stack below the
    // calendar, and focusing one scrolls the page and misplaces the
    // screen reader's box.
    function placeCalendarPart(part, row, column) {
        const WIDTH = 268, HEIGHT = 292, PADDING = 8, WEEKDAY_HEIGHT = 24, CELL_SIZE = 36, ROWS = 6;
        // From the top: the day rows end PADDING above the bottom, and the
        // weekday row sits just above them. Row -1 is the weekday row.
        let gridTop = HEIGHT - PADDING - ROWS * CELL_SIZE;
        let top = row < 0 ? gridTop - WEEKDAY_HEIGHT : gridTop + row * CELL_SIZE;
        let height = row < 0 ? WEEKDAY_HEIGHT : CELL_SIZE;
        part.style.position = "absolute";
        part.style.left = (100 * (PADDING + column * CELL_SIZE) / WIDTH) + "%";
        part.style.top = (100 * top / HEIGHT) + "%";
        part.style.width = (100 * CELL_SIZE / WIDTH) + "%";
        part.style.height = (100 * height / HEIGHT) + "%";
    }

    function buildCalendarGrid(element, calendar) {
        let id = element.id;
        element.quorumCalendarGrid = true;

        let header = document.createElement("div");
        header.id = id + "-weekdays";
        header.setAttribute("role", "row");
        for (let column = 0; column < 7; column++) {
            let weekday = document.createElement("div");
            weekday.id = id + "-weekday-" + column;
            weekday.setAttribute("role", "columnheader");
            placeCalendarPart(weekday, -1, column);
            header.appendChild(weekday);
        }
        element.appendChild(header);

        for (let row = 0; row < 6; row++) {
            let rowElement = document.createElement("div");
            rowElement.id = id + "-row-" + row;
            rowElement.setAttribute("role", "row");
            for (let column = 0; column < 7; column++) {
                let cell = document.createElement("div");
                cell.id = id + "-day-" + row + "-" + column;
                cell.setAttribute("role", "gridcell");
                cell.setAttribute("tabindex", "-1");
                placeCalendarPart(cell, row, column);
                cell.quorumDate = null;

                // Clicking a day does what clicking it on the screen does: a
                // day of the month shown is chosen, a day of another month
                // is moved to, and an unavailable day is left alone.
                cell.addEventListener("click", (event) => {
                    let date = cell.quorumDate;
                    if (date == null) {
                        return;
                    }
                    if (!calendar.IsAvailable$quorum_Libraries_System_CalendarDate(date)) {
                        return;
                    }
                    if (!hasQuorumFocus(calendar)) {
                        calendar.Focus();
                    }
                    let inMonth = calendar.IsInDisplayedMonth$quorum_Libraries_System_CalendarDate(date);
                    let days = calendar.GetCurrentDay().DaysUntil$quorum_Libraries_System_CalendarDate(date);
                    calendar.MoveCurrentDay$quorum_integer(days);
                    if (inMonth) {
                        calendar.ChooseCurrentDay();
                    }
                });

                // A day cell taking the focus gives Quorum's focus to the calendar.
                cell.addEventListener("focus", (event) => {
                    if (blurDelayedCall !== null) {
                        clearTimeout(blurDelayedCall);
                        blurDelayedCall = null;
                    }
                    if (!hasQuorumFocus(calendar)) {
                        calendar.Focus();
                    }
                });
                addBlurListener(cell);
                rowElement.appendChild(cell);
            }
            element.appendChild(rowElement);
        }
    }

    // Brings a calendar's grid up to date: its label, the weekday headers,
    // and each day's words and states. The current day's cell is the one the
    // focus goes to, and gets the DOM focus when the calendar has Quorum's.
    function updateCalendar(calendar) {
        let id = calendar.GetHashCode();
        if (elementList[id] == null) {
            return;
        }
        let element = document.getElementById(id);
        if (element == null) {
            return;
        }
        let name = calendar.GetName();
        if (name != null) {
            element.setAttribute("aria-label", name);
        }

        for (let column = 0; column < 7; column++) {
            let weekday = document.getElementById(id + "-weekday-" + column);
            if (weekday == null) {
                continue;
            }
            let weekdayName = calendar.GetWeekdayName$quorum_integer(column);
            if (weekdayName == null) {
                weekdayName = "";
            }
            weekday.setAttribute("aria-label", weekdayName);
            weekday.textContent = weekdayName.substring(0, 2);
        }

        let today = new quorum_Libraries_System_CalendarDate_();
        today.SetToToday();
        let chosen = calendar.GetDate();
        let current = calendar.GetCurrentDay();
        let focusCell = null;

        for (let row = 0; row < 6; row++) {
            for (let column = 0; column < 7; column++) {
                let cell = document.getElementById(id + "-day-" + row + "-" + column);
                if (cell == null) {
                    continue;
                }
                let date = calendar.GetShownDay$quorum_integer$quorum_integer(row, column);
                cell.quorumDate = date;
                if (date == null) {
                    cell.removeAttribute("aria-label");
                    cell.textContent = "";
                    continue;
                }
                // The label is the date in words; being today, chosen or
                // unavailable comes from the cell's states, so it isn't said twice.
                cell.setAttribute("aria-label", date.ToSpokenText());
                cell.textContent = "" + date.GetDay();

                let selected = false;
                if (chosen != null) {
                    selected = chosen.Equals$quorum_Libraries_Language_Object(date);
                }
                // Only the chosen day has aria-selected, as in the W3C date
                // picker, so the other days aren't read as "not selected."
                if (selected) {
                    cell.setAttribute("aria-selected", "true");
                } else {
                    cell.removeAttribute("aria-selected");
                }

                if (today.Equals$quorum_Libraries_Language_Object(date)) {
                    cell.setAttribute("aria-current", "date");
                } else {
                    cell.removeAttribute("aria-current");
                }

                if (calendar.IsAvailable$quorum_Libraries_System_CalendarDate(date)) {
                    cell.removeAttribute("aria-disabled");
                } else {
                    cell.setAttribute("aria-disabled", "true");
                }

                if (current != null && current.Equals$quorum_Libraries_Language_Object(date)) {
                    cell.setAttribute("tabindex", "0");
                    focusCell = cell;
                } else {
                    cell.setAttribute("tabindex", "-1");
                }
            }
        }

        element.quorumFocusTarget = focusCell;
        if (focusCell != null && hasQuorumFocus(calendar) && document.activeElement !== focusCell) {
            if (plugins_quorum_Libraries_Game_WebInput_.IsFocused(plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().game)) {
                // The cell is placed over its day, but never scroll the page for it.
                focusCell.focus({ preventScroll: true });
            }
        }
    }

    // A spreadsheet's selection kinds, as in SpreadsheetSelection.
    const SHEET_CELLS = 0;
    const SHEET_ROWS = 1;
    const SHEET_COLUMNS = 2;
    const SHEET_EVERYTHING = 3;

    // Before acting on a click in a spreadsheet's grid: an edit in progress
    // is kept and the spreadsheet takes Quorum's focus, as a press on the
    // screen does.
    function prepareSpreadsheetClick(sheet) {
        if (sheet.IsEditing()) {
            sheet.CommitEditingWithoutFocus();
        }
        if (!sheet.IsFocused()) {
            sheet.Focus();
        }
    }

    // A part of a spreadsheet's grid taking the focus gives Quorum's focus to
    // the spreadsheet.
    function addSpreadsheetFocusListener(part, sheet) {
        part.addEventListener("focus", (event) => {
            if (blurDelayedCall !== null) {
                clearTimeout(blurDelayedCall);
                blurDelayedCall = null;
            }
            if (!hasQuorumFocus(sheet)) {
                sheet.Focus();
            }
        });
        addBlurListener(part);
    }

    // Makes one of the elements of a spreadsheet's grid: a row, the corner,
    // a column or row header, or a cell. Clicking a part does what clicking
    // it on the screen does.
    function makeSpreadsheetPart(sheet, partID, role) {
        let part = document.createElement("div");
        part.id = partID;
        part.setAttribute("role", role);
        part.quorumRow = -1;
        part.quorumColumn = -1;

        if (role == "gridcell") {
            part.setAttribute("tabindex", "-1");
            part.addEventListener("click", (event) => {
                prepareSpreadsheetClick(sheet);
                let selection = sheet.GetSelection();
                let extend = event.shiftKey && selection != null && !selection.IsEmpty()
                    && selection.GetKind() == SHEET_CELLS;
                sheet.MoveTo$quorum_integer$quorum_integer$quorum_boolean(part.quorumRow, part.quorumColumn, extend);
            });
            part.addEventListener("dblclick", (event) => {
                prepareSpreadsheetClick(sheet);
                sheet.MoveTo$quorum_integer$quorum_integer$quorum_boolean(part.quorumRow, part.quorumColumn, false);
                sheet.BeginEditing$quorum_text(null);
            });
            addSpreadsheetFocusListener(part, sheet);
        } else if (role == "columnheader" && part.id.endsWith("-sheet-corner")) {
            part.addEventListener("click", (event) => {
                prepareSpreadsheetClick(sheet);
                sheet.SelectEverythingAsUser();
            });
        } else if (role == "columnheader") {
            // Column letters take the focus when whole columns are selected.
            part.setAttribute("tabindex", "-1");
            addSpreadsheetFocusListener(part, sheet);
            part.addEventListener("click", (event) => {
                prepareSpreadsheetClick(sheet);
                let selection = sheet.GetSelection();
                if (event.shiftKey && selection != null && selection.GetKind() == SHEET_COLUMNS) {
                    sheet.SelectColumns$quorum_integer$quorum_integer(selection.GetAnchorColumn(), part.quorumColumn);
                } else {
                    sheet.SelectColumns$quorum_integer$quorum_integer(part.quorumColumn, part.quorumColumn);
                }
            });
        } else if (role == "rowheader") {
            // Row numbers take the focus when whole rows are selected.
            part.setAttribute("tabindex", "-1");
            addSpreadsheetFocusListener(part, sheet);
            part.addEventListener("click", (event) => {
                prepareSpreadsheetClick(sheet);
                let selection = sheet.GetSelection();
                if (event.shiftKey && selection != null && selection.GetKind() == SHEET_ROWS) {
                    sheet.SelectRows$quorum_integer$quorum_integer(selection.GetAnchorRow(), part.quorumRow);
                } else {
                    sheet.SelectRows$quorum_integer$quorum_integer(part.quorumRow, part.quorumRow);
                }
            });
        }
        return part;
    }

    // The element of a spreadsheet's grid with this id, made if it is
    // missing. Parts are kept between updates, so the focused cell isn't
    // replaced while it stays on screen.
    function getSpreadsheetPart(element, sheet, partID, role) {
        let part = element.quorumSheetParts.get(partID);
        if (part == null) {
            part = makeSpreadsheetPart(sheet, partID, role);
            element.quorumSheetParts.set(partID, part);
        }
        return part;
    }

    // Puts the wanted parts in a container in order, removing parts no longer
    // shown first, and moving only the parts that are out of place.
    function arrangeSpreadsheetParts(container, wanted) {
        let keep = new Set(wanted);
        for (let child of Array.from(container.children)) {
            if (!keep.has(child)) {
                child.remove();
            }
        }
        for (let i = 0; i < wanted.length; i++) {
            let atPlace = container.children[i];
            if (atPlace !== wanted[i]) {
                container.insertBefore(wanted[i], atPlace == undefined ? null : atPlace);
            }
        }
    }

    // Brings a spreadsheet's grid up to date: its label and size, a header
    // row of column letters, and a row for each row on screen with its row
    // number and cells. Only the cells on screen have elements; their
    // aria-rowindex and aria-colindex give their places in the whole grid.
    // The focus goes to the current row's number when whole rows are
    // selected, the current column's letters when whole columns are, and
    // otherwise the current cell; it gets the DOM focus when the spreadsheet
    // has Quorum's and isn't editing.
    // Places a spreadsheet's header or cell over where Quorum draws it, in
    // pixels from the spreadsheet's top left after scrolling, as a share of
    // its size (as SpreadsheetKit's bounds do). Without this the parts stack
    // below the grid, and focusing one scrolls the page.
    function placeSheetPart(part, sheet, left, top, width, height) {
        let sheetWidth = sheet.GetWidth();
        let sheetHeight = sheet.GetHeight();
        if (!(sheetWidth > 0 && sheetHeight > 0)) {
            return;
        }
        part.style.position = "absolute";
        part.style.left = (100 * left / sheetWidth) + "%";
        part.style.top = (100 * top / sheetHeight) + "%";
        part.style.width = (100 * width / sheetWidth) + "%";
        part.style.height = (100 * height / sheetHeight) + "%";
    }

    function updateSpreadsheet(sheet) {
        let id = sheet.GetHashCode();
        if (elementList[id] == null) {
            return;
        }
        let element = document.getElementById(id);
        if (element == null) {
            return;
        }
        element.quorumSpreadsheetGrid = true;
        // Whether one of the grid's parts has the DOM focus before any is
        // removed.
        let hadFocus = element.contains(document.activeElement) && document.activeElement !== element;
        if (element.quorumSheetParts == null) {
            element.quorumSheetParts = new Map();
        }

        let name = sheet.GetName();
        if (name != null) {
            element.setAttribute("aria-label", name);
        }
        element.setAttribute("aria-multiselectable", "true");

        let rowSize = sheet.GetDisplayedRowSize();
        let columnSize = sheet.GetDisplayedColumnSize();
        element.setAttribute("aria-rowcount", rowSize + 1);
        element.setAttribute("aria-colcount", columnSize + 1);

        let firstRow = Math.max(0, sheet.GetFirstVisibleRow());
        let lastRow = Math.min(rowSize - 1, sheet.GetLastVisibleRow());
        let firstColumn = Math.max(0, sheet.GetFirstVisibleColumn());
        let lastColumn = Math.min(columnSize - 1, sheet.GetLastVisibleColumn());

        let selection = sheet.GetSelection();
        let empty = selection == null || selection.IsEmpty();
        let kind = empty ? SHEET_CELLS : selection.GetKind();
        let currentRow = empty ? -1 : selection.GetRow();
        let currentColumn = empty ? -1 : selection.GetColumn();
        let allRows = !empty && (kind == SHEET_COLUMNS || kind == SHEET_EVERYTHING);
        let allColumns = !empty && (kind == SHEET_ROWS || kind == SHEET_EVERYTHING);
        // Where the focus goes, as in Spreadsheet's GetAccessibilityFocusPart:
        // 1 the current cell, 2 the current row's number, 3 the current
        // column's letters, 0 nothing.
        let focusPart = sheet.GetAccessibilityFocusPart();
        let focusPartElement = null;

        let rows = [];

        // The header row: the corner, then a column header for each column
        // on screen.
        let header = getSpreadsheetPart(element, sheet, id + "-sheet-header", "row");
        header.setAttribute("aria-rowindex", 1);
        let headerParts = [];
        let corner = getSpreadsheetPart(element, sheet, id + "-sheet-corner", "columnheader");
        placeSheetPart(corner, sheet, 0, 0, sheet.GetRowLabelWidthInPixels(), sheet.GetHeaderHeightInPixels());
        corner.setAttribute("aria-colindex", 1);
        corner.setAttribute("aria-selected", (!empty && kind == SHEET_EVERYTHING) ? "true" : "false");
        headerParts.push(corner);
        for (let column = firstColumn; column <= lastColumn; column++) {
            let columnHeader = getSpreadsheetPart(element, sheet, id + "-sheet-column-" + column, "columnheader");
            placeSheetPart(columnHeader, sheet, sheet.GetCellLeft$quorum_integer(column), 0, sheet.GetColumnWidthInPixels$quorum_integer(column), sheet.GetHeaderHeightInPixels());
            columnHeader.quorumColumn = column;
            columnHeader.setAttribute("aria-colindex", column + 2);
            let label = sheet.GetColumnLabel$quorum_integer(column);
            columnHeader.textContent = label == null ? "" : label;
            let selected = allRows && selection.Contains$quorum_integer$quorum_integer(Math.max(0, currentRow), column);
            columnHeader.setAttribute("aria-selected", selected ? "true" : "false");
            // Screen readers say "column", or "column, selected", in place of
            // the role.
            columnHeader.setAttribute("aria-roledescription", sheet.GetColumnHeaderRoleText$quorum_integer(column));
            if (focusPart == 3 && column == currentColumn) {
                columnHeader.setAttribute("tabindex", "0");
                focusPartElement = columnHeader;
            } else {
                columnHeader.setAttribute("tabindex", "-1");
            }
            headerParts.push(columnHeader);
        }
        arrangeSpreadsheetParts(header, headerParts);
        rows.push(header);

        // A row for each row on screen: its row number, then its cells.
        for (let row = firstRow; row <= lastRow; row++) {
            let rowElement = getSpreadsheetPart(element, sheet, id + "-sheet-row-" + row, "row");
            rowElement.setAttribute("aria-rowindex", row + 2);
            let rowParts = [];

            let rowHeader = getSpreadsheetPart(element, sheet, id + "-sheet-rowheader-" + row, "rowheader");
            placeSheetPart(rowHeader, sheet, 0, sheet.GetCellTop$quorum_integer(row), sheet.GetRowLabelWidthInPixels(), sheet.GetRowHeightInPixels());
            rowHeader.quorumRow = row;
            rowHeader.setAttribute("aria-colindex", 1);
            rowHeader.textContent = "" + (row + 1);
            let rowSelected = allColumns && selection.Contains$quorum_integer$quorum_integer(row, Math.max(0, currentColumn));
            rowHeader.setAttribute("aria-selected", rowSelected ? "true" : "false");
            // Screen readers say "row", or "row, selected", in place of the
            // role.
            rowHeader.setAttribute("aria-roledescription", sheet.GetRowHeaderRoleText$quorum_integer(row));
            if (focusPart == 2 && row == currentRow) {
                rowHeader.setAttribute("tabindex", "0");
                focusPartElement = rowHeader;
            } else {
                rowHeader.setAttribute("tabindex", "-1");
            }
            rowParts.push(rowHeader);

            for (let column = firstColumn; column <= lastColumn; column++) {
                let cell = getSpreadsheetPart(element, sheet, id + "-sheet-cell-" + row + "-" + column, "gridcell");
                placeSheetPart(cell, sheet, sheet.GetCellLeft$quorum_integer(column), sheet.GetCellTop$quorum_integer(row), sheet.GetColumnWidthInPixels$quorum_integer(column), sheet.GetRowHeightInPixels());
                cell.quorumRow = row;
                cell.quorumColumn = column;
                cell.setAttribute("aria-colindex", column + 2);
                let value = sheet.GetText$quorum_integer$quorum_integer(row, column);
                cell.textContent = value == null ? "" : value;
                let spoken = sheet.GetCellSpokenText$quorum_integer$quorum_integer(row, column);
                if (spoken != null) {
                    cell.setAttribute("aria-label", spoken);
                } else {
                    cell.removeAttribute("aria-label");
                }
                let selected = !empty && selection.Contains$quorum_integer$quorum_integer(row, column);
                cell.setAttribute("aria-selected", selected ? "true" : "false");
                // Screen readers say the cell's name, such as C2, in place of
                // the role, with the range for the current cell of a range.
                cell.setAttribute("aria-roledescription", sheet.GetCellRoleText$quorum_integer$quorum_integer(row, column));
                if (focusPart == 1 && row == currentRow && column == currentColumn) {
                    cell.setAttribute("tabindex", "0");
                    focusPartElement = cell;
                } else {
                    cell.setAttribute("tabindex", "-1");
                }
                rowParts.push(cell);
            }
            arrangeSpreadsheetParts(rowElement, rowParts);
            rows.push(rowElement);
        }

        // The grid holds only its rows; anything else in it, such as the
        // edit field, is placed just before it when added.
        arrangeSpreadsheetParts(element, rows);

        // Parts no longer shown are forgotten.
        for (let [partID, part] of Array.from(element.quorumSheetParts.entries())) {
            if (!part.isConnected) {
                element.quorumSheetParts.delete(partID);
            }
        }

        element.quorumFocusTarget = focusPartElement;
        if (sheet.IsEditing() || !hasQuorumFocus(sheet)) {
            return;
        }
        let target = focusPartElement;
        if (target == null && hadFocus && !element.contains(document.activeElement)) {
            // The focused cell scrolled away and the current cell isn't
            // shown, so the grid itself keeps the focus.
            target = element;
        }
        if (target != null && document.activeElement !== target) {
            if (plugins_quorum_Libraries_Game_WebInput_.IsFocused(plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().game)) {
                // The part is placed over its cell, but never scroll the page for it.
                target.focus({ preventScroll: true });
            }
        }
    }

//    system action SpreadsheetUpdated(Spreadsheet sheet)
    // A spreadsheet changed: its values, size, what is on screen, or the
    // selection. Its grid is brought up to date and the focus follows the
    // current cell.
    this.SpreadsheetUpdated$quorum_Libraries_Interface_Controls_Spreadsheet = function(sheet) {
        updateSpreadsheet(sheet);
    };

//    system action ComboBoxExpanded(ComboBox box)
    // A combo box's list opened: the field is expanded and controls the list.
    this.ComboBoxExpanded$quorum_Libraries_Interface_Controls_ComboBox = function(box) {
        updateComboBox(box);
    };

    // A control that opened a popup menu, with the attributes it had before,
    // by the menu's id. While the menu is open, the control is marked as
    // MDN's menu button is: aria-haspopup, aria-controls and aria-expanded.
    // A control that already uses aria-expanded for itself, such as a tree
    // item, keeps its own value, so its open or closed state isn't misreported.
    const popupOpeners = new Map();

    function restoreAttribute(element, name, value) {
        if (value == null) {
            element.removeAttribute(name);
        } else {
            element.setAttribute(name, value);
        }
    }

//    system action PopupMenuOpenedNative(PopupMenu menu, Item opener)
    this.PopupMenuOpenedNative$quorum_Libraries_Interface_Controls_PopupMenu$quorum_Libraries_Interface_Item = function(menu, opener) {
        let element = document.getElementById(opener.GetHashCode());
        if (element == null) {
            return;
        }
        let saved = {
            haspopup: element.getAttribute("aria-haspopup"),
            controls: element.getAttribute("aria-controls"),
            expanded: element.getAttribute("aria-expanded")
        };
        popupOpeners.set(menu.GetHashCode(), { element: element, saved: saved });
        element.setAttribute("aria-haspopup", "menu");
        element.setAttribute("aria-controls", String(menu.GetHashCode()));
        if (saved.expanded == null) {
            element.setAttribute("aria-expanded", "true");
        }
    };

//    system action PopupMenuClosedNative(PopupMenu menu)
    // The control that opened the popup gets back the attributes it had.
    this.PopupMenuClosedNative$quorum_Libraries_Interface_Controls_PopupMenu = function(menu) {
        let entry = popupOpeners.get(menu.GetHashCode());
        if (entry == null) {
            return;
        }
        popupOpeners.delete(menu.GetHashCode());
        restoreAttribute(entry.element, "aria-haspopup", entry.saved.haspopup);
        restoreAttribute(entry.element, "aria-controls", entry.saved.controls);
        restoreAttribute(entry.element, "aria-expanded", entry.saved.expanded);
    };

    // Marks a tab pane's tabs selected or not (aria-selected), and hides
    // every tab's panel but the selected one's, as MDN's tab pattern does.
    function updateTabs(pane) {
        if (pane == null) {
            return;
        }
        let selected = pane.GetSelectedTab();
        let selectedID = selected == null ? null : selected.GetHashCode();
        let tabs = pane.GetTabs();
        for (let i = 0; i < tabs.GetSize(); i++) {
            let tab = tabs.Get$quorum_integer(i);
            let tabID = tab.GetHashCode();
            let element = document.getElementById(tabID);
            if (element != null) {
                element.setAttribute("aria-selected", tabID == selectedID ? "true" : "false");
            }
            let panel = document.getElementById(tabID + "-tabpanel");
            if (panel != null) {
                panel.hidden = (tabID != selectedID);
            }
        }
    }

//    system action TabSelectedNative(Tab tab)
    // A tab was selected: its pane's tabs and panels are brought up to date.
    this.TabSelectedNative$quorum_Libraries_Interface_Controls_Tab = function(tab) {
        updateTabs(tab.GetTabPane());
    };

//    system action ComboBoxCollapsed(ComboBox box)
    // A combo box's list closed: the field is collapsed with no active option.
    this.ComboBoxCollapsed$quorum_Libraries_Interface_Controls_ComboBox = function(box) {
        updateComboBox(box);
    };

//    system action ColorWellUpdated(ColorWell well)
    // A color well's color changed: its label gets the new color's words.
    this.ColorWellUpdated$quorum_Libraries_Interface_Controls_ColorWell = function(well) {
        updateColorWell(well);
    };

//    system action CalendarUpdated(Calendar calendar)
    // A calendar changed: its days are filled in again and the focus follows
    // the current day.
    this.CalendarUpdated$quorum_Libraries_Interface_Controls_Calendar = function(calendar) {
        updateCalendar(calendar);
    };

    // The item an item's element stands for: the item itself, or its nearest
    // accessible parent when it has no element of its own; null if neither
    // has an element.
    function getAccessibleItem(item) {
        if (item == null) {
            return null;
        }
        if (item.GetAccessibilityCode() == -1) {
            item = item.GetAccessibleParent();
            if (item == null) {
                return null;
            }
        }
        if (elementList[item.GetHashCode()] == null) {
            return null;
        }
        return item;
    }

    // Whether NativeAdd can put an item's element in the document: the list
    // or radio group it joins must have its element already. Items placed in
    // other controls' own parts, such as menus, trees, tabs and tables, are
    // left to those controls.
    function canAddElement(item) {
        let code = item.GetAccessibilityCode();
        if (code == -1 || code == 8 || code == 11 || code == 13 || code == 16 || code == 24) {
            return false;
        }
        let parentItem = null;
        if (global_InstanceOf(item, "Libraries.Interface.Controls.ListItem")) {
            parentItem = global_CheckCast(item, "Libraries.Interface.Controls.ListItem").GetList();
        } else if (global_InstanceOf(item, "Libraries.Interface.Controls.RadioButton")) {
            parentItem = global_CheckCast(item, "Libraries.Interface.Controls.RadioButton").GetButtonGroup();
        }
        if (parentItem == null) {
            return true;
        }
        return document.getElementById(parentItem.GetHashCode()) != null;
    }

    // Whether a part's element belongs to an element, as aria-activedescendant
    // needs: inside it, or inside an element it controls or owns.
    function isPartOf(element, partElement) {
        if (element.contains(partElement)) {
            return true;
        }
        let related = (element.getAttribute("aria-controls") || "") + " " + (element.getAttribute("aria-owns") || "");
        for (let relatedID of related.split(/\s+/)) {
            if (relatedID === "") {
                continue;
            }
            let relatedElement = document.getElementById(relatedID);
            if (relatedElement != null && relatedElement.contains(partElement)) {
                return true;
            }
        }
        return false;
    }

    // Takes an id out of an element's aria-owns, removing the attribute when
    // nothing is left in it.
    function removeOwned(element, ownedID) {
        let owns = (element.getAttribute("aria-owns") || "").split(/\s+/).filter((ownsID) => ownsID !== "" && ownsID !== ownedID);
        if (owns.length > 0) {
            element.setAttribute("aria-owns", owns.join(" "));
        } else {
            element.removeAttribute("aria-owns");
        }
    }

    // Removes the aria-activedescendant given to the element with this id, and
    // the aria-owns entry added with it.
    function clearActiveDescendant(ownerID) {
        let ownedID = activeDescendantOwners.get(ownerID);
        activeDescendantOwners.delete(ownerID);
        let element = document.getElementById(ownerID);
        if (element == null) {
            return;
        }
        element.removeAttribute("aria-activedescendant");
        if (ownedID != null) {
            removeOwned(element, ownedID);
        }
    }

    // Removes the aria-activedescendant given to every element but the one
    // with this id, so only the focused item points at a part.
    function clearOtherActiveDescendants(keepID) {
        for (let ownerID of Array.from(activeDescendantOwners.keys())) {
            if (ownerID !== keepID) {
                clearActiveDescendant(ownerID);
            }
        }
    }

//    system action ActiveDescendantUpdated(Item focus, Item active)
    // The focused item's active part changed: its element's
    // aria-activedescendant points at the part's element, which is added if
    // it is missing and owned by the element if it isn't already its part.
    // With no part, the attribute is removed. A combo box keeps its own
    // handling, which also marks the option the arrows are on.
    this.ActiveDescendantUpdated$quorum_Libraries_Interface_Item$quorum_Libraries_Interface_Item = function(focus, active) {
        let item = getAccessibleItem(focus);
        if (item == null) {
            return;
        }
        let ownerID = String(item.GetHashCode());
        let element = document.getElementById(ownerID);
        if (element == null) {
            return;
        }
        clearOtherActiveDescendants(ownerID);

        let partElement = null;
        if (active != null) {
            let part = active;
            if (part.GetAccessibilityCode() == -1) {
                part = part.GetAccessibleParent();
            }
            if (part != null) {
                let partID = part.GetHashCode();
                if (elementList[partID] == null && canAddElement(part)) {
                    this.NativeAdd$quorum_Libraries_Interface_Item(part);
                }
                if (elementList[partID] != null) {
                    partElement = document.getElementById(partID);
                }
            }
        }

        let ownedID = activeDescendantOwners.get(ownerID);
        if (ownedID != null) {
            removeOwned(element, ownedID);
        }
        activeDescendantOwners.delete(ownerID);

        if (item.GetAccessibilityCode() == 32 && global_InstanceOf(item, "Libraries.Interface.Controls.ComboBox")) {
            updateComboBox(global_CheckCast(item, "Libraries.Interface.Controls.ComboBox"));
            if (element.hasAttribute("aria-activedescendant")) {
                activeDescendantOwners.set(ownerID, null);
            }
            return;
        }

        if (partElement == null || partElement === element) {
            element.removeAttribute("aria-activedescendant");
            return;
        }

        ownedID = null;
        if (!isPartOf(element, partElement)) {
            let owns = element.getAttribute("aria-owns");
            element.setAttribute("aria-owns", owns ? owns + " " + partElement.id : partElement.id);
            ownedID = partElement.id;
        }
        element.setAttribute("aria-activedescendant", partElement.id);
        activeDescendantOwners.set(ownerID, ownedID);
    };

//    system action Select(Item item)

    this.Select$quorum_Libraries_Interface_Item = function(item) {
        //console.log(item.GetName() + " selected");
        var id = item.GetHashCode();
        // A combo box's option: updateComboBox points the field at it.
        if (global_InstanceOf(item, "Libraries.Interface.Controls.ListItem")) {
            let list = global_CheckCast(item, "Libraries.Interface.Controls.ListItem").GetList();
            if (list != undefined && comboBoxLists.has(list.GetHashCode())) {
                return;
            }
        }
        // Look for the nearest focusable element.
        let element = document.getElementById(id);
        while (element) {
            if (element.hasAttribute("tabindex")) {
                break;
            }
            element = element.parentElement;
        }
        if (element == null) {
            // The item has no element, or nothing above it takes focus: a
            // list that closed as its choice was made, such as a combo box's,
            // whose options updateComboBox handles.
            return;
        }
        element.setAttribute("aria-activedescendant", id);
    };

//    system action SelectionChanged(SelectionEvent event)
    // REMOVED for now as it was easier to handle Quorum side
    this.SelectionChanged$quorum_Libraries_Interface_Events_SelectionEvent = function(event) {
        //console.log("Selection Changed");
    };
    
//    system action ButtonActivated(Button button)
this.ButtonActivated$quorum_Libraries_Interface_Controls_Button = function(button) {
    var id = button.GetHashCode();
    if( elementList[id] != null ) {
        var element = document.getElementById(id);
        // removed for now because this makes it a toggle button
        // not sure what we should be doing instead
        //element.setAttribute('aria-pressed', "true");
    }
    //console.log("Button Activated");
};

//    system action ToggleButtonToggled(ToggleButton button)    
this.ToggleButtonToggled$quorum_Libraries_Interface_Controls_ToggleButton = function(button) {
    var id = button.GetHashCode();
    if( elementList[id] != null ) {
        var element = document.getElementById(id);
        element.setAttribute("aria-checked", button.GetToggleState())
    }
    //console.log("Toggled Buttoned");
};
    
//    system action FocusChanged(FocusEvent event)
    this.FocusChanged$quorum_Libraries_Interface_Events_FocusEvent = function(event) {
        var item = event.GetNewFocus();
        if(item == null) {
            //console.log("Tried to focus nothing");
            return;
        }

        // if not accessible focus the parent
        // if no accessible parent then ignore event
        if (item.GetAccessibilityCode() == -1) {
            var accessibleFocus = item.GetAccessibleParent();
            if (accessibleFocus != undefined) {
                item = accessibleFocus;
            } else {
                return;
            }
        }
        var id = item.GetHashCode();
        //accessible item got focus that was never added
        if (elementList[id] == null) {
            return;
        }
        currentFocus = item;
        // The item that had the focus no longer points at its active part;
        // the new one's part, if it has one, is set right after this.
        clearOtherActiveDescendants(String(id));
        // A color well's picker closing returns the focus to it, so its
        // expanded state is brought up to date here.
        updateOwnLabel(item);
        if (plugins_quorum_Libraries_Game_WebInput_.IsFocused(plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().game) || removedFocusedElement) {
            removedFocusedElement = false;
            let element = getFocusTarget(document.getElementById(id));
            if (element != null) {
                element.focus();
            }
        }
        
    };
//    system action NativeAdd(Item item)
    this.NativeAdd$quorum_Libraries_Interface_Item = function(item) {
        //dont add to DOM if not accessible
        if (item.GetAccessibilityCode() == -1) {
            return false;
        }

       //replace this code with item appropriate material
        var id = item.GetHashCode();
        //dont want to add something to the DOM twice
        if( elementList[id] != null ) {
            return false;
        }
        var itemName = item.GetName();
        var itemDescription = item.GetDescription();
        elementList[id] = item;      //adds the item to the elementList array using the item's HashCode value as an index
        elementType = "DIV";
        //default role
        let role = null;
        let roleDescription = item.GetAccessibilityRoleDescription();

        /* Creating Item Element Tag with Attributes */
        var parent = undefined; // used if item needs to be added to group
        var para = document.createElement(elementType);
        para.id = id;       //sets the item's id to the item's HashCode value

        var sendInputEvents = false;
        let tablist = null;
        let tabpanel = null;
        // run once the element is in the document, for controls whose
        // attributes point at other elements
        let afterAdd = null;

        switch(item.GetAccessibilityCode()){
            //ITEM or CUSTOM
            case 0:
            case 1:
                if (item.IsFocusable() ) {
                    role = "application";
                } else {
                    role = "img";
                }
                // If a custom role description isn't provided, an empty string
                // will indicate that while assistive technologies should treat
                // this item like an application, e.g. by switching into
                // focus mode, it's not really an application, but we don't know
                // what it is.
                if (roleDescription == null) {
                    roleDescription = "";
                }
                break;
            //CHECKBOX
            case 2:
                role = "checkbox";
                if (item.GetName() == undefined)
                    itemName = "Check Box"
                if (global_InstanceOf(item,"Libraries.Interface.Controls.Checkbox")) {
                    let checkbox = global_CheckCast(item, "Libraries.Interface.Controls.Checkbox");
                    para.setAttribute('aria-checked', checkbox.GetToggleState());
                }   
                //check for checked status
                break;
            //RADIO_BUTTON
            case 3:
                role = "radio";
                if (global_InstanceOf(item,"Libraries.Interface.Controls.RadioButton")) {
                    let radioButton = global_CheckCast(item, "Libraries.Interface.Controls.RadioButton");
                    // attach to proper parent
                    // IDK WHAT WILL HAPPEN IF RADIO BUTTON IS ADDED TO GAME BEFORE GROUP
                    let parentGroup = radioButton.GetButtonGroup();
                    if (parentGroup != undefined) {
                        // attach to radiogroup
                        parent = parentGroup.GetHashCode();
                    }
                    para.setAttribute("aria-checked", radioButton.GetToggleState());
                }
                break;
            //BUTTON
            case 4:
                role = "button";
                break;
            //TOGGLE_BUTTON
            case 5:
                role = "button";
                para.setAttribute("aria-roledescription","toggle button");
                if (item.GetName() == undefined)
                    itemName = "Toggle Button"
                para.setAttribute('aria-pressed', "false");
                //check for pressed
                break;
            //TEXTBOX
            case 6:
                elementType = "textarea"
                para = document.createElement(elementType);
                role="textbox";
                para.setAttribute('aria-multiline','true');
                para.id = id;
                sendInputEvents = true;

                if (global_InstanceOf(item, "Libraries.Interface.Controls.TextBox"))
                {
                    let textBox = global_CheckCast(item, "Libraries.Interface.Controls.TextBox")
                    para.value = textBox.GetText();
                    para.selectionStart = 0;
                    para.selectionEnd = 0;
                }
                para.quorumItem = item;
                textInputElements.set(id, para);
                break;
            //MENU_BAR
            case 7:
                role = "menubar";
                break;
            //MENU_ITEM
            case 8:
                role = "menuitem";
                if (global_InstanceOf(item,"Libraries.Interface.Controls.MenuItem")) {
                    let menuItem = global_CheckCast(item, "Libraries.Interface.Controls.MenuItem");
                    if (menuItem.IsMenu()) {
                        let menuGroup = document.createElement(elementType);
                        menuGroup.id = id + "-submenu";
                        menuGroup.setAttribute("role", "menu");
                        // A menu needs a name: the item that opens it.
                        menuGroup.setAttribute("aria-labelledby", id);
                        para.appendChild(menuGroup);
                        para.setAttribute("aria-haspopup", true);
                        para.setAttribute("aria-expanded", menuItem.IsOpen());
                    }
                    //attach to proper parent
                    let parentMenu = menuItem.GetParentMenu();
                    if (parentMenu != undefined) {
                        parent = parentMenu.GetHashCode() + "-submenu";
                    } else {
                        parentMenu = menuItem.GetMenuRoot();
                        if (parentMenu != undefined) {
                            parent = parentMenu.GetHashCode();
                        } else {
                            // lonely menu item
                        }
                    }
                }
                break;
            //PANE
            case 9:
                break;
            //TREE
            case 10:
                role = "tree";
                break;
            //TREE_ITEM
            case 11:
                role = "treeitem";
                // tree items can have subtrees so they need a group too
                if (global_InstanceOf(item,"Libraries.Interface.Controls.TreeItem")) {
                    let treeItem = global_CheckCast(item, "Libraries.Interface.Controls.TreeItem");
                    if (treeItem.IsSubtree()) {
                        let treegroup = document.createElement(elementType);
                        treegroup.id = id+"-group";
                        treegroup.setAttribute("role","group");
                        para.appendChild(treegroup);
                        para.setAttribute("aria-expanded", treeItem.IsOpen());
                    }
                    // attach to proper parent
                    let parentTree = treeItem.GetParentTreeItem();
                    if (parentTree != undefined) {
                        // if attached to a treeitem they need to be in a group
                        parent = parentTree.GetHashCode() + "-group";
                    } else {
                        parentTree = treeItem.GetTree();
                        if (parentTree != undefined) {
                            // if attached to tree directly they can be a child of that element
                            parent = parentTree.GetHashCode();
                        } else {
                            // the tree item is not on any tree
                        }
                    }
                }
                break;
            //TOOLBAR
            case 12:
                role = "toolbar";
                break;
            //TAB
            case 13:
                role = "tab";
                if (global_InstanceOf(item,"Libraries.Interface.Controls.Tab")) {
                    let tab = global_CheckCast(item, "Libraries.Interface.Controls.Tab");
                    let tabpane = tab.GetTabPane();
                    if (tabpane != undefined) {
                        parent = tabpane.GetHashCode();
                    }
                    // create associated tab panel
                    let tabpanelID = tab.GetHashCode()+"-tabpanel";
                    tabpanel = document.createElement(elementType);
                    tabpanel.id = tabpanelID;
                    tabpanel.role = "tabpanel";
                    tabpanel.setAttribute("aria-labelledby", tab.GetHashCode());
                    
                    para.setAttribute("aria-controls", tabpanelID);
                    afterAdd = () => {
                        updateTabs(tabpane);
                    };
                }
                break;
            //TAB_PANE
            case 14:
                role = "tablist";
                break;
            //TABLE: a spreadsheet is a grid labelled by its name, with a
            //header row of column letters and a row for each row on screen,
            //built by updateSpreadsheet; the current cell takes the focus.
            case 15:
                role = "table";
                if (global_InstanceOf(item, "Libraries.Interface.Controls.Spreadsheet")) {
                    let spreadsheet = global_CheckCast(item, "Libraries.Interface.Controls.Spreadsheet");
                    role = "grid";
                    itemName = spreadsheet.GetName();
                    itemDescription = null;
                    para.quorumSpreadsheetGrid = true;
                    para.quorumSheetParts = new Map();
                    afterAdd = () => {
                        updateSpreadsheet(spreadsheet);
                    };
                }
                break;
            //CELL: spreadsheets no longer have cell items; their cells are
            //parts of the spreadsheet's grid.
            case 16:
                role = "cell";
                break;
            //TEXT_FIELD
            case 17:
                elementType = "INPUT"
                para = document.createElement(elementType);
                para.id = id;
                para.type = "text"
                sendInputEvents = true;

                if (global_InstanceOf(item, "Libraries.Interface.Controls.TextField"))
                {
                    let textField = global_CheckCast(item, "Libraries.Interface.Controls.TextField")
                    para.value = textField.GetText();
                    para.selectionStart = 0;
                    para.selectionEnd = 0;
                }

                para.quorumItem = item;
                textInputElements.set(id, para);
                break;
            //LIST
            case 18:
                role = "list";
                if (comboBoxLists.has(id)) {
                    role = "listbox";
                }
                break;
            //LIST_ITEM
            case 19:
                role = "listitem";
                if (global_InstanceOf(item,"Libraries.Interface.Controls.ListItem")) {
                    let listItem = global_CheckCast(item, "Libraries.Interface.Controls.ListItem");
                    para.textContent = listItem.GetText();
                    itemName = listItem.GetText();
                    //attach to proper parent
                    let parentList = listItem.GetList();
                    if (parentList != undefined) {
                        parent = parentList.GetHashCode();
                        if (comboBoxLists.has(parent)) {
                            role = "option";
                            para.setAttribute("aria-selected", "false");
                        }
                    } else {
                        // the list item is not in a list
                    }
                }
                break;
            //TREE_TABLE
            case 20:
                role = "treegrid";
                let rowgroup = document.createElement(elementType);
                rowgroup.id = id+"-rowgroup";
                rowgroup.setAttribute("role", "rowgroup");
                para.appendChild(rowgroup);
                break;
            //DIALOG
            case 21:
                role = "dialog";
                // dialogs are accessible parents so as long as their children are added properly dialogs will be announced
                break;
            //POPUP_MENU: a menu, named by the popup (by default "Pop-Up Menu");
            //its items attach to it through GetMenuRoot.
            case 22:
                role = "menu";
                break;
            //PROGRESS_BAR
            case 23:
                role = "progressbar";
                if (global_InstanceOf(item,"Libraries.Interface.Controls.ProgressBar")) {
                    let progressbar = global_CheckCast(item, "Libraries.Interface.Controls.ProgressBar");
                    para.setAttribute("aria-valuemin", progressbar.GetMinimum());
                    para.setAttribute("aria-valuemax", progressbar.GetMaximum());
                    para.setAttribute("aria-valuenow", clampProgress(progressbar, progressbar.GetValue()));
                }
                break;
            //TREE_TABLE_CELL
            case 24:
                role = "gridcell";
                if (global_InstanceOf(item,"Libraries.Interface.Controls.TreeTableCell")) {
                    let treecell = global_CheckCast(item, "Libraries.Interface.Controls.TreeTableCell");
                    let treeTableID = undefined;
                    if (treecell.GetTreeTable() != undefined) {
                        treeTableID = treecell.GetTreeTable().GetHashCode() + "-rowgroup"
                    }
                    let treeTableRow = treecell.GetRow();
                    if (treeTableRow != undefined && treeTableID != undefined) {
                        let rowID = treeTableRow.GetHashCode();
                        let row = document.getElementById(rowID);
                        // if the row is not in the DOM make a new row
                        if (row == undefined) {
                            row = document.createElement(elementType);
                            row.id = treeTableRow.GetHashCode();
                            row.setAttribute("role", "row");
                            let tableElement = document.getElementById(treeTableID);
                            if (tableElement != undefined) {
                                tableElement.appendChild(row);
                            }
                        }
                        parent = rowID;
                        //check if this is an expandable cell
                        if (!treeTableRow.IsEmpty()) {
                            let column = treecell.GetColumn();
                            if (column != undefined && column.IsFirstColumn()) {
                                para.setAttribute("aria-expanded", treeTableRow.IsExpanded());
                            }
                        }
                    }
                    para.innerHTML = treecell.GetText();
                }
                break;
            //GROUP
            case 25:
                role = "radiogroup";
                if (item.GetName() == undefined)
                    itemName = "Radio Group"
                break;
            case 26:
                role="application";
                break;
            case 27:
                role="graphics-object group";
                break;
            case 28:
                role="graphics-symbol img";
                break;
            case 29: //label
                if (item.IsFocusable() ) {
                    role = "img";
                    itemName = item.GetText();
                    if(itemName != null && itemDescription != null && itemName.localeCompare(itemDescription)==0) {
                        itemDescription = null;
                    }
                }
                break;
            //SLIDER: a value in a range; its keys reach Quorum as other
            //controls' do, and aria-valuetext says it with its units.
            case 30:
                role = "slider";
                if (global_InstanceOf(item,"Libraries.Interface.Controls.Slider")) {
                    let slider = global_CheckCast(item, "Libraries.Interface.Controls.Slider");
                    para.setAttribute("aria-orientation", "horizontal");
                    para.setAttribute("aria-valuemin", slider.GetMinimum());
                    para.setAttribute("aria-valuemax", slider.GetMaximum());
                    para.setAttribute("aria-valuenow", slider.GetValue());
                    para.setAttribute("aria-valuetext", slider.GetValueText());
                }
                break;
            //TOOLTIP: a plain tooltip is role tooltip holding its words; one
            //with buttons is a dialog that doesn't trap the focus, and its
            //buttons are its child elements.
            case 31:
                if (global_InstanceOf(item,"Libraries.Interface.Controls.Tooltip")) {
                    let tooltip = global_CheckCast(item, "Libraries.Interface.Controls.Tooltip");
                    itemName = tooltip.GetText();
                    itemDescription = null;
                    if (tooltip.GetContent() == null) {
                        role = "tooltip";
                        para.textContent = itemName;
                    } else {
                        role = "dialog";
                        para.setAttribute("aria-modal", "false");
                    }
                } else {
                    role = "tooltip";
                }
                break;
            //COMBO_BOX: a text field whose list of choices is a listbox it
            //controls; the focus stays in the field, and the option the
            //arrows are on is its active descendant.
            case 32:
                elementType = "INPUT"
                para = document.createElement(elementType);
                para.id = id;
                para.type = "text"
                sendInputEvents = true;
                role = "combobox";
                para.setAttribute("aria-autocomplete", "list");
                para.setAttribute("aria-haspopup", "listbox");
                para.setAttribute("aria-expanded", "false");

                if (global_InstanceOf(item, "Libraries.Interface.Controls.ComboBox"))
                {
                    let comboBox = global_CheckCast(item, "Libraries.Interface.Controls.ComboBox");
                    para.value = comboBox.GetText();
                    para.selectionStart = 0;
                    para.selectionEnd = 0;
                    afterAdd = () => {
                        updateComboBox(comboBox);
                    };
                }

                para.quorumItem = item;
                textInputElements.set(id, para);
                break;
            //COLOR_WELL: a button that opens the color picker; its label is
            //the name and the color's words, since a button has no value.
            case 33:
                role = "button";
                para.setAttribute("aria-haspopup", "dialog");
                if (global_InstanceOf(item, "Libraries.Interface.Controls.ColorWell")) {
                    let well = global_CheckCast(item, "Libraries.Interface.Controls.ColorWell");
                    para.setAttribute("aria-expanded", well.IsOpen() ? "true" : "false");
                    itemDescription = well.GetColorText();
                    if (itemName == null) {
                        itemName = itemDescription;
                        itemDescription = null;
                    }
                }
                break;
            //CALENDAR: a grid labelled by the month, with weekday column
            //headers and six weeks of day cells built here; the current day's
            //cell takes the focus.
            case 34:
                role = "grid";
                if (global_InstanceOf(item, "Libraries.Interface.Controls.Calendar")) {
                    let calendar = global_CheckCast(item, "Libraries.Interface.Controls.Calendar");
                    itemName = calendar.GetName();
                    itemDescription = null;
                    buildCalendarGrid(para, calendar);
                    afterAdd = () => {
                        updateCalendar(calendar);
                    };
                }
                break;
            default:
                // do nothing?
        }

        if (parent == undefined) {
            let accessibleParent = item.GetAccessibleParent();
            if (accessibleParent != undefined) {
                let parentID = accessibleParent.GetHashCode();
                if (elementList[parentID] != null) {
                    parent = parentID;
                }
            }
        }

        if (role != null) {
            para.setAttribute("role",role);
        }
        if (roleDescription != null) {
            para.setAttribute("aria-roledescription", roleDescription);
        }
        
        if (itemName != null) {
            var result = itemName;
            if(itemDescription != null) {
                result = result + ", " + itemDescription;
            }
            para.setAttribute("aria-label", result);
        }

        if (item.IsFocusable()) {
            para.setAttribute("tabindex", "-1");
            para.addEventListener("click", (event) => {
                let webEvent = new quorum_Libraries_Interface_Events_WebAccessibilityEvent_();
                webEvent.type = 4
                webEvent.focusedItem = elementList[parseInt(event.target.id)];
                me_.NotifyListeners$quorum_Libraries_Interface_Events_WebAccessibilityEvent(webEvent);
            });
            para.addEventListener("focus", (event) => {
                if (blurDelayedCall !== null) {
                    clearTimeout(blurDelayedCall);
                    blurDelayedCall = null;
                }
                if (currentFocus !== elementList[parseInt(event.target.id)]) {
                    elementList[parseInt(event.target.id)].Focus();
                }
            });
            addBlurListener(para);
        }

        if (global_InstanceOf(item,"Libraries.Interface.Controls.ListItem")) {
            let listItem = global_CheckCast(item, "Libraries.Interface.Controls.ListItem");
            para.addEventListener("click", (event) => {
                if (event.target !== para) {
                    return; // ignore bubbled events
                }
                listItem.Select();
            });
        } else if (global_InstanceOf(item,"Libraries.Interface.Controls.Control")) {
            let control = global_CheckCast(item, "Libraries.Interface.Controls.Control");
            para.addEventListener("click", (event) => {
                if (event.target !== para) {
                    return; // ignore bubbled events
                }
                control.Activate();
            });
        }

        if (sendInputEvents && plugins_quorum_Libraries_Game_WebInput_.HasTouchScreen())
        {
            para.addEventListener('input', plugins_quorum_Libraries_Game_WebInput_.InputText);
            // If it can process input events, we'll need to sometimes refer back to the Quorum item in order to update text.
            para.quorumItem = item;
        }
        
        //add element to a parent if need be or directly to the root
        if (parent != undefined) {
            var parentElement = document.getElementById(parent);
            if(parentElement.role == "tab") {
                parentElement = document.getElementById(parent+"-tabpanel");
            }
            if(tablist !== null) {
                parentElement.appendChild(tablist);
            }
            if ((parentElement.quorumCalendarGrid === true || parentElement.quorumSpreadsheetGrid === true) && parentElement.parentNode != null) {
                // A grid holds only its rows, so a calendar's own controls,
                // such as its month buttons, and a spreadsheet's edit field
                // go just before it.
                parentElement.parentNode.insertBefore(para, parentElement);
            } else {
                parentElement.appendChild(para);
            }
            if(tabpanel !== null) {
                parentElement.parentNode.appendChild(tabpanel);
            }
            //console.log(item.GetName(), " has been added to a parent.");
        } else {
            if(tablist !== null) {
                root.appendChild(tablist);
            }
            if(tabpanel !== null) {
                root.appendChild(tabpanel);
            }
            root.appendChild(para);
            //console.log(item.GetName(), " has been added.");
        }

        // Set the element's bounds after we've added it, so setBounds can assume
        // the element's parent is already set.
        setBounds(para, item);

        if (afterAdd !== null) {
            afterAdd();
        }

        // Under rare circumstances, the item that is being added is supposed to already be focused by Quorum,
        // or it was previously focused, but then was removed and re-added.
        // If so, make sure it gets the focus.
        if (item.IsFocused() && (plugins_quorum_Libraries_Game_WebInput_.IsFocused(plugins_quorum_Libraries_Game_GameStateManager_.GetActiveGameInfo().game) || removedFocusedElement)) {
            removedFocusedElement = false;
            getFocusTarget(para).focus();
        }

        return true;
};
//    system action NativeRemove(Item item)
    this.NativeRemove$quorum_Libraries_Interface_Item = function(item) {
        let id = item.GetHashCode();
        //cant remove what's not there
        if( elementList[id] == null ) {
            return false;
        }
        let element = document.getElementById(id);
        if (textInputElements.has(id)) {
            let textElement = textInputElements.get(id);
            if (!('quorumItem' in textElement)) {
                textElement.quorumItem = null;
            }
            textInputElements.delete(id);
        }
        if (element != null) { //if the parent was removed then this would come up null
            if (element === document.activeElement)
            {
                removedFocusedElement = true;
            }

            element.remove();
        }
        plainTooltipOwners.delete(id);
        activeDescendantOwners.delete(String(id));
        comboBoxLists.delete(id);
        for (let [listID, comboBox] of comboBoxLists) {
            if (comboBox.GetHashCode() == id) {
                comboBoxLists.delete(listID);
            }
        }
        //console.log(elementList[id], " has been removed.");
        elementList[id] = null;
        return true;
    };
    
//    system action MenuChanged(MenuChangeEvent event)
    this.MenuChanged$quorum_Libraries_Interface_Events_MenuChangeEvent = function(event) {
        //console.log("Menu Changed");
        var menuItemID = event.GetMenuItem().GetHashCode();
        if (elementList[menuItemID] != null) {
            var element = document.getElementById(menuItemID);
            if (event.GetEventType() == 1) {  //OPENED
                element.setAttribute("aria-expanded", true);
            } else if (event.GetEventType() == 2) {   //CLOSED
                element.setAttribute("aria-expanded", false);
            }
        }
    };
    
//    system action TreeChanged(TreeChangeEvent event)
    this.TreeChanged$quorum_Libraries_Interface_Events_TreeChangeEvent = function(event) {
        //console.log("Tree Changed");
        var treeItemID = event.GetTreeItem().GetHashCode();
        if (elementList[treeItemID] != null) {
            var element = document.getElementById(treeItemID);
            if (event.GetEventType() == 1) {  //OPENED
                element.setAttribute("aria-expanded", true);
            } else if (event.GetEventType() == 2) {   //CLOSED
                element.setAttribute("aria-expanded", false);
            }
        }
    };
    
//    system action TreeTableChanged(TreeTableChangeEvent event)
    this.TreeTableChanged$quorum_Libraries_Interface_Events_TreeTableChangeEvent = function(event) {
        //console.log("TreeTable Changed");
        let cells = event.GetTreeTableCells();
        for(let i = 0; i< cells.GetSize(); i++) {
            let cell = cells.Get$quorum_integer(i).GetHashCode();
            let element = document.getElementById(cell);
            if (element != undefined && element.hasAttribute("aria-expanded")) {
                if (event.GetEventType() == 1) {  //OPENED
                    element.setAttribute("aria-expanded", true);
                } else if (event.GetEventType() == 2) {   //CLOSED
                    element.setAttribute("aria-expanded", false);
                }
                break;
            }
        }
    };
    
//    system action ControlActivated(ControlActivationEvent event)
    this.ControlActivated$quorum_Libraries_Interface_Events_ControlActivationEvent = function(event) {
        //console.log("Control Activated");
    };

//    system action TextChanged(TextChangeEvent event)
    this.TextChanged$quorum_Libraries_Interface_Events_TextChangeEvent = function(event) {
        var control = event.GetControl();
        if ( global_InstanceOf(control,"Libraries.Interface.Controls.TextBox") )
        {
            var textbox = global_CheckCast(control, "Libraries.Interface.Controls.TextBox");
            var text = textbox.GetText();
            var id = textbox.GetHashCode();
            var position = textbox.GetCaretPosition();
            var element = document.getElementById(id);
            if(element != null) {
              element.value = text;
              element.selectionStart = position;
              element.selectionEnd = position;
            }
            // console.log("Caret Position: ", position);
            // console.log("Current Line", linePosition);
            // console.log("TextBox text Changed");
        }
        else if ( global_InstanceOf(control,"Libraries.Interface.Controls.TextField") )
        {
            var textfield = global_CheckCast(control, "Libraries.Interface.Controls.TextField");
            var text = textfield.GetText();
            var id = textfield.GetHashCode();
            var position = textfield.GetCaretPosition();
            var element = document.getElementById(id);
            if(element != null) {
                element.selectionStart = position;
                element.selectionEnd = position;
              element.value = text;
            }
            // Moving the highlight writes its choice into the field, so the
            // active option is brought up to date with the text.
            if (global_InstanceOf(control, "Libraries.Interface.Controls.ComboBox")) {
                updateComboBox(global_CheckCast(control, "Libraries.Interface.Controls.ComboBox"));
            }
            //console.log("TextField Text Changed");
        }
        else {
            //console.log("Text Changed");
        }
        
    };
    
//    system action WindowFocusChanged(WindowFocusEvent event)
    this.WindowFocusChanged$quorum_Libraries_Interface_Events_WindowFocusEvent = function(event) {
        //console.log("Window Focused");
    };
    
//    system action Notify(Item item, text value)
    this.Notify$quorum_Libraries_Interface_Item$text = function(item, value) {
        //console.log("Notify call");
    };
    
//    system action Notify(Item item, text value, integer notificationType)
    this.Notify$quorum_Libraries_Interface_Events_Item$text$integer = function(item, value, notificationType) {
        //console.log("Notify call 2");
    };
    
//    system action Shutdown
    this.Shutdown = function() {
        //console.log("Shutdown");
        //dispose of the shadow DOM tree
        if(controlElementRoot) {
            controlElementRoot.remove();
            controlElementRoot = null;
            controlElementList.clear();
            controlElementList = null;
        }
        if(textInputElements) {
            textInputElements.clear();
            textInputElements = null;
        }
        if (root) {
            root.remove();
            root = null;
        }
        
        if (focusButton) {
            focusButton.remove();
            focusButton = null;
        }
        document.removeEventListener("selectionchange", selectionListener);
        comboBoxLists.clear();
        elementList.length = 0;
        currentFocus = null;
        thisGame = null;
    };

    this.OnHiddenElementClick = function(item) {
        let event = new quorum_Libraries_Interface_Events_WebAccessibilityEvent_();
        event.type = 4
        event.focusedItem = item;
        me_.NotifyListeners$quorum_Libraries_Interface_Events_WebAccessibilityEvent(event);
    }

    this.AddHiddenHeader$quorum_text$quorum_text$quorum_boolean = function(name, title, attachToRoot) {
        element = document.createElement("h2");

        element.style.position = "absolute";
        element.style.left = 0;
        element.style.top = 0;
        element.style.width = "1px";
        element.style.height = "1px";
        element.innerHTML = title;
        element.id = name + "hiddenHeader";
        if (attachToRoot) {
            root.appendChild(element);
        } else {
            controlElementRoot.appendChild(element);
        }
        controlElementList.set(name,element);
        addBlurListener(element);
    };

    this.AddHiddenButton$quorum_text = function(name) {
        element = document.createElement("button");

        element.style.position = "absolute";
        element.style.left = 0;
        element.style.top = 0;
        element.style.width = "1px";
        element.style.height = "1px";
        element.innerHTML = name;
        element.id = name + "hiddenButton";
        element.addEventListener("click", (event) => {  
            let webEvent = new quorum_Libraries_Interface_Events_WebAccessibilityEvent_();
            webEvent.eventType = webEvent.FOCUSED;
            webEvent.elementName = name;
            me_.NotifyListeners$quorum_Libraries_Interface_Events_WebAccessibilityEvent(webEvent);
        });
        controlElementRoot.appendChild(element);
        controlElementList.set(name,element);
        addBlurListener(element);
    };

    this.AddHiddenLabel$quorum_text$quorum_text$quorum_boolean = function(name, words, attachToRoot) {
        element = document.createElement("label");

        element.style.position = "absolute";
        element.style.left = 0;
        element.style.top = 0;
        element.style.width = "1px";
        element.style.height = "1px";
        element.innerHTML = words;
        element.id = name + "hiddenLabel";
        if (attachToRoot) {
            root.appendChild(element);
        } else {
            controlElementRoot.appendChild(element);
        }
        controlElementList.set(name,element);
        addBlurListener(element);
    };

    this.ModifyHiddenLabel$quorum_text$quorum_text = function(name, words) {
        element = document.getElementById(name+"hiddenLabel");
        if(element) {
            element.innerHTML = words;
        }
    };

    this.AddHiddenSlider$quorum_text$quorum_integer$quorum_integer$quorum_integer = function(name, min, max, step) {
        element = document.createElement("input");

        element.style.position = "absolute";
        element.style.left = 0;
        element.style.top = 0;
        element.style.width = "1px";
        element.style.height = "1px";
        element.type = "range";
        element.min = min;
        element.max = max;
        element.step = step;
        element.id = name + "hiddenSlider";
        element.addEventListener("input", (event) => {  
            let webEvent = new quorum_Libraries_Interface_Events_WebAccessibilityEvent_();
            webEvent.eventType = webEvent.SLIDER_CHANGED;
            webEvent.elementName = name;
            webEvent.sliderValue = event.target.value;
            me_.NotifyListeners$quorum_Libraries_Interface_Events_WebAccessibilityEvent(webEvent);
        });
        controlElementRoot.appendChild(element);
        controlElementList.set(name,element);
        addBlurListener(element);
    };

    this.ModifyHiddenSlider$quorum_text$quorum_integer$quorum_integer$quorum_integer = function(name, min, max, step) {
        element = document.getElementById(name+"hiddenSlider");

        element.min = min;
        element.max = max;
        element.step = step;
    };

    this.SetHiddenSliderValueText$quorum_text$quorum_text = function(name, value) {
        element = document.getElementById(name+"hiddenSlider");

        element.ariaValueText = value;
    };

    this.SetHiddenSliderCurrentValue$quorum_text$quorum_integer = function(name, value) {
        element = document.getElementById(name+"hiddenSlider");

        element.value = value;
    };

    this.FocusHiddenElement$quorum_text = function(name) {
        if (controlElementList.has(name)) {
            controlElementList.get(name).focus();
        }
    };

    this.SetHiddenOnElement$quorum_text$quorum_boolean = function(id, hide) {
        const element = document.getElementById(id);
        if(element) {
            element.ariaHidden = hide;
        }
    }

    this.ForceReleaseFocus = function() {
        focusButton.hidden = false;
        root.hidden = true;
    };

/*
 * This implementation is old, but I am leaving it in as an exemplar. 
 * 
 * 
 *
    this.InvokeButton$quorum_Libraries_Interface_Item = function(item) {
        var buttonId = this.id;
        var button = elementList[buttonId];
        //console.log(button.GetName(), "has been clicked.");
        
        var mouseEvent = new quorum_Libraries_Interface_Events_MouseEvent_();     //Creates MouseEvent variable
        mouseEvent.SetSource$quorum_Libraries_Interface_Item(button);                 //sets the Source for the mouseEvent to the quorum_FakeButton_ item
        mouseEvent.Set_Libraries_Interface_Events_MouseEvent__eventType_(1);      //sets the MouseEvent to MOUSE_CLICKED
        button.ActivateFakeButton$quorum_Libraries_Interface_Events_MouseEvent(mouseEvent);
    };
    

    this.UpdateToggleState$quorum_Libraries_Interface_Item$boolean = function(item, selected) {
        var toggleId = this.id;
        var toggle = elementList[toggleId];
        
        var mouseEvent = new quorum_Libraries_Interface_Events_MouseEvent_();
        mouseEvent.SetSource$quorum_Libraries_Interface_Item(toggle);
        mouseEvent.Set_Libraries_Interface_Events_MouseEvent__eventType_(1);
        if(toggle.GetAccessibilityCode() == 2){     //checkboxes
            if (this.checked == true){
                toggle.ActivateFakeCheckbox$quorum_Libraries_Interface_Events_MouseEvent(mouseEvent);
                //console.log(toggle.GetName(), "has been checked.");
            }
            else if (this.checked == false){
                toggle.DeactivateFakeCheckbox$quorum_Libraries_Interface_Events_MouseEvent(mouseEvent);
                //console.log(toggle.GetName(), "has been unchecked.");
            }
        }
        else if (toggle.GetAccessibilityCode() == 3){   //radio buttons work on traversing radio button options within the group
            //console.log("radio buttons");
        }

    };
    */
}
