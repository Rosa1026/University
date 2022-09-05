module Vr_HW3_Score (CLK, RST, STAT, ASC, BSC);
  /* I/O interface */
  input CLK, RST;
  input [2:0] STAT;
  output [3:0] ASC, BSC;

  /* state declarations */
  parameter [2:0] S_INIT = 3'b000,
                  S_AATK = 3'b001,
                  S_BATK = 3'b010,
                  S_AW = 3'b011,
                  S_BW = 3'b100,
                  S_DRAW = 3'b101;

  reg [3:0] a_score = 4'b0000, b_score = 4'b0000;
  /* Implementation 3: Score Calculator */
  always @ (posedge CLK) begin
     if (RST) begin
	a_score <= 4'b0000;
	b_score <= 4'b0000;
     end

     else begin
      case (STAT)
	  3'b000, 3'b001, 3'b010, 3'b101 : begin a_score = a_score; b_score = b_score; end //INIT, AATK, BATK, DRAW   
	  3'b011 : a_score = a_score + 4'b0001; //AW
	  3'b100 : b_score = b_score + 4'b0001; //BW
      endcase
	
      if(a_score > 4'b0101) a_score = 4'b0000;

      if(b_score > 4'b0101) b_score = 4'b0000;
     end
  end

  assign ASC = a_score;
  assign BSC = b_score;
endmodule
