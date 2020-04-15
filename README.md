# mips-parser-combinator
Very basic MIPS parser combinator in Scala + PrettyPrinter.

This project used Scala's built-in parser combinator to parse a subset of MIPS and indent it.

```Assembly
	.data
str:	.asciiz "Hello world!"
foo:	.word 5
	.text
main:
  li $v0, 4
  la $a0, str
  syscall

  li $v0, 1
  la $t0, foo
  lw $t0, 0($t0)
  move $a0, $t0
  syscall

  halt:
    li $v0, 10
    syscall
```

