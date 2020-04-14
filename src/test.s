.data
name: .asciiz "amir"
name: .word 4
.text
main:
li $v0, 4
la $a0, amir
syscall