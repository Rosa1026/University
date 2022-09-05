module Vr_HW2_data_mem(ADDR, RW, WD, RD);
  input [31:0] ADDR;
  input RW; /* 0: Read, 1: Write */
  input [31:0] WD;
  output [31:0] RD;
  
  wire [9:0] word_addr;

  reg [31:0] mem_cell [0:1023];

  assign word_addr = ADDR[11:2];
  
  /* write */
  //always @ (posedge CLK)
  always @ (*)
  begin
    if (ADDR <1024) 
      if (RW)
        mem_cell[$unsigned(word_addr)] = WD;
  end

  /* read */
  assign RD = RW ? 32'hz : mem_cell[$unsigned(word_addr)];
  
endmodule
