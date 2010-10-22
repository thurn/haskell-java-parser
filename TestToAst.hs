module TestToAst where

import Java
import ToAst
import JavaAst
import Test.HUnit
 
exc fn parser text = fn (getRun parser text)

tests = test [ 
  "int literal 1" ~: (IntLiteral 4) ~=?
      exc simplifyLiteral literal "4",
  "int literal 2" ~: (IntLiteral 0) ~=?
      exc simplifyLiteral literal "0",
  "fp literal 1" ~: (FpLiteral 4.0) ~=?
      exc simplifyLiteral literal "4.0",
  "fp literal 2" ~: (FpLiteral 0.0) ~=?
      exc simplifyLiteral literal "0.0",
  "fp literal 3" ~: (FpLiteral 1e4) ~=?
      exc simplifyLiteral literal "1e4",
  "fp literal 4" ~: (FpLiteral 1e4) ~=?
      exc simplifyLiteral literal "1E4",
  "fp literal 5" ~: (FpLiteral 1e-4) ~=?
      exc simplifyLiteral literal "1E-4",
  "char literal 1" ~: (CharLiteral 'c') ~=?
      exc simplifyLiteral literal "'c'",
  "char literal 2" ~: (CharLiteral '\n') ~=?
      exc simplifyLiteral literal "'\\n'",
  "string literal 1" ~: (StringLiteral "") ~=?
      exc simplifyLiteral literal "\"\"",
  "string literal 2" ~: (StringLiteral "foo") ~=?
      exc simplifyLiteral literal "\"foo\"",
  "string literal 3" ~: (StringLiteral "f\no") ~=?
      exc simplifyLiteral literal "\"f\\no\"",
  "bool literal 1" ~: (BoolLiteral True) ~=?
      exc simplifyLiteral literal "true",
  "bool literal 2" ~: (BoolLiteral False) ~=?
      exc simplifyLiteral literal "false",
  "bool literal 2" ~: (NullLiteral) ~=?
      exc simplifyLiteral literal "null",
  "identifier 1" ~: (Identifier "foo") ~=?
      exc simplifyIdentifier identifier "foo",
  "identifier 2" ~: (Identifier "foo.bar") ~=?
      exc simplifyQualifiedIdentifier qualifiedIdentifier "foo.bar",
  "prefixop 1" ~: ("++") ~=?
      exc simplifyPrefixOp prefixOp "++",
  "prefixop 2" ~: ("+") ~=?
      exc simplifyPrefixOp prefixOp "+",
  "expression3a 1" ~:
      PrefixExpression "+" (LiteralExpression (IntLiteral 1)) ~=?
      exc simplifyExpression3A expression3 "+1",
  "expression3a 2" ~:
      PrefixExpression "++" (LiteralExpression (IntLiteral 0)) ~=?
      exc simplifyExpression3A expression3 "++0",
  "expression3c 1" ~:
      LiteralExpression (IntLiteral 1) ~=?
      exc simplifyExpression3C expression3 "1",
  "primary 1" ~:
      LiteralExpression (IntLiteral 1) ~=?
      exc simplifyPrimary primary "1",
  "expression2 1" ~:
      InfixExpression (LiteralExpression (IntLiteral 1)) "+"
        (LiteralExpression (IntLiteral 1)) ~=?
      exc simplifyExpression2 expression2 "1+1",
  
  
  
  
  
  "true?" ~: True ~=? True
  ]
        
main = runTestTT tests
