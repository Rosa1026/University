`timescale 1 ns / 100 ps
module Vr_HW2_tb();
  reg sig_clk, sig_rst;

  Vr_HW2 UUT (sig_clk, sig_rst);

  always begin
    sig_clk = 1; #10;
    sig_clk = 0; #10;
  end

  always begin
    sig_rst = 0; #15;
    sig_rst = 1; #20;
    sig_rst = 0; #10000;
  end

endmodule
