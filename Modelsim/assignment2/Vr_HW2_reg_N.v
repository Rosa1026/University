module Vr_HW2_reg_N(CLK, RST, EN, REG_IN, REG_OUT);
  parameter N=32, INIT_VAL=0;
  input CLK;
  input RST;
  input EN;
  input [N-1:0] REG_IN;
  output reg [N-1:0] REG_OUT;

  always @ (posedge CLK)
    if (RST)
      REG_OUT = INIT_VAL;
    else
      if (EN) REG_OUT <= REG_IN;

endmodule
