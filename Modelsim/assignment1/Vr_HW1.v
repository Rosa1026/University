module Vr_HW1(I, mode, O);

  input [3:0] I;
  input [1:0] mode;
  parameter i=1;
  output O;

  /* internal signal delcaration here (if necessary) */
  wire m1, m2, m3, m4;
  /* fill in your structural implementation here */
  not U1(m1, i);
  Vr_HW1_mode01 U2(.O(m2), .I(I));
  Vr_HW1_mode10 U3(.O(m3), .I(I));
  Vr_HW1_mode11 U4(.O(m4), .I(I));
  Vr_HW1_MUX4 U5(.data_out(O), .a(m1), .b(m2), .c(m3), .d(m4), .sel(mode));
endmodule
