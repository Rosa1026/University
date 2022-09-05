module Vr_HW2_control (op, RegDst, Branch, MemRead, MemtoReg, ALUOp, MemWrite, ALUSrc, RegWrite);
  input [5:0] op;
  output RegDst, Branch, MemRead, MemtoReg, MemWrite, ALUSrc, RegWrite;
  output [1:0] ALUOp;

  wire sig_r_format, sig_lw, sig_sw, sig_beq;

  /* Implementation 10: fill in your implementation here */
  wire L0,L1,L2,L3,L4,L5;
  not n1 (L5,op[5]);
  not n2 (L4,op[4]);
  not n3 (L3,op[3]);
  not n4 (L2,op[2]);
  not n5 (L1,op[1]);
  not n6 (L0,op[0]);

  and a1 (sig_r_format,L0,L1,L2,L3,L4,L5);
  and a2 (sig_lw,op[0],op[1],L2,L3,L4,op[5]);
  and a3 (sig_sw,op[0],op[1],L2,op[3],L4,op[5]);
  and a4 (sig_beq,L0,L1,op[2],L3,L4,L5);
  or a5 (ALUSrc, sig_lw, sig_sw);
  or a6 (RegWrite, sig_r_format, sig_lw);
  assign RegDst=sig_r_format;
  assign MemRead=sig_lw;
  assign MemtoReg=sig_lw;
  assign MemWrite=sig_sw;
  assign Branch=sig_beq;
  assign ALUOp[1]=sig_r_format;
  assign ALUOp[0]=sig_beq;
endmodule
