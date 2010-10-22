module ToAst where

import JavaParseTree as P
import JavaAst as A
import Data.List

simplifyCompilationUnit (P.CompilationUnit ptn) = A.CompilationUnit {
    package = Nothing,
    imports = [],
    classDeclarations = [
      A.ClassDeclaration {
         className = "Empty",
         classModifiers = [],
         superClass = Nothing,
         implementedInterfaces = [],
         classBody = A.ClassBody {
           staticBlocks = [],
           blocks = [],
           methodDeclarations = [],
           variableDeclarations = []
           }
         }
      ],
    interfaceDeclarations = []
    } :: A.CompilationUnit
         
simplifyLiteral (P.Literal ptn) =
    case ptn of
      (P.IntLiteral val) -> A.IntLiteral val
      (P.FPLiteral val) -> A.FpLiteral val
      (P.CharLiteral val) -> A.CharLiteral val
      (P.StringLiteral val) -> A.StringLiteral val
      (P.BoolLiteral val) -> A.BoolLiteral val
      (P.NullLiteral) -> A.NullLiteral
      
simplifyIdentifier (P.Identifier iden) = A.Identifier iden

simplifyQualifiedIdentifier (P.QualifiedIdentifier xs) = A.Identifier $
                                        intercalate "." (map extract xs)
                                        where extract (P.Identifier x) = x

simplifyPrefixOp (P.PrefixOp op) = op

simplifyExpression3A (P.Expression3A lst) =  
  case lst of
    [prefix@(P.PrefixOp _), exp@(P.Expression3C _)] ->
      A.PrefixExpression (simplifyPrefixOp prefix) (simplifyExpression3C exp)
      
simplifyExpression3C (P.Expression3C lst) =
  case lst of
    [primary@(P.Primary _)] -> simplifyPrimary primary
    
simplifyPrimary (P.Primary lst) =
  case lst of
    [lit@(P.Literal _)] -> A.LiteralExpression (simplifyLiteral lit)

simplifyExpression2 (P.Expression2 lst) =
  case lst of
    [exp3@(P.Expression3C _), rest@(P.Expression2Rest _)] ->
      A.InfixExpression (simplifyExpression3C exp3) 
