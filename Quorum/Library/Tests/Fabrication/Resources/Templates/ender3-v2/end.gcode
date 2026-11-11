; ##################
; END G-CODE for the Lamprey slicer
; Runs once, AFTER the last layer.
;

; Lines starting with ; are comments; the printer ignores them.
; #######################
; ------ PASTE BELOW THIS LINE ------
M104 S0 ;nozzle heater off
M140 S0 ;bed heater off
G91 ;relative positioning
G1 Z10 F600 ;lift away from the print
G90
G1 X0 Y{PARK_Y} F3000
M107 ;fan off
M84 ;motors off
