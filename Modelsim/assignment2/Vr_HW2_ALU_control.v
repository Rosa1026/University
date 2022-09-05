module Vr_HW2_ALU_control(funct, ALUOp, ALUCtrl);

  input [5:0] funct;
  input [1:0] ALUOp;
  output reg [2:0] ALUCtrl;
  
  /* Implementation 11: fill in your implmentation here */
  always @ (ALUOp, funct) begin
   case(ALUOp)
    2'b00:ALUCtrl=3'b010;
    2'b01:ALUCtrl=3'b110;
    2'b10:if(funct==6'b100000) ALUCtrl=3'b010;
          else if(funct==6'b100010) ALUCtrl=3'b110;
   endcase
  end
endmodule
