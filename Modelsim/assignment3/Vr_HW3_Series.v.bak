module Vr_HW3_Series (CLK, RST, V, S);
  /* I/O interface */
  input CLK, RST;
  input [0:1] V;
  output S;

  /* parameterizable length threshold */
  parameter integer len = 2;

  integer cnt = 32'b0;
  reg [0:1] prev_v;
  reg s = 0;


  /* Implementation 2: Series detector */

  always @ (posedge CLK or posedge RST) begin
    if (RST) begin
	prev_v <= 2'bxx;
	cnt <= 32'b0;
	s <= 0;
    end

    else begin
	 if (V == 2'b11) begin s = 0; cnt <= 32'b0; prev_v <= 2'bxx; end

	 else begin
	  if (cnt == 32'b010) begin s = 0; cnt <= 32'b0; prev_v <= 2'bxx; end

	  else begin
	   if(cnt == 32'b01) begin
	    if (V == prev_v) begin cnt <= cnt + 32'b01; prev_v <= V; s = 1; end
	    else begin prev_v <= V; cnt <= cnt - 32'b01; s = 0; end
	   end

	   else begin
	    if (V == prev_v) begin cnt <= cnt + 32'b01; prev_v <= V; s = 0; end
	    else begin prev_v <= V; cnt <= cnt; s = 0; end
	   end
	end
	end
     end
   end

  assign S = s;
endmodule
