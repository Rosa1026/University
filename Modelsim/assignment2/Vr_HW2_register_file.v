module Vr_HW2_register_file(CLK, RST, RR1, RR2, WR, WD, WE, RD1, RD2);
  input CLK;
  input RST;
  input [4:0] RR1;
  input [4:0] RR2;
  input [4:0] WR;
  input [31:0] WD;
  input WE;
  output [31:0] RD1;
  output [31:0] RD2;
  


  wire [31:0] pre_register_we;
  wire [31:0] register_we;  
  reg [31:0] register_data_in[0:31];
  wire [31:0] register_data_out[0:31];

  genvar i;

  /* register write enable signals: use binary decoder */
  /* Implementation 3-1: Decoder for write enable signals */
  Vr_HW2_N_to_S_dec # (.N(5), .S(32)) u_D1 (WR,WE,pre_register_we);
  for (i=0; i<=31;i=i+1)
    and A1(register_we[i], pre_register_we[i], WE);
  /* register data_in */
  for(i=0;i<=31;i=i+1) assign register_data_in[i] = WD;

  /* Register instantiations: 32 x 32-bit Registers */
  for(i=0;i<=31;i=i+1) 
    Vr_HW2_reg_N #(.N(32), .INIT_VAL(i)) u_regs (CLK, RST, register_we[i],  register_data_in[i],  register_data_out[i]);
  /* The above line is equivalent to the following 32 lines: 
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(0)) u_reg0  (CLK, RST, register_we[0],  register_data_in[0],  register_data_out[0]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(1)) u_reg1  (CLK, RST, register_we[1],  register_data_in[1],  register_data_out[1]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(2)) u_reg2  (CLK, RST, register_we[2],  register_data_in[2],  register_data_out[2]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(3)) u_reg3  (CLK, RST, register_we[3],  register_data_in[3],  register_data_out[3]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(4)) u_reg4  (CLK, RST, register_we[4],  register_data_in[4],  register_data_out[4]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(5)) u_reg5  (CLK, RST, register_we[5],  register_data_in[5],  register_data_out[5]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(6)) u_reg6  (CLK, RST, register_we[6],  register_data_in[6],  register_data_out[6]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(7)) u_reg7  (CLK, RST, register_we[7],  register_data_in[7],  register_data_out[7]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(8)) u_reg8  (CLK, RST, register_we[8],  register_data_in[8],  register_data_out[8]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(9)) u_reg9  (CLK, RST, register_we[9],  register_data_in[9],  register_data_out[9]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(10)) u_reg10 (CLK, RST, register_we[10], register_data_in[10], register_data_out[10]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(11)) u_reg11 (CLK, RST, register_we[11], register_data_in[11], register_data_out[11]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(12)) u_reg12 (CLK, RST, register_we[12], register_data_in[12], register_data_out[12]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(13)) u_reg13 (CLK, RST, register_we[13], register_data_in[13], register_data_out[13]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(14)) u_reg14 (CLK, RST, register_we[14], register_data_in[14], register_data_out[14]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(15)) u_reg15 (CLK, RST, register_we[15], register_data_in[15], register_data_out[15]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(16)) u_reg16 (CLK, RST, register_we[16], register_data_in[16], register_data_out[16]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(17)) u_reg17 (CLK, RST, register_we[17], register_data_in[17], register_data_out[17]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(18)) u_reg18 (CLK, RST, register_we[18], register_data_in[18], register_data_out[18]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(19)) u_reg19 (CLK, RST, register_we[19], register_data_in[19], register_data_out[19]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(20)) u_reg20 (CLK, RST, register_we[20], register_data_in[20], register_data_out[20]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(21)) u_reg21 (CLK, RST, register_we[21], register_data_in[21], register_data_out[21]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(22)) u_reg22 (CLK, RST, register_we[22], register_data_in[22], register_data_out[22]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(23)) u_reg23 (CLK, RST, register_we[23], register_data_in[23], register_data_out[23]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(24)) u_reg24 (CLK, RST, register_we[24], register_data_in[24], register_data_out[24]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(25)) u_reg25 (CLK, RST, register_we[25], register_data_in[25], register_data_out[25]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(26)) u_reg26 (CLK, RST, register_we[26], register_data_in[26], register_data_out[26]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(27)) u_reg27 (CLK, RST, register_we[27], register_data_in[27], register_data_out[27]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(28)) u_reg28 (CLK, RST, register_we[28], register_data_in[28], register_data_out[28]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(29)) u_reg29 (CLK, RST, register_we[29], register_data_in[29], register_data_out[29]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(30)) u_reg30 (CLK, RST, register_we[30], register_data_in[30], register_data_out[30]);
  Vr_HW2_reg_N #(.N(32), .INIT_VAL(31)) u_reg31 (CLK, RST, register_we[31], register_data_in[31], register_data_out[31]);*/


  /* MUX: data_out -> RD1/RD2 */
  /* Implementation 3-2: Two MUXes for two read register values */
  Vr_HW2_MUX_S_sel_M_bits #(.S(5), .M(32)) u_M1(1'b1,RR1,register_data_out,RD1);
  Vr_HW2_MUX_S_sel_M_bits #(.S(5), .M(32)) u_M2(1'b1,RR2,register_data_out,RD2);
endmodule
