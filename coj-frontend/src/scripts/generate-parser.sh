#!/bin/bash

# 定义 ANTLR jar 文件路径
antlr4='java -jar ../monaco/antlr/antlr-4.13.1-complete.jar'

# 定义输入目录和输出目录
input_dir='../monaco/antlr'
output_dir='../monaco/'

mkdir -p $output_dir

# 生成 Lexer
$antlr4 -Dlanguage=JavaScript -o $output_dir $input_dir/Java20Lexer.g4

# 生成 Parser，确保能找到生成的 .tokens 文件
$antlr4 -Dlanguage=JavaScript -visitor -lib $output_dir -o $output_dir $input_dir/Java20Parser.g4
