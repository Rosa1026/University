module Vr_HW3_RSP (CLK, RST, AIN, BIN, AS, BS, STAT);
  /* I/O interface */
  input CLK, RST;
  input [0:1] AIN, BIN;
  input AS, BS;
  output [2:0] STAT;

  reg [2:0] S_reg, S_next; /* state memory and next state signals */
  reg [0:1] a_prev, b_prev; /* previous a, b values */
  reg [2:0] S_STAT;

  /* state declarations */
  parameter [2:0] S_INIT = 3'b000,
                  S_AATK = 3'b001,
                  S_BATK = 3'b010,
                  S_AW = 3'b011,
                  S_BW = 3'b100,
                  S_DRAW = 3'b101;

  /* RSP */
  parameter [0:1] ROCK=2'b00, SCISSORS=2'b01, PAPER=2'b10, INVALID=2'b11;


  /* Implementation 1: main FSM */
  always @ (posedge CLK) begin//State memory
   if (RST) begin S_reg <= S_INIT; a_prev <= 2'bxx; b_prev <= 2'bxx; end
   else begin 
	if (S_next == S_AW || S_next == S_BW) begin 
	 S_reg <= S_next; a_prev <= 2'bxx; b_prev <= 2'bxx; 
	end
	else begin 
	 S_reg <= S_next; a_prev <= AIN; b_prev <= BIN; 
	end
   end
  end

  always @ (S_reg, AIN, BIN, AS, BS) begin //Next-state logic
    case (S_reg)
	S_INIT : if (AIN == INVALID && BIN != INVALID) S_next = S_BW;
               else if (AIN != INVALID && BIN == INVALID) S_next = S_AW;
               else if (AIN == INVALID && BIN == INVALID) S_next = S_DRAW;
	       else if (AIN != INVALID && BIN != INVALID) begin
	       if (AIN == BIN) S_next = S_INIT;
               if (AIN == ROCK && BIN == SCISSORS) S_next = S_AATK;
               else if (AIN == SCISSORS && BIN == PAPER) S_next = S_AATK;
               else if (AIN == PAPER && BIN == ROCK) S_next = S_AATK;
               else if (AIN == ROCK && BIN == PAPER) S_next = S_BATK;
               else if (AIN == SCISSORS && BIN == ROCK) S_next = S_BATK;
               else if (AIN == PAPER && BIN == SCISSORS) S_next = S_BATK;
	       end

	S_AATK : if (AS) begin
		  if (BS) begin
		   if (AIN == a_prev && BIN == b_prev)
		    S_next = S_DRAW;

		   else if (AIN == a_prev && BIN != b_prev)
		    S_next = S_BW;

		   else if (AIN != a_prev && BIN == b_prev)
		    S_next = S_AW;
		  end
 		 
		 else begin
		  if (AIN == a_prev) S_next = S_BW;
		 end
		end

		else if (BS) begin
		 if (BIN == b_prev) S_next = S_AW;
		end

		else begin
		 if (AIN == INVALID && BIN != INVALID) S_next = S_BW;
		 else if (AIN != INVALID && BIN == INVALID) S_next = S_AW;
		 else if (AIN == INVALID && BIN == INVALID) S_next = S_DRAW;
		 else if (AIN != INVALID && BIN != INVALID) begin
		   if (AIN == BIN) S_next = S_AW;

                   else if (AIN == ROCK && BIN == SCISSORS) S_next = S_AATK;

                   else if (AIN == SCISSORS && BIN == PAPER) S_next = S_AATK;

                   else if (AIN == PAPER && BIN == ROCK) S_next = S_AATK;

                   else if (AIN == ROCK && BIN == PAPER) S_next = S_BATK;

                   else if (AIN == SCISSORS && BIN == ROCK) S_next = S_BATK;

                   else if (AIN == PAPER && BIN == SCISSORS) S_next = S_BATK;
		 end
		end

	S_BATK : if (AS) begin
		  if (BS) begin
		   if (AIN == a_prev && BIN == b_prev)
		    S_next = S_DRAW;
		   else if (AIN == a_prev && BIN != b_prev)
		    S_next = S_BW;
		   else if (AIN != a_prev && BIN == b_prev)
		    S_next = S_AW;
		  end

		  else begin
		   if (AIN == a_prev)
		    S_next = S_BW;
		  end
		 end

		else if (BS) begin
		if (BIN == b_prev)
		 S_next = S_AW;
		end

		else begin
		 if (AIN == INVALID && BIN != INVALID) S_next = S_BW;
		 else if (AIN != INVALID && BIN == INVALID) S_next = S_AW;
		 else if (AIN == INVALID && BIN == INVALID) S_next = S_DRAW;
		 else if (AIN != INVALID && BIN != INVALID) begin
		  if (AIN == BIN) S_next = S_BW;

                  else if (AIN == ROCK && BIN == SCISSORS) S_next = S_AATK;

                  else if (AIN == SCISSORS && BIN == PAPER) S_next = S_AATK;

                  else if (AIN == PAPER && BIN == ROCK) S_next = S_AATK;

                  else if (AIN == ROCK && BIN == PAPER) S_next = S_BATK;

                  else if (AIN == SCISSORS && BIN == ROCK) S_next = S_BATK;

                  else if (AIN == PAPER && BIN == SCISSORS) S_next = S_BATK;
		end
	       end

	S_AW : S_next = S_INIT;

	S_BW : S_next = S_INIT;


	S_DRAW : if (AIN == INVALID && BIN != INVALID) S_next = S_BW;
               else if (AIN != INVALID && BIN == INVALID) S_next = S_AW;
               else if (AIN == INVALID && BIN == INVALID) S_next = S_INIT;
	       else if (AIN != INVALID && BIN != INVALID) S_next = S_INIT;
   endcase
  end

  always @ (S_reg) begin //Output logic
    case (S_reg)
	S_INIT : S_STAT = 3'b000;
	S_AATK : S_STAT = 3'b001;
	S_BATK : S_STAT = 3'b010;
	S_AW : S_STAT = 3'b011;
	S_BW : S_STAT = 3'b100;
	S_DRAW : S_STAT = 3'b101;
	default : S_STAT = 3'b000;
    endcase
  end

  assign STAT = S_STAT;
endmodule
