; ================================================================
; END G-CODE for the Lamprey slicer: Bambu Lab P2S
; EXTERNAL SPOOL version: leaves the filament loaded (no unload to AMS).
; Based on Bambu Studio's own P2S end code (2026/05/18 version).
; Placeholders: {LIFT_Z} = top + 0.4, {CLEAR_Z} = top + 10,
;   {PARK_Z} / {PARK_Z_BACK} = Bambu's "80 - Z/2" bed drop, capped at
;   the build height set with SetBedHeight(256).
; ================================================================
M106 S0
M106 P2 S0
M981 S0 P20000 ; close spaghetti detector

;======== P2S end gcode ==========
M400 ; wait for buffer to clear
G92 E0 ; zero the extruder
M211 Z1

G90
G1 Z{LIFT_Z} F900 ; lower z a little
M1002 judge_flag timelapse_record_flag
M622 J1
    G150.3
    M400 ; wait all motion done
    M991 S0 P-1 ;end smooth timelapse at safe pos
    M400 S5 ;wait for last picture to be taken
M623  ;end of "timelapse_record_flag

G90
G1 Z{CLEAR_Z} F900 ; lower z a little

M140 S0 ; turn off bed
M106 S0 ; turn off fan
M106 P2 S0 ; turn off remote part cooling fan
M106 P3 S0 ; turn off chamber cooling fan
M106 P10 S0 ; turn off left aux fan

G150.1 F8000 ; wipe nozzle

G150.3
M104 S0 ; turn off hotend
M400 ; wait all motion done
M17 S
M17 Z0.4 ; lower z motor current to reduce impact if there is something in the bottom
G1 Z{PARK_Z} F600
G1 Z{PARK_Z_BACK}
M400 P100
M17 R ; restore z current

M220 S100  ; Reset feedrate magnitude
M201.2 K1.0 ; Reset acc magnitude
M73.2 R1.0 ;Reset left time magnitude
M1002 set_gcode_claim_speed_level : 0

M1015.3 S0 ;disable clog detect
M1015.4 S0 K0 ;disable air printing detect

;=====printer finish air purification=========
M622.1 S0
M1002 judge_flag print_finish_air_filt_flag

M622 J1
M1002 gcode_claim_action : 66
M145 P1
M106 P2 S255
M400 S180
M106 P2 S0
M623

M622 J2
M1002 gcode_claim_action : 66
M145 P0
M106 P3 S255
M400 S180
M106 P3 S0
M623
;=====printer finish air purification=========

;=====printer finish  sound=========
M17
M400 S1
M1006 S1
M1006 A53 B10 L50 C53 D10 M50 E53 F10 N50
M1006 A57 B10 L50 C57 D10 M50 E57 F10 N50
M1006 A0 B15 L0 C0 D15 M0 E0 F15 N0
M1006 A53 B10 L50 C53 D10 M50 E53 F10 N50
M1006 A57 B10 L50 C57 D10 M50 E57 F10 N50
M1006 A0 B15 L0 C0 D15 M0 E0 F15 N0
M1006 A48 B10 L50 C48 D10 M50 E48 F10 N50
M1006 A0 B15 L0 C0 D15 M0 E0 F15 N0
M1006 A60 B10 L50 C60 D10 M50 E60 F10 N50
M1006 W
;=====printer finish  sound=========
M400
M18
M73 P100 R0
