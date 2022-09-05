module Vr_HW1_mode01(I, O);
  input [3:0] I;
  output O;
  
  /* fill in your dataflow specification here */
  wire a;
  /* use only "continuous-assignments statements */
  assign a = ~(I[0]==1);
  assign O = (I!=0)&a;
endmodule
