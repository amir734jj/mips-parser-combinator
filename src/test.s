    .data
str:   .asciiz "Hello world!"
    .text
main:
    li $v0, 4   # print string
    la $a0, str
    syscall

    halt:
        li $v0, 10  # halt
        syscall