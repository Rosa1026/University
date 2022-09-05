module Vr_HW2_FA(A, B, CIN, S, COUT);
  input A, B, CIN;
  output S, COUT;

  /* Implementation 5: complete the design of a full adder */
  wire L1,L2,L3,L4,L5,L6,L7,L8,L9,L10;
  not U1(L1,A);
  not U2(L2,B);
  not U3(L3,CIN);
  and U4(L4,A,L2,L3);
  and U5(L5,L1,B,L3);
  and U6(L6,L1,L2,CIN);
  and U7(L7,A,B,CIN);
  or U8(S,L4,L5,L6,L7);

  and U9(L8,A,B);
  and U10(L9,A,CIN);
  and U11(L10,B,CIN);
  or U12(COUT,L8,L9,L10);
endmodule
