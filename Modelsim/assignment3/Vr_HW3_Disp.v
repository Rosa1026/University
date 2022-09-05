module Vr_HW3_Disp (CLK, RST, LDISP, RDISP, SC_LDISP, SC_RDISP);
  /* I/O interface */
  input CLK, RST;
  input [0:12] LDISP, RDISP;
  input [0:12] SC_LDISP, SC_RDISP;

  parameter [0:1] ROCK=2'b00, SCISSORS=2'b01, PAPER=2'b10, INVALID=2'b11;
  integer cnt;
  reg [0:1] asign, bsign;
  integer ascore, bscore;


  /* scores */
  always @ (*)
  begin
    if(SC_LDISP == 13'b1111110000000) ascore = 0;
    else if (SC_LDISP == 13'b0110000000000) ascore = 1;
    else if (SC_LDISP == 13'b1101101000000) ascore = 2;
    else if (SC_LDISP == 13'b1111001000000) ascore = 3;
    else if (SC_LDISP == 13'b0110011000000) ascore = 4;
    else if (SC_LDISP == 13'b1011011000000) ascore = 5;
    else if (SC_LDISP == 13'b1011111000000) ascore = 6;
    else if (SC_LDISP == 13'b1110010000000) ascore = 7;
    else if (SC_LDISP == 13'b1111111000000) ascore = 8;
    else if (SC_LDISP == 13'b1111011000000) ascore = 9;
    else ascore = -1;

    if(SC_RDISP == 13'b1111110000000) bscore = 0;
    else if (SC_RDISP == 13'b0110000000000) bscore = 1;
    else if (SC_RDISP == 13'b1101101000000) bscore = 2;
    else if (SC_RDISP == 13'b1111001000000) bscore = 3;
    else if (SC_RDISP == 13'b0110011000000) bscore = 4;
    else if (SC_RDISP == 13'b1011011000000) bscore = 5;
    else if (SC_RDISP == 13'b1011111000000) bscore = 6;
    else if (SC_RDISP == 13'b1110010000000) bscore = 7;
    else if (SC_RDISP == 13'b1111111000000) bscore = 8;
    else if (SC_RDISP == 13'b1111011000000) bscore = 9;
    else bscore = -1;
  end

  /* displays */
  always @ (*)
  begin
    if (LDISP == 13'b0011101000111) asign = ROCK; // rock
    else if (LDISP == 13'b0011101101010) asign = SCISSORS; // scissors
    else if (LDISP == 13'b0111111010000) asign = PAPER; // paper
    else if (LDISP == 13'b0000000101101) asign = INVALID; // invalid
    else asign = INVALID;  

    if (RDISP == 13'b0011101000111) bsign = ROCK; // rock
    else if (RDISP == 13'b0011101101010) bsign = SCISSORS; // scissors
    else if (RDISP == 13'b0111111010000) bsign = PAPER; // paper
    else if (RDISP == 13'b0000000101101) bsign = INVALID; // invalid
    else bsign = INVALID; 
  end


  /* counter */
  always @ (posedge CLK)
  begin
    if (RST==1) cnt = 0;
    else begin 
      cnt = cnt + 1;
      if ((LDISP==13'b0000001000000) && (RDISP==13'b0000001000000))
         $display("clock tick %d: DRAW", cnt);
      else if ((LDISP==13'b0000001000000) && (RDISP==13'b0000000100100))
         $display("clock tick %d: A WINS THE GAME", cnt);
      else if ((LDISP==13'b0000000001001) && (RDISP==13'b0000001000000))
         $display("clock tick %d: B WINS THE GAME", cnt);
      else if ((asign==ROCK) && (bsign==ROCK))
         $display("clock tick %d: A:ROCK, B:ROCK, Score %d:%d", cnt, ascore, bscore );
      else if ((asign==ROCK) && (bsign==SCISSORS))
         $display("clock tick %d: A:ROCK, B:SCISSORS, Score %d:%d", cnt, ascore, bscore);
      else if ((asign==ROCK) && (bsign==PAPER))
         $display("clock tick %d: A:ROCK, B:PAPER, Score %d:%d", cnt, ascore, bscore);
      else if ((asign==ROCK) && (bsign==INVALID))
         $display("clock tick %d: A:ROCK, B:INVALID, Score %d:%d", cnt, ascore, bscore);
      else if ((asign==SCISSORS) && (bsign==ROCK))
         $display("clock tick %d: A:SCISSORS, B:ROCK, Score %d:%d", cnt, ascore, bscore);
      else if ((asign==SCISSORS) && (bsign==SCISSORS))
         $display("clock tick %d: A:SCISSORS, B:SCISSORS, Score %d:%d", cnt, ascore, bscore);
      else if ((asign==SCISSORS) && (bsign==PAPER))
         $display("clock tick %d: A:SCISSORS, B:PAPER, Score %d:%d", cnt, ascore, bscore);
      else if ((asign==SCISSORS) && (bsign==INVALID))
         $display("clock tick %d: A:SCISSORS, B:INVALID, Score %d:%d", cnt, ascore, bscore);
      else if ((asign==PAPER) && (bsign==ROCK))
         $display("clock tick %d: A:PAPER, B:ROCK, Score %d:%d", cnt, ascore, bscore);
      else if ((asign==PAPER) && (bsign==SCISSORS))
         $display("clock tick %d: A:PAPER, B:SCISSORS, Score %d:%d", cnt, ascore, bscore);
      else if ((asign==PAPER) && (bsign==PAPER))
         $display("clock tick %d: A:PAPER, B:PAPER, Score %d:%d", cnt, ascore, bscore);
      else if ((asign==PAPER) && (bsign==INVALID))
         $display("clock tick %d: A:PAPER, B:INVALID, Score %d:%d", cnt, ascore, bscore);
      else if ((asign==INVALID) && (bsign==ROCK))
         $display("clock tick %d: A:INVALID, B:ROCK, Score %d:%d", cnt, ascore, bscore);
      else if ((asign==INVALID) && (bsign==SCISSORS))
         $display("clock tick %d: A:INVALID, B:SCISSORS, Score %d:%d", cnt, ascore, bscore);
      else if ((asign==INVALID) && (bsign==PAPER))
         $display("clock tick %d: A:INVALID, B:PAPER, Score %d:%d", cnt, ascore, bscore);
      else if ((asign==INVALID) && (bsign==INVALID))
         $display("clock tick %d: A:INVALID, B:INVALID, Score %d:%d", cnt, ascore, bscore);
      else
         $display("clock tick %d: UNDEFINED, Score %d:%d", cnt, ascore, bscore);
    end // end if-else
  end // end always

  
endmodule
