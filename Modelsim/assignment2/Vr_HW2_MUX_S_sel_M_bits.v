
module Vr_HW2_MUX_S_sel_M_bits(EN, SEL, A, Y);
  parameter S = 4;
  parameter M = 1;

  input EN;
  input [S-1:0] SEL;
  input [M-1:0] A [0:2**S-1];
  output [M-1:0] Y;
  reg [M-1:0]temp;

  integer i, j;
  /* Implementation 1: 
     You need to design a parameterizable active-high M-bit Multiplexer with S-bit SEL
     E.g., when S=4 and M=2, this MUX has 2^S inputs each of which is 2-bit. */ 
  always @ (*) begin
   if(EN==0)
    for(i=M-1;i>=0;i=i-1)
     temp[i]=0;
   else
    for(j=0;j<2**S;j=j+1)
     if(j=={SEL}) begin
      for(i=0;i<M;i=i+1)
        temp[i]=A[j][i];
     end
  end

  assign Y=temp;

endmodule
