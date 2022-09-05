module Vr_HW2_inst_mem(ADDR, INST);
  input [31:0] ADDR;
  output reg [31:0] INST;

  always @ (ADDR)
  begin
    case(ADDR)
        /* at the time of reset, registers are initialized with their index
           i.e., $r0 = 0, $r1 = 1, $r2 = 2, ..., $r31 = 31 */
        0: INST = 32'hAC000064; /* mem[100] <- 0; sw $r0, 100($r0); 101011 base:00000, rt:00000, offset:0000000001100100 */
        4: INST = 32'hAC800064; /* mem[104] <- 0; sw $r0, 100($r4); 101011 base:00100, rt:00000, offset:0000000001100100 */
        8: INST = 32'h8C070064; /* i(r7) <- mem[100]; lw $r7, 100($r0); 100011 base:00000, rt:00111, offset:0000000001100100 */
       12: INST = 32'h8C080068; /* sum(r8) <- mem[104]; lw $r8, 104($r0); 100011 base:00000, rt:01000, offset:0000000001101000 */
       16: INST = 32'h00E13820; /* i($r7) = i($r7) + 1($r1); add $r7, $r7, $r1; 000000 rs:00111, rt:00001, rd:00111, shamt:00000, funct:100000  */
       20: INST = 32'h01074020; /* sum($r8) = sum($r8) + i($r7); add $r8, $r8, $r7; 000000 rs:01000, rt:00111, rd:01000, shamt:00000, funct:100000  */ 
       24: INST = 32'h00674822; /* sub $r9, $r3, $r7; 000000 rs:00011, rt:00111, rd:01001, shamt:00000, funct:100010 */
       28: INST = 32'h1122FFFC; /* beq $r9, $r2, -4; 000100 rs:01001, rt:00010, offset:1111111111111100 */
       32: INST = 32'h1121FFFB; /* beq $r9, $r1, -5; 000100 rs:01001, rt:00001, offset:1111111111111011 */
       36: INST = 32'h1120FFFA; /* beq $r9, $r0, -6; 000100 rs:01001, rt:00000, offset:1111111111111010 */
       40: INST = 32'hAC08006C; /* mem[108] <- r8; sw $r8, 108($r0); 101011 base:00000, rt:01000, offset:0000000001101100 */
       44: INST = 32'h8C0A006C; /* return($r10) <- mem[108]; lw $r10, 108($r0); 100011 base:00000, rt:01010, offset:0000000001101100 */
       48: INST = 32'h1000FFF3; /* beq $r0, $r0, -13; 000100 rs:00000, rt:00000, offset:1111111111110011 */
      default: INST = 32'h00000000;
    endcase
  end
endmodule
