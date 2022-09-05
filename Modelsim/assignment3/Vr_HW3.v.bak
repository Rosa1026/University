module Vr_HW3 (CLK, RST, AIN, BIN, LDISP, RDISP, SC_LDISP, SC_RDISP);
  /* I/O interface */
  input CLK, RST;
  input [0:1] AIN, BIN;
  output [0:12] LDISP, RDISP;
  output [0:12] SC_LDISP, SC_RDISP;

  /* Implementation 5: top-level module */
  /* see page 10 for its structure */
  /* instantiate one main FSM, two series detectors, one score calculator,
     and one display decoder; and properly connect them with each other */
  wire AS;
  wire BS;
  wire [2:0] STAT;
  wire [3:0] ASC, BSC;

  Vr_HW3_Series A_Series (CLK, RST, AIN, AS);
  Vr_HW3_Series B_Series (CLK, RST, BIN, BS);

  Vr_HW3_RSP Main (CLK, RST, AIN, BIN, AS, BS, STAT);

  Vr_HW3_Score Score (CLK, RST, STAT, ASC, BSC);

  Vr_HW3_Disp_Decoder Display (AIN, BIN, STAT, ASC, BSC, LDISP, RDISP, SC_LDISP, SC_RDISP);
endmodule
