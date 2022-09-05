module Vr_HW2_ripple_adder_M_bits(A, B, CIN, S, COUT);
  parameter M=32;
  input [M-1:0] A, B;
  input CIN; /* CIN_0 for LSB */
  output [M-1:0] S;
  output COUT; /* COUT_M-1 for MSB */

  genvar i;
  wire [M-1:0] sig_cin, sig_s, sig_cout;

  /* Implementation 6: complete the design of an M-bit parameterizable ripple adder 
     using full adders (structural design) */
  assign sig_cin[0]=CIN;
  assign COUT=sig_cout[M-1];
  assign S=sig_s;

  for(i=0;i<M-1;i=i+1) begin
    assign sig_cin[i+1]=sig_cout[i];
    Vr_HW2_FA U1 (A[i],B[i], sig_cin[i], sig_s[i], sig_cout[i]);
  end
  Vr_HW2_FA U2 (A[M-1],B[M-1],sig_cin[M-1],sig_s[M-1],sig_cout[M-1]);
endmodule