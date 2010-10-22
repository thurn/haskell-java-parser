module JavaAst where

{-
Examples

"class Empty {}" becomes:

CompilationUnit {
  package = Nothing,
  imports = [],
  classDeclarations = [
    ClassDeclaration {
      className = "Empty",
      classModifiers = [],
      superClass = Nothing,
      implementedInterfaces = [],
      classBody = ClassBody {
        staticBlocks = [],
        blocks = [],
        memberDeclarations = []
      }
    }
  ],
  interfaceDeclarations = []
}

-}

{- 
 - Top of the abstract syntax tree, the root node type. Contains all of the other
 - relevent AST data.
-}
data CompilationUnit = CompilationUnit {
  package :: Maybe Package,
  imports :: [Import],
  classDeclarations :: [ClassDeclaration],
  interfaceDeclarations :: [InterfaceDeclaration]
  } deriving (Eq, Show, Read)

{- 
 - The name of the package for the current compilation unit.
-}
type Package = String

{- 
 - An import statement, requiring an import path and an import type
-}
data Import = Import {
  importPath :: String,
  importType :: ImportType
  } deriving (Eq, Show, Read)
             
{- 
 - The possible types of import statements. Currently, you can either import 
 - a single name, or you can import everything under a given name, via "*".
-}
data ImportType = SingleImport | BulkImport
                deriving (Eq, Show, Read)
                              
data ClassDeclaration = ClassDeclaration {
  className :: String,
  classModifiers :: [Modifier],
  superClass :: Maybe Type,
  implementedInterfaces :: [Type],
  classBody :: ClassBody
  } deriving (Eq, Show, Read)

data InterfaceDeclaration = InterfaceDeclaration {
  interfaceName :: String,
  interfaceModifiers :: [Modifier],
  extendedInterfaces :: [Type],
  interfaceBody :: InterfaceBody
  } deriving (Eq, Show, Read)
             
data ClassBody = ClassBody {
  staticBlocks :: [Block],
  blocks :: [Block],
  methodDeclarations :: [MethodDeclaration],
  variableDeclarations :: [VariableDeclaration]
  } deriving (Eq, Show, Read)
             
data Identifier = Identifier String deriving (Show, Read, Eq)

data Literal = IntLiteral Integer
                 | CharLiteral Char
                 | FpLiteral Double
                 | StringLiteral String
                 | BoolLiteral Bool
                 | NullLiteral
                 deriving (Show, Read, Eq)
                          
data VariableDeclaration = VariableDeclaration {
  variableName :: String,
  variableType :: Type,
  variableModifiers :: [Modifier],
  variableInitialValue :: Maybe Expression
  } deriving (Eq, Show, Read)
             
data Type = PrimitiveType TypeName
          | ReferenceType TypeName
          | ArrayType Type Arity [ArrayDimensionSize]
          deriving (Show, Read, Eq)
                   
type TypeName = String
type Arity = Int
type ArrayDimensionSize = Int

data MethodDeclaration = MethodDeclaration {
  methodName :: String,
  methodModifiers :: [Modifier],
  returnType :: Type,
  methodArgs :: [Argument],
  methodExceptions :: [Type],
  methodBody :: Maybe MethodBody
  } deriving (Eq, Show, Read)
  
data InterfaceBody = InterfaceBody {
  interfaceMethods :: [MethodDeclaration],
  interfaceVariables :: [VariableDeclaration]
  } deriving (Eq, Show, Read)
             
type Modifier = String

data Argument = Argument {
  argumentName :: String,
  argumentType :: Type
  } deriving (Eq, Show, Read)
             
data Block = Block [Statement] deriving (Eq, Show, Read)

data Statement = IfStatement Expression Block
               | IfElseStatement Expression Block Block
               | WhileStatement Expression Block
               | ForStatement (Maybe VariableDeclaration)
                 (Maybe Expression) [Expression] Block
               | LabeledStatement Label Statement
               | StatementBlock Block
               | EmptyStatement
               | ExpressionStatement Expression
               | SwitchStatement Expression SwitchBlock
               | DoStatement Block Expression
               | BreakStatement (Maybe Identifier)
               | ContinueStatement (Maybe Identifier)
               | ReturnStatement (Maybe Expression)
               | SynchronizedStatement (Maybe Expression) Block
               | ThrowStatement (Maybe Expression)
               | TryStatement Block [Catch] (Maybe FinallyBlock)
               | VariableDeclarationStatement VariableDeclaration
                 deriving(Eq, Show, Read)
                         
type Label = String
                         
data MethodBody = MethodBody Block deriving (Eq, Show, Read)

data Expression = AssignmentExpression Assignment
                | LiteralExpression Literal
                | ThisExpression
                | SubExpression Expression
                | ClassCreationExpression ClassCreation
                | FieldAccessExpression FieldAccess
                | MethodInvocationExpression MethodInvocation
                | ArrayAccessExpression ArrayAccess
                | ArrayCreationExpression ArrayCreation
                | ConditionalExpression Conditional
                | InfixExpression Expression InfixOperator Expression
                | PrefixExpression PrefixOperator Expression
                | PostfixExpression Expression PostfixOperator
                  deriving (Eq, Show, Read)

type PrefixOperator = String
type InfixOperator = String
type PostfixOperator = String

type Assignment = String
type ClassCreation = String
type FieldAccess = String
type MethodInvocation = String
type ArrayAccess = String
type ArrayCreation = String
type Conditional = String
type SwitchBlock = String
type Catch = String
type FinallyBlock = String
