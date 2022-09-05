module Vr_HW1_mode10(I, O);
  input [3:0] I;
  output reg O;
  
  always@(I)begin
   if((I%3)|(I==0))
    O=0;
   else
    O=1;
  end
endmodule
