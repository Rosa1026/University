module Vr_HW1_mode11(I, O);
  input [3:0] I;
  output O;
  
  /* internal wire declarations */
  wire a, b, c;
  /* fill in your structural desgin of mode11 here. 
     only Vr_HW1_mode01 and built-in AND gate can be used */
  Vr_HW1_mode01 U1(.O(a), .I(I));
  or U2 (b, I[3], I[2]);
  not U3 (c, I[1]);
  and U4 (O, a, b, c);

endmodule
