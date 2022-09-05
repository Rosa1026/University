module Vr_HW2(CLK, RST);

  input CLK, RST;


  wire [31:0] sig_pc_in, sig_pc_out; /* input/output of PC */
  wire [31:0] sig_pc_plus_4;
  wire sig_pc_plus_4_cout;

  wire [31:0] sig_branch_addr;
  wire sig_branch_cout;
  wire [31:0] sig_extended_offset;
 
  wire branch_and; /*andgate output*/
  wire [31:0] inst; /* instruction */

  /* for register file */
  wire [4:0] sig_rr1, sig_rr2, sig_wr;
  wire [4:0] sig_wr_mux_in [0:1]; 
  wire [31:0] sig_rf_write_data;
  //wire sig_rf_we;
  wire [31:0] sig_rf_rd1, sig_rf_rd2;

  wire [31:0] sig_alu_a, sig_alu_b, sig_alu_out;

  wire sig_alu_zero;

  wire [31:0] sig_data_mem_out;

  /* control signals */
  wire sig_reg_dst, sig_branch, sig_mem_read, sig_mem_to_reg, sig_mem_write, sig_alu_src, sig_reg_write;
  wire [1:0] sig_alu_op;
  wire [2:0] sig_alu_ctrl;

  wire [31:0] sig_pc_mux_in [0:1]; 
  wire [31:0] sig_alu_b_mux_in [0:1]; 
  wire [31:0] sig_rf_wd_mux_in [0:1]; 

  /* program counter */
  Vr_HW2_reg_N #(.N(32)) u_pc (CLK, RST, 1'b1, sig_pc_in, sig_pc_out);  

  /* PC + 4 */
  /* Implementation 8-1: PC+4 (instantiate the ripple adder implemented in Vr_HW2_ripple_adder_M_bits.v */
  Vr_HW2_ripple_adder_M_bits #(.M(32)) u_ad1 (sig_pc_out, 32'b0100, 1'b0, sig_pc_plus_4, sig_pc_plus_4_cout);
  

  /* instruction memory */
  Vr_HW2_inst_mem u_inst_m (sig_pc_out, inst);  


  /* Implementation 4-1: top-level MUX 1 (given as an example; you don't need to modify) */
  assign sig_wr_mux_in[0] = sig_rr2;
  assign sig_wr_mux_in[1] = inst[15:11];
  Vr_HW2_MUX_S_sel_M_bits #(.S(1),.M(5)) u_mux_wr (1'b1, sig_reg_dst, sig_wr_mux_in, sig_wr); /* input MUX to WR */

  /* register file */
  assign sig_rr1 = inst[25:21];
  assign sig_rr2 = inst[20:16];
  Vr_HW2_register_file u_rf (CLK, RST, sig_rr1,sig_rr2,sig_wr, sig_rf_write_data, sig_reg_write, sig_rf_rd1, sig_rf_rd2);


  
  
  /* control */
  Vr_HW2_control u_control (inst[31:26], sig_reg_dst, sig_branch, sig_mem_read, sig_mem_to_reg, sig_alu_op, sig_mem_write, sig_alu_src, sig_reg_write );
  Vr_HW2_ALU_control u_alu_control (inst[5:0],sig_alu_op, sig_alu_ctrl);


  /* Implementation 4-2: top-level MUX 2 */
  assign sig_alu_b_mux_in[0]=sig_rf_rd2;
  assign sig_alu_b_mux_in[1]=sig_extended_offset;
  Vr_HW2_MUX_S_sel_M_bits #(.S(1), .M(32)) u_mux_2(1'b1,sig_alu_src,sig_alu_b_mux_in,sig_alu_b);

  /* main ALU */
  /* Implementation 8-2: main ALU (instantiate the ALU implemented in Vr_HW2_ALU.v) */
  assign sig_alu_a=sig_rf_rd1;
  Vr_HW2_ALU u_alu_main (sig_alu_a,sig_alu_b,sig_alu_ctrl,sig_alu_out,sig_alu_zero);



  /* data memory */
  Vr_HW2_data_mem u_data_m (sig_alu_out, sig_mem_write, sig_rf_rd2, sig_data_mem_out);


  /* Implementation 4-4: top-level MUX 4 */
  assign sig_rf_wd_mux_in[0]=sig_alu_out;
  assign sig_rf_wd_mux_in[1]=sig_data_mem_out;
  Vr_HW2_MUX_S_sel_M_bits #(.S(1),.M(32)) u_mux_4 (~sig_mem_write, sig_mem_to_reg,sig_rf_wd_mux_in,sig_rf_write_data);



  /* PC MUX */
  /* Implementation 4-3: top-level MUX 3 */
  assign sig_pc_mux_in[0] = sig_pc_plus_4;
  assign sig_pc_mux_in[1] = sig_branch_addr;
  and u_and (branch_and, sig_alu_zero, sig_branch);
  Vr_HW2_MUX_S_sel_M_bits #(.S(1), .M(32)) u_mux_pc (1'b1,branch_and,sig_pc_mux_in,sig_pc_in);




  /* Implementation 9: sign extension and shift (input for the calculation of branch address) */
  assign sig_extended_offset=$signed(inst[15:0]);



  /* branch address */
  /* Implementation 8-3: adder for branch address (instantiate the ripple adder implemented in Vr_HW2_ripple_adder_M_bits.v */
  Vr_HW2_ripple_adder_M_bits #(.M(32)) u_add2 (sig_pc_plus_4, sig_extended_offset<<<2, sig_pc_plus_4_cout, sig_branch_addr, sig_branch_cout);
  

endmodule
