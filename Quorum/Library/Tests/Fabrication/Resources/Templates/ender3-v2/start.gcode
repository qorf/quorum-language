; ##################
; START G-CODE for the Lamprey slicer
; Runs once, BEFORE the first layer.
;

;
; After this block the slicer itself sets G90 (absolute XYZ) and M83
; (relative extrusion), so pasted code may use either extrusion mode.
; Lines starting with ; are comments; the printer ignores them.
; #######################
; ------ PASTE BELOW THIS LINE ------
M140 S{BED_TEMP}
M190 S{BED_TEMP}
M104 S{NOZZLE_TEMP}
M109 S{NOZZLE_TEMP}
G28 ; home all axes
G29
G90
M83
G1 Z2.0 F300
G92 E0
G1 X10 Y20 Z0.3 F5000   ; go to front left, just above the bed
G1 X10 Y120 E8 F1500    ; prime line 1, 100 mm toward the back
G1 X10.4 Y120 F5000     ; step over one line width
G1 X10.4 Y20 E8 F1500   ; prime line 2, back toward the front
G92 E0
G1 Z2.0 F3000           ; lift before the print starts
