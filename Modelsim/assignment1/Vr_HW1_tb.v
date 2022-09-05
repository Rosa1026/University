`timescale 1 ns/ 100 ps
module Vr_HW1_tb();
  reg [3:0] tb_I;
  reg [1:0] tb_mode;
  wire tb_O;

  /* instatination of UUT */
  Vr_HW1 UUT (.I(tb_I), .mode(tb_mode), .O(tb_O));

  /* input stimuli generation */
  initial begin: tb
  /* your test bench generation comes here */
   integer i;
   integer n;
   for(n=0;n<=3;n=n+1)begin
    tb_mode=n; #0;
    for(i=0;i<=15;i=i+1) begin
     tb_I=i; #1; end
   end
  end
endmodule
