module JavaParseTree where

data LiteralType = IntLiteral Integer
                 | CharLiteral Char
                 | FPLiteral Double
                 | StringLiteral String
                 | BoolLiteral Bool
                 | NullLiteral
                 deriving (Show, Read, Eq)

data ParseTreeNode = Identifier String
             | QualifiedIdentifier [ParseTreeNode]
             | Literal LiteralType
             | Expression [ParseTreeNode] 
             | AssignmentOperator
             | Type [ParseTreeNode] 
             | StatementExpression [ParseTreeNode] 
             | ConstantExpression [ParseTreeNode] 
             | Expression1 [ParseTreeNode] 
             | Expression1Rest [ParseTreeNode] 
             | Expression2  [ParseTreeNode] 
             | Expression2Rest [ParseTreeNode] 
             | Infixop 
             | Expression3A [ParseTreeNode] 
             | Expression3B [ParseTreeNode] 
             | Expression3C [ParseTreeNode] 
             | Primary [ParseTreeNode] 
             | IdentifierSuffix [ParseTreeNode]
             | PrefixOp String
             | PostfixOp
             | Selector [ParseTreeNode] 
             | SuperSuffix [ParseTreeNode] 
             | BasicType 
             | ArgumentsOpt [ParseTreeNode] 
             | Arguments [ParseTreeNode] 
             | BracketsOpt [ParseTreeNode]
             | Creator [ParseTreeNode] 
             | InnerCreator [ParseTreeNode] 
             | ArrayCreatorRest [ParseTreeNode] 
             | ClassCreatorRest [ParseTreeNode] 
             | ArrayInitializer [ParseTreeNode] 
             | VariableInitializer [ParseTreeNode] 
             | ParExpression [ParseTreeNode] 
             | Block [ParseTreeNode] 
             | BlockStatements [ParseTreeNode] 
             | BlockStatement  [ParseTreeNode] 
             | LocalVariableDeclarationStatement [ParseTreeNode]
             | Statement [ParseTreeNode]
             | Catches [ParseTreeNode]
             | CatchClause [ParseTreeNode] 
             | SwitchBlockStatementGroups [ParseTreeNode] 
             | SwitchBlockStatementGroup [ParseTreeNode] 
             | SwitchLabel [ParseTreeNode] 
             | ForInit [ParseTreeNode] 
             | ForUpdate [ParseTreeNode] 
             | ModifiersOpt [ParseTreeNode] 
             | Modifier
             | VariableDeclarators [ParseTreeNode] 
             | VariableDeclaratorsRest [ParseTreeNode] 
             | ConstantDeclaratorsRest [ParseTreeNode] 
             | VariableDeclarator [ParseTreeNode] 
             | ConstantDeclarator [ParseTreeNode] 
             | VariableDeclaratorRest [ParseTreeNode] 
             | ConstantDeclaratorRest [ParseTreeNode] 
             | VariableDeclaratorId [ParseTreeNode] 
             | CompilationUnit [ParseTreeNode] 
             | ImportDeclaration [ParseTreeNode] 
             | TypeDeclaration [ParseTreeNode] 
             | ClassOrInterfaceDeclaration [ParseTreeNode] 
             | ClassDeclaration [ParseTreeNode] 
             | InterfaceDeclaration [ParseTreeNode] 
             | TypeList [ParseTreeNode] 
             | ClassBody [ParseTreeNode] 
             | InterfaceBody [ParseTreeNode] 
             | ClassBodyDeclaration [ParseTreeNode]
             | MemberDecl [ParseTreeNode]
             | MethodOrFieldDecl [ParseTreeNode]
             | MethodOrFieldRest [ParseTreeNode]
             | InterfaceBodyDeclaration [ParseTreeNode]
             | InterfaceMemberDecl [ParseTreeNode]
             | InterfaceMethodOrFieldDecl [ParseTreeNode]
             | InterfaceMethodOrFieldRest [ParseTreeNode]
             | MethodDeclaratorRest [ParseTreeNode]
             | VoidMethodDeclaratorRest [ParseTreeNode]
             | InterfaceMethodDeclaratorRest [ParseTreeNode]
             | VoidInterfaceMethodDeclaratorRest [ParseTreeNode]
             | ConstructorDeclaratorRest [ParseTreeNode]
             | QualifiedIdentifierList [ParseTreeNode] 
             | FormalParameters [ParseTreeNode] 
             | FormalParameter [ParseTreeNode] 
             | MethodBody [ParseTreeNode]
             | None
             | ThisSelector
             | VoidDotClass
             | IdentifierSuffixClass
             | IdentifierSuffixThis
             | FinallyBlock [ParseTreeNode]
             | EmptyStatement
             | DefaultSwitchLabel
             | PackageDeclaration [ParseTreeNode]
             | ImportQualifier [ParseTreeNode]
             | ImportDotStar
             | EmptyClassBodyDeclaration
             | EmptyInterfaceBodyDeclaration
             | Expression2RestComponent [ParseTreeNode]
             | EmptyBlock
             | BracketsOptTok
             deriving (Show, Read, Eq)
