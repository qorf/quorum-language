from hub import button

class plugins_quorum_Libraries_Robots_Spike_Button_:
    def __init__(self):
        pass

    def IsLeftPressedNative(self):
        if button.pressed(button.LEFT):
            return True
        else:
            return False
        
    def IsRightPressedNative(self):
        if button.pressed(button.RIGHT):
            return True
        else:
            return False
        
    def ButtonPressDuration__quorum_integer(self, btn):
        return button.pressed(btn)