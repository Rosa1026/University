`timescale 1 ns / 100 ps
module Vr_HW3_tb ();

  reg sig_clk, sig_rst;
  reg [0:1] sig_ain, sig_bin;
  wire [0:12] sig_ldisp, sig_rdisp, sig_sc_ldisp, sig_sc_rdisp;

  parameter [0:1] ROCK=2'b00, SCISSORS=2'b01, PAPER=2'b10, INVALID=2'b11;


  /* main top-level file */
  Vr_HW3 uut (.CLK(sig_clk), .RST(sig_rst), .AIN(sig_ain), .BIN(sig_bin), .LDISP(sig_ldisp), .RDISP(sig_rdisp), .SC_LDISP(sig_sc_ldisp), .SC_RDISP(sig_sc_rdisp));

  /* display module */
  Vr_HW3_Disp u_disp (.CLK(sig_clk), .RST(sig_rst), .LDISP(sig_ldisp), .RDISP(sig_rdisp), .SC_LDISP(sig_sc_ldisp), .SC_RDISP(sig_sc_rdisp) );

  /* clock/reset generation */

  always begin
    sig_clk = 1; #10;
    sig_clk = 0; #10;
  end

  always begin
    sig_rst = 0; #15;
    sig_rst = 1; #20;
    sig_rst = 0; #10000;
  end

  /* game playing scenarios */
  always begin
    sig_ain = INVALID; sig_bin = INVALID; #35;
    sig_ain = ROCK; sig_bin = ROCK; #20;
    sig_ain = ROCK; sig_bin = SCISSORS; #20;
    sig_ain = PAPER; sig_bin = ROCK; #20;
    sig_ain = PAPER; sig_bin = PAPER; #20; // A WIN (RULE 1)
    sig_ain = INVALID; sig_bin = INVALID; #20; //  A is expected to win the game here


    sig_ain = INVALID; sig_bin = ROCK; #20; // B is expected to win the game (RULE 3)
    sig_ain = INVALID; sig_bin = INVALID; #20; 

    sig_ain = INVALID; sig_bin = INVALID; #20; // They are expected to draw (RULE 2)
    sig_ain = INVALID; sig_bin = INVALID; #20; 


    sig_ain = PAPER; sig_bin = SCISSORS; #20;
    sig_ain = ROCK; sig_bin = SCISSORS; #20;
    sig_ain = ROCK; sig_bin = PAPER; #20;
    sig_ain = ROCK; sig_bin = SCISSORS; #20; 
    sig_ain = INVALID; sig_bin = INVALID; #20; //DRAW

    /* Implementation 6: complete the test bench to check more scenarios */
    sig_ain = PAPER; sig_bin = PAPER; #20; 
    sig_ain = PAPER; sig_bin = PAPER; #20;
    sig_ain = PAPER; sig_bin = PAPER; #20; //ATK X - SAME 3 -> INIT

    sig_ain = PAPER; sig_bin = SCISSORS; #20; 
    sig_ain = PAPER; sig_bin = SCISSORS; #20;
    sig_ain = PAPER; sig_bin = ROCK; #20; //BATK 2, AATK 1 - A SAME 3 -> B WIN (RULE 4)

    sig_ain = PAPER; sig_bin = ROCK; #20; 
    sig_ain = PAPER; sig_bin = ROCK; #20;
    sig_ain = SCISSORS; sig_bin = ROCK; #20; //AATK 2, BATK 1 - B SAME 3 -> A WIN (RULE 4)

    sig_ain = PAPER; sig_bin = ROCK; #20; 
    sig_ain = SCISSORS; sig_bin = PAPER; #20;
    sig_ain = SCISSORS; sig_bin = PAPER; #20;
    sig_ain = SCISSORS; sig_bin = PAPER; #20; //AATK 1 - A, B SAME 3 -> DRAW (RULE 5)

  end
  
endmodule
