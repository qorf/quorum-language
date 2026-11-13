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

    this.ProgressBarValueChanged$quorum_Libraries_Interface_Events_ProgressBarValueChangedEvent = function(event) {
        let progressbarID = event.GetProgressBar().GetHashCode();
        if(elementList[progressbarID] != null) {
            let element = document.getElementById(progressbarID);
            element.setAttribute("aria-valuenow", event.GetNewValue());
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
                cell.setAttribute("aria-selected", selected ? "true" : "false");

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
                focusCell.focus();
            }
        }
    }

//    system action ComboBoxExpanded(ComboBox box)
    // A combo box's list opened: the field is expanded and controls the list.
    this.ComboBoxExpanded$quorum_Libraries_Interface_Controls_ComboBox = function(box) {
        updateComboBox(box);
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
        // Look for the nearest focusable element.
        let element = document.getElementById(id);
        while (element) {
            if (element.hasAttribute("tabindex")) {
                break;
            }
            element = element.parentElement;
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
                }
                break;
            //TAB_PANE
            case 14:
                role = "tablist";
                break;
            //TABLE
            case 15:
                role = "table";
                // if cells get added after the spreadsheet is added this could cause issues
                if (global_InstanceOf(item,"Libraries.Interface.Controls.Spreadsheet")) {
                    let spreadsheet = global_CheckCast(item, "Libraries.Interface.Controls.Spreadsheet");
                    for (let i = 0; i < spreadsheet.GetColumnsSize(); i++) {
                        let row = document.createElement(elementType);
                        row.id =id+"-row-"+i;
                        row.setAttribute("role","row");
                        para.appendChild(row);
                    }
                }
                break;
            //CELL
            case 16:
                role = "cell";
                if (global_InstanceOf(item,"Libraries.Interface.Controls.Cell")) {
                    let cell = global_CheckCast(item, "Libraries.Interface.Controls.Cell");
                    let spreadsheet = global_CheckCast(cell.GetSpreadsheet(), "Libraries.Interface.Controls.Spreadsheet");
                    para.innerHTML = cell.GetText();
                    if(spreadsheet != undefined) {
                        let position = spreadsheet.GetCellCoordinates$quorum_Libraries_Interface_Controls_Cell(cell);
                        let rowNum = position.GetFirstValue();
                        parent = spreadsheet.GetHashCode()+"-row-"+rowNum.GetValue();
                    }
                }
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
            //POPUP_MENU
            case 22:
                break;
            //PROGRESS_BAR
            case 23:
                role = "progressbar";
                if (global_InstanceOf(item,"Libraries.Interface.Controls.ProgressBar")) {
                    let progressbar = global_CheckCast(item, "Libraries.Interface.Controls.ProgressBar");
                    para.setAttribute("aria-valuemin", progressbar.GetMinimum());
                    para.setAttribute("aria-valuemax", progressbar.GetMaximum());
                    para.setAttribute("aria-valuenow", progressbar.GetValue());
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
            if (parentElement.quorumCalendarGrid === true && parentElement.parentNode != null) {
                // A grid holds only its rows, so a calendar's own controls,
                // such as its month buttons, go just before it.
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
