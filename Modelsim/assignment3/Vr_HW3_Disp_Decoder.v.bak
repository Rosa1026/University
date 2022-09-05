module Vr_HW3_Disp_Decoder (AIN, BIN, STAT, ASC, BSC, LDISP, RDISP, SC_LDISP, SC_RDISP);
  /* I/O interface */
  input [0:1] AIN, BIN;
  input [2:0] STAT;
  input [3:0] ASC, BSC;
  output reg [0:12] LDISP, RDISP;
  output reg [0:12] SC_LDISP, SC_RDISP;

  /* state declarations */
  parameter [2:0] S_INIT = 3'b000,
                  S_AATK = 3'b001,
                  S_BATK = 3'b010,
                  S_AW = 3'b011,
                  S_BW = 3'b100,
                  S_DRAW = 3'b101;
  parameter [0:1] ROCK=2'b00, SCISSORS=2'b01, PAPER=2'b10, INVALID=2'b11;

  function [0:12] number_dec;
    input [3:0] in_val;
    if(in_val==4'b0000) number_dec = 13'b1111110000000; // 0
    else if(in_val==4'b0001) number_dec = 13'b0110000000000; // 1
    else if(in_val==4'b0010) number_dec = 13'b1101101000000; // 2
    else if(in_val==4'b0011) number_dec = 13'b1111001000000; // 3
    else if(in_val==4'b0100) number_dec = 13'b0110011000000; // 4
    else if(in_val==4'b0101) number_dec = 13'b1011011000000; // 5
    else if(in_val==4'b0110) number_dec = 13'b1011111000000; // 6
    else if(in_val==4'b0111) number_dec = 13'b1110010000000; // 7
    else if(in_val==4'b1000) number_dec = 13'b1111111000000; // 8
    else if(in_val==4'b1001) number_dec = 13'b1111011000000; // 9
    else number_dec = 13'b0000000000000;
  endfunction

  function [0:12] sign_dec;
    input [0:1] in_val;
    if(in_val==2'b00) sign_dec = 13'b0011101000111; // rock 
    else if(in_val==2'b01) sign_dec = 13'b0011101101010; // scissors
    else if(in_val==2'b10) sign_dec = 13'b0111111010000; // paper
    else if(in_val==2'b11) sign_dec = 13'b0000000101101; // invalid
    else sign_dec = 13'b0000000000000; 
  endfunction

  function isRock;
    input [0:1] in_val;
    if (in_val == ROCK) isRock = 1'b1;
    else isRock = 1'b0;
  endfunction

  function isScissors;
    input [0:1] in_val;
    if (in_val == SCISSORS) isScissors = 1'b1;
    else isScissors = 1'b0;
  endfunction

  function isPaper;
    input [0:1] in_val;
    if (in_val == PAPER) isPaper = 1'b1;
    else isPaper = 1'b0;
  endfunction

  function isInvalid;
    input [0:1] in_val;
    if (in_val == INVALID) isInvalid = 1'b1;
    else isInvalid = 1'b0;
  endfunction

  function RCPAWin;
    input [0:1] Aval; input [0:1] Bval;
    if (isRock(Aval) && isScissors(Bval)) RCPAWin = 1'b1;
    else if (isScissors(Aval) && isPaper(Bval)) RCPAWin = 1'b1;
    else if (isPaper(Aval) && isRock(Bval)) RCPAWin = 1'b1;
    else RCPAWin = 1'b0;
  endfunction

  function RCPBWin;
    input [0:1] Aval; input [0:1] Bval;
    if (isRock(Bval) && isScissors(Aval)) RCPBWin = 1'b1;
    else if (isScissors(Bval) && isPaper(Aval)) RCPBWin = 1'b1;
    else if (isPaper(Bval) && isRock(Aval)) RCPBWin = 1'b1;
    else RCPBWin = 1'b0;
  endfunction

  /* Implementation 4: display decoder */
  always @ (AIN or BIN or STAT or ASC or BSC) begin
   if (STAT != 3'b011 && STAT != 3'b100 && STAT != 3'b101) begin
    LDISP = sign_dec(AIN);
    RDISP = sign_dec(BIN);
   end

   else if (STAT == 3'b011) begin//AW
    LDISP = 13'b0000001000000;
    RDISP = 13'b0000000100100;
   end

   else if (STAT == 3'b100) begin//BW
    LDISP = 13'b0000000001001;
    RDISP = 13'b0000001000000;
   end

   else if (STAT == 3'b101) begin//DRAW
    LDISP = 13'b0000001000000;
    RDISP = 13'b0000001000000;
   end

   SC_LDISP = number_dec(ASC);
   SC_RDISP = number_dec(BSC);

  end
endmodule
