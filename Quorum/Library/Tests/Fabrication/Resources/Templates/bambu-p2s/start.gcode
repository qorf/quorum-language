; ================================================================
; START G-CODE for the Lamprey slicer: Bambu Lab P2S, 0.4 mm nozzle
; EXTERNAL SPOOL version: no AMS filament switch (no T0). Load the
; filament into the toolhead from the printer screen BEFORE printing.
; Based on Bambu Studio's own P2S start code (2026/05/18 version),
; with Bambu's {...} expressions replaced by Lamprey placeholders:
;   {BED_TEMP} {NOZZLE_TEMP} {NOZZLE_DIAMETER}
;   {FIRST_MIN_X} {FIRST_MIN_Y} {FIRST_SIZE_X} {FIRST_SIZE_Y}  (bed mesh area)
;   {FLOW_FEED} {CALI_FLOW} {PURGE_FEED}  (from SetMaxFlow, 21 for Bambu PLA)
;
; Assumes PLA on a Textured PEI plate. For PETG / other plates, see the
; notes marked "CHANGE" below.
; ================================================================
M201 X20000 Y20000 Z500 E5000
M203 X600 Y600 Z20 E30
M204 P20000 R5000 T20000
M205 X9.00 Y9.00 Z3.00 E2.50
M106 S0
M106 P2 S0

;======== P2S start gcode==========
  M140 S{BED_TEMP} ; heat heatbed first
  M993 A0 B0 C0 ; nozzle cam detection not allowed.
  M400

;=====printer start sound ===================
M17
M400 S1
M1006 S1
M1006 A53 B9 L50 C53 D9 M50 E53 F9 N50
M1006 A56 B9 L50 C56 D9 M50 E56 F9 N50
M1006 A61 B9 L50 C61 D9 M50 E61 F9 N50
M1006 A53 B9 L50 C53 D9 M50 E53 F9 N50
M1006 A56 B9 L50 C56 D9 M50 E56 F9 N50
M1006 A61 B18 L50 C61 D18 M50 E61 F18 N50
M1006 W
;=====printer start sound ===================

  M620 M ;enable remap
  G389

;===== avoid end stop =================
  G91
  G380 S2 Z22 F1200
  G380 S2 Z-12 F1200
  G90
;===== avoid end stop =================

;===== reset machine status =================
  M204 S10000
  M630 S0 P1
  G90
  M17 D ; reset motor current to default
  M960 S5 P1 ; turn on logo lamp
  G90
  M220 S100 ;Reset Feedrate
  M1002 set_gcode_claim_speed_level: 5
  M221 S100 ;Reset Flowrate
  M73.2   R1.0 ;Reset left time magnitude
  G29.1 Z0 ; clear z-trim value first
  M983.1 M1
  M982.2 S1 ; turn on cog noise reduction
  M983.4 S0
;===== reset machine status =================

;==== set airduct mode (PLA / PETG: cooling mode) ====
M145 P0 ; set airduct mode to cooling mode for cooling
M106 P2 S255 ; turn on auxiliary fan for cooling
M106 P3 S127 ; turn on chamber fan for cooling
M1002 gcode_claim_action : 29
M191 S0 ; wait for chamber temp
M106 P2 S102 ; turn on chamber cooling fan
M622.1 S0
M1002 judge_flag ventobox_replace_aux1_fan_flag
M622 J0
M106 P10 S0 ; turn off left aux fan
M623
M142 P6 R30 S40 U0.3 V0.8 ; set exhaust chamber autocooling
;==== set airduct mode ====

;===== start to heat heatbed & hotend==========
  M1002 gcode_claim_action : 2
  M1002 set_filament_type:PLA ; CHANGE for other filaments (PETG, ABS, ...)
  M104 S140 A

  G29.2 S0 ; avoid invalid abl data

;===== first homing start =====
  M1002 gcode_claim_action : 13
  G28 X T300
  G150.1 F8000 ; wipe mouth to avoid filament stick to heatbed
  G150.3
  M972 S24 P0
  M972 S26 P0 C0
  M972 S42 P0 T5000
  G150.1 F8000 ; wipe mouth to avoid filament stick to heatbed
  G90
  G1 X128 Y128 F30000
  G28 Z P0 T400
  M400
;===== first homing end =====

;===== detection start =====
  M1002 gcode_claim_action : 11
  M104 S140 A ; rise temp in advance
  M972 S19 P0 T5000 ;plate type detection
;===== detection end =====

;===== prepare print temperature and material ==========
  M400
  M211 X0 Y0 Z0 ;turn off soft endstop
  M975 S1 ; turn on input shaping

  G29.2 S0 ; avoid invalid abl data
  G150.3

M620.10 A0 F{FLOW_FEED} H{NOZZLE_DIAMETER} T240 P{NOZZLE_TEMP} S1
M620.10 A1 F{FLOW_FEED} H{NOZZLE_DIAMETER} T240 P{NOZZLE_TEMP} S1

  ; (no filament switch: prints with the filament already in the toolhead)
    M620.11 P0 L0 I0 E0
    M620.11 K0 I0 R0
     M620 S0A
     M1002 gcode_claim_action : 4
     M1002 set_filament_type:UNKNOWN
     M400
     T0
     M400
     M628 S0
     M629
     M400
     M1002 set_filament_type:PLA
     M621 S0A
;===== prepare print temperature and material ==========

;===== auto extrude cali start =========================
  M975 S1
  M1002 judge_flag extrude_cali_flag
  M622 J0
    M983.3 F{CALI_FLOW} A0.4 ; cali dynamic extrusion compensation
  M623

  M622 J1
    M1002 set_filament_type:PLA
    M1002 gcode_claim_action : 8
    M109 S{NOZZLE_TEMP}
    G90
    M83
    M983.3 F{CALI_FLOW} A0.4 ; cali dynamic extrusion compensation
    M400
    M106 P1 S255
    M400 S5
    M106 P1 S0
    G150.3
  M623

  M622 J2
    M1002 set_filament_type:PLA
    M1002 gcode_claim_action : 8
    M109 S{NOZZLE_TEMP}
    G90
    M83
    M983.3 F{CALI_FLOW} A0.4 ; cali dynamic extrusion compensation
    M400
    M106 P1 S255
    M400 S5
    M106 P1 S0
    G150.3
  M623
;===== auto extrude cali end =========================

    M83
    G1 E-3 F1800
    M400 P500

  G150.2
  G150.1 F8000
  G150.2
  G150.1 F8000

  G91
  G1 Y-16 F12000 ; move away from the trash bin
  G90
  M400

  M104 S140 A

;===== wipe nozzle start =====
  M1002 gcode_claim_action : 14
  G150 T{NOZZLE_TEMP}
  M400

  M109 S140 A

  G91
  G1 Z5 F1200
  G90
  M400
  G150.1
;===== wipe nozzle end =====

;===== mech mode sweep start =====
  M1002 gcode_claim_action : 3
  G90
  G1 X128 Y128 F20000
  G1 Z5 F1200
  M400 P200
  M970.3 Q1 A5 K0 O1
  M970.2 Q1 K1 W74 Z0.01
  M974 Q1 S2 P0
  M970.3 Q0 A7 K0 O1
  M970.2 Q0 K1 W74 Z0.01
  M974 Q0 S2 P0
  M975 S1
  M400
;===== mech mode sweep end =====

;===== bed leveling (only where the first layer prints) ==================
  M1002 gcode_claim_action : 54
  M190 S{BED_TEMP}; ensure bed temp
  M109 S140 A
  M106 S0 ; turn off fan , too noisy
  M1002 judge_flag g29_before_print_flag
  M622 J1
    M1002 gcode_claim_action : 1
    G29 A1 X{FIRST_MIN_X} Y{FIRST_MIN_Y} I{FIRST_SIZE_X} J{FIRST_SIZE_Y}
    M400
  M623

  M622 J2
    M1002 gcode_claim_action : 1
    G29 A2 X{FIRST_MIN_X} Y{FIRST_MIN_Y} I{FIRST_SIZE_X} J{FIRST_SIZE_Y}
    M400
  M623

  M622 J0
    G28
  M623
  G29.2 S1
  G28
;===== bed leveling end ================================

  M985.1 U0 E2
  M985.1 U1 E2

  M104 S{NOZZLE_TEMP} A
  G150.3 ; move to garbage can to wait for temp

;===== wait temperature reaching the reference value =======
  M190 S{BED_TEMP}

  ;========turn off light and fans =============
  M960 S1 P0 ; turn off laser
  M960 S2 P0 ; turn off laser
  M106 S0 ; turn off cooling fan
;===== wait temperature reaching the reference value =======

  M1002 gcode_claim_action : 255
  M400
  M975 S1 ; turn on mech mode supression

  M211 X0 Y0 Z0 ;turn off soft endstop
  G91
  G1 Z6 F1200
  G90

;===== z offset for the plate ==========================
; Textured PEI with bed <= 89 C: Z0.01 (Bambu's value)
; CHANGE: smooth PEI / Cool Plate / Engineering Plate use Z0.03
  G29.1 Z0.01

;===== nozzle load line ===============================
M1002 gcode_claim_action : 51
  G29.2 S1 ; ensure z comp turn on
  G90
  M1002 set_filament_type:PLA
  M620 S0A
  M400
  T0
  M400
  M628 S0
  M629
  M400
  M621 S0A
  M83
  M400 P50
  M500 D1
  M400 S3
  M109 S{NOZZLE_TEMP}
  G0 X100 Y0 F24000
  M400
  G130 O0 X100 Y-0.2 Z0.6 F{PURGE_FEED} L40 E12 D4
  G90
  M83
  G1 Z1
  M400
;===== nozzle load line end ===========================
M1002 gcode_claim_action : 0
  G29.99

M1015.3 S1 H{NOZZLE_DIAMETER} ;enable PLA / PETG / TPU clog detect
M1015.4 S1 K1 H{NOZZLE_DIAMETER} ;enable E air printing detect (PLA / PETG)

M1010 Q0 B0.023 S0.01
M1010 Q1 B0.005 S0.01
M1010.1 S1
; MACHINE_START_GCODE_END

; filament start gcode
G90
G21
M83 ; use relative distances for extrusion
M981 S1 P20000 ;open spaghetti detector
