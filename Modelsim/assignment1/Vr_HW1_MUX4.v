module Vr_HW1_MUX4(a, b, c, d, sel, data_out);
  input a, b, c, d;
  input [1:0]sel;
  output reg data_out;
  
  always @ (sel or a or b or c or d) begin
    case (sel)
      2'b00: data_out <= a;
      2'b01: data_out <= b;
      2'b10: data_out <= c;
      2'b11: data_out <= d;
    endcase
  end
endmodule
