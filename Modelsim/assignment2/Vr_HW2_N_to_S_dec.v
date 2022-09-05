module Vr_HW2_N_to_S_dec(A, EN, Y);
  parameter N=3, S=8;
  input [N-1:0] A;
  input EN;
  output reg[S-1:0] Y;
  
  integer i;
  /* Implementation 2: 
     You need to design a parameterizable N-to-S binary decoder.
     E.g., when N=3 and S=8, it is a 3-to-8 binary decoder. */ 
  always @ (A, EN) begin
   for (i=0;i<=S-1;i=i+1)
    Y[i]=0; /*default*/
   if (EN==0) begin/*EN==0*/
    for (i=S-1;i>=0;i=i-1)
     Y[i]=0;
   end
   else begin/*EN==1*/
    for (i=0;i<=S-1;i=i+1) begin
     if (i=={A}) 
      Y[i]=1;
    end
   end
  end
endmodule
