module Java where

import Text.ParserCombinators.Parsec
import Text.ParserCombinators.Parsec.Expr
import qualified Text.ParserCombinators.Parsec.Token as P
import Text.ParserCombinators.Parsec.Language 
import Debug.Trace
import JavaParseTree
import Data.Char
import Text.Regex

-- UTILITIES

runParse parser input
        = case (parse parser "" input) of
            Left err -> do { putStr "parse error at "
                           ; print err
                           }
            Right x -> print x

runFile fname = do { input <- readFile fname;
                     runString input
                   }

runString input = run compilationUnit input

run parser input = runParse (do { whiteSpace;
                           x <- parser;
                           eof;
                           return x
                         }) input
                   

getParse parser input
        = case (parse parser "" input) of
            Left err -> None
            Right x -> x
            
getRun parser input = getParse (do { whiteSpace;
                           x <- parser;
                           eof;
                           return x
                         }) input
                      
-- LEXER -- LANGUAGE DEFINITION

joosReservedNames = ["abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float", "for", "new", "switch", "if", "package", "synchronized", "goto", "private", "implements", "protected", "import", "public", "instanceof", "return", "int", "short", "interface", "static", "long", "strictfp", "native", "super", "this", "throw", "throws", "transient", "try", "void", "volatile", "while", "true", "false", "null" ]

-- joosReservedOpNames = ["=", ">", "<", "!", "~", "?", ":", "==", "<=", ">=", "!=", "&&", "||", "++", "--", "+", "-", "*", "/", "&", "|", "^", "%", "<<", ">>", ">>>", "+=", "-=", "*=", "/=", "&=", "|=", "^=", "%=", "<<=", ">>=", ">>>=" ]

languageDefinition = javaStyle
                   {
                     P.nestedComments = False,
                     P.reservedNames = joosReservedNames,
                     -- P.reservedOpNames = joosReservedOpNames, -- 
                     P.caseSensitive = True,
                     P.identStart = (letter <|> char '_'),
                     P.identLetter = (alphaNum <|> char '_')
                   }

lexer = P.makeTokenParser languageDefinition

-- LEXER -- TOKEN DEFINITIONS
parens                = P.parens lexer    
braces                = P.braces lexer    
-- brackets              = P.brackets lexer

-- semiSep               = P.semiSep lexer  
-- semiSep1              = P.semiSep1 lexer    
-- commaSep              = P.commaSep lexer
-- commaSep1             = P.commaSep1 lexer

dot                   = P.dot lexer
comma                 = P.comma lexer
semi                  = P.semi lexer

whiteSpace            = P.whiteSpace lexer    
symbol                = P.symbol lexer    
reserved              = P.reserved lexer    
reservedOp            = P.reservedOp lexer

-- Literal lexeres
charLiteralLexer      = P.charLiteral lexer    
stringLiteralLexer    = P.stringLiteral lexer    
identifierLexer       = P.identifier lexer    
nullLiteralLexer      = do{ reserved "null"; return (NullLiteral)}
boolLiteralLexer      = do{ b <- reserved "true"; return (True)} <|>
                        do{ b <- reserved "false"; return (False)}

integerLiteral = do {
  x <- try(octalIntegerLiteral) <|>
  try(hexIntegerLiteral) <|>
  decimalIntegerLiteral;
  whiteSpace;
  return x
}

decimalIntegerLiteral = do {
  x <- decimalNumeral;
  optional(integerTypeSuffix);
  return x
}

hexIntegerLiteral = do {
  x <- hexNumeral;
  optional(integerTypeSuffix);
  return x
}

octalIntegerLiteral = do {
  x <- octalNumeral;
  optional(integerTypeSuffix);
  return x
}

integerTypeSuffix = do {
  char 'l' <|> char 'L'
}

decimalNumeral = do {
  char '0';
  return 0
} <|> do {
  ds <- many1 digit;
  return (read ds :: Integer)
}

hexNumeral = do {
  z <- char '0';
  x <- char 'x' <|> char 'X';
  ds <- many1 hexDigit;
  return (read (z : x : ds) :: Integer)
}

octalNumeral = do {
  z <- char '0';
  ds <- many1 octDigit;
  return (read (z : 'o' : ds) :: Integer)
}

floatingPointLiteral = do {
  x <- floatingPointLiteralLexer;
  whiteSpace;
  return x
}

floatingPointLiteralLexer = try(do {
  before <- many1 digit;
  char '.';
  after <- many digit;
  exp <- option "0" exponentPart;
  optional floatTypeSuffix;
  return (processFp before after exp)
}) <|> try(do {
  char '.';
  after <- many1 digit;
  exp <- option "0" exponentPart;
  optional floatTypeSuffix;
  return (processFp "0" after exp)
}) <|> try(do {
  before <- many1 digit;
  exp <- exponentPart;
  optional floatTypeSuffix;
  return (processFp before "0" exp)
}) <|> do {
  before <- many1 digit;
  exp <- option "0" exponentPart;
  floatTypeSuffix;
  return (processFp before "0" exp)
}

exponentPart = do {
  exponentIndicator;
  x <- signedInteger;
  return x
}

exponentIndicator = char 'e' <|> char 'E'

signedInteger = do {
  c <- option '+' (char '+' <|> char '-');
  ds <- many1 digit;
  return (stripPlus c ds)
} where stripPlus c ds = if c == '-'
                         then c : ds
                         else ds

floatTypeSuffix = char 'f' <|> char 'F' <|> char 'd' <|> char 'D'

processFp before after exp = read floating :: Double
  where floating = (before ++ dotAfter ++ "e" ++ exp)
        dotAfter = if null after
                   then []
                   else '.' : after

-- PARSER

opt = option None

filt :: [ParseTreeNode] -> [ParseTreeNode]
filt = filter (/= None)

identifier = do {
  x <- identifierLexer;
  return(Identifier x)
}

qualifiedIdentifier = do {
  x <- sepBy1 identifier dot;
  return(QualifiedIdentifier (filt x))
}

literal = do {
  x <- try (do { v <- floatingPointLiteral; return (FPLiteral v)}) <|>
       try (do { v <- integerLiteral; return (IntLiteral v)}) <|>
       try (do { v <- charLiteralLexer; return (CharLiteral v)}) <|>
       try (do { v <- stringLiteralLexer; return (StringLiteral v)}) <|>
       try (do { v <- boolLiteralLexer; return (BoolLiteral v)}) <|>
       (do { v <- nullLiteralLexer; return (NullLiteral)});
  return(Literal x)
}

-- The grammar for Expression has improperly balanced brackets. I theorize that the 
-- intention at this point was actually to use braces, signifying *zero or more* 
-- assignment expressions, since you can do things like a = b = c. I have in turn 
-- simplified this to expression1s separated by assignment operators.
expression = do {
  x <- sepBy1 expression1 assignmentOperator;
  return(Expression (filt x))
}
 
assignmentOperator = do {
  try (reservedOp "=") <|>
  try (reservedOp "+=") <|>
  try (reservedOp "-=") <|>
  try (reservedOp "*=") <|>
  try (reservedOp "/=") <|>
  try (reservedOp "&=") <|>
  try (reservedOp "|=") <|>
  try (reservedOp "^=") <|>
  try (reservedOp "%=") <|>
  try (reservedOp "<<=") <|>
  try (reservedOp ">>=") <|>
  reservedOp ">>>=";
  return(AssignmentOperator)
}

-- renamed to avoid conflict with type keyword 
joosType = try(do {
  x <- sepBy1 identifier dot;
  y <- bracketsOpt;
  return(Type (filt (x ++ [y])))
}) <|> do {
  x <- basicType;
  y <- bracketsOpt;
  return (Type (filt[x,y]))
}
 
statementExpression = do {
  x <- expression;
  -- optional semi; -- NOT IN GRAMMAR 
  -- Having this here messed up for loops. It consumed the semi the 
  -- for loop was expecting
  return(StatementExpression (filt[x]))
}
 
constantExpression = do {
  x <- expression;
  return(ConstantExpression (filt[x]))
}
 
expression1 = do {
  x <- expression2;
  y <- opt(expression1Rest);
  return(Expression1 (filt[x,y]))
}
 
expression1Rest = do {
  symbol "?";
  x <- expression;
  symbol ":";
  y <- expression1;
  return(Expression1Rest (filt[x,y]))
}
 
expression2 = do {
  x <- expression3;
  y <- opt(expression2Rest);
  return(Expression2 (filt[x,y]))
}

-- CHANGED FROM GRAMMAR
-- There appear to be a few errors in the grammar here. First of all, why are
-- there two Expression3s in a row for the instanceof case? This seems obviously
-- wrong. Second of all, instanceof being treated specially caused lots of strange
-- errors in parsing expressions where it was used like 'a || b instanceof C', because
-- you really want to be able to get back and forth between instanceof and infixOp.
-- In the interests of simplicity, I have elected to take the easy way out of this 
-- and just parse instanceOf as an infix operator like everything else. The constraint
-- that that argument to instanceOf must be a type and not, for example, a literal or
-- something, is left to a later stage of compilation.
expression2Rest = do {
  x <- many( do {
    c <- (try (do {
      a <- infixop;
      b <- expression3;
      return (Expression2RestComponent (filt [a,b]))
    }) <|> do {
      reserved "instanceof";
      a <- joosType;
      return (Expression2RestComponent (filt [a]))
    });
    return (Expression2RestComponent (filt [c]))
  });
  return(Expression2Rest (filt x))
}

infixop = do {
  try(reservedOp "||") <|>
  try(reservedOp "&&") <|>
  try(reservedOp "|") <|>
  try(reservedOp "^") <|>
  try(reservedOp "&") <|>
  try(reservedOp "==") <|>
  try(reservedOp "!=") <|>
  try(reservedOp "<") <|>
  try(reservedOp ">") <|>
  try(reservedOp "<=") <|>
  try(reservedOp ">=") <|>
  try(reservedOp "<<") <|>
  try(reservedOp ">>") <|>
  try(reservedOp ">>>") <|>
  try(reservedOp "+") <|>
  try(reservedOp "-") <|>
  try(reservedOp "*") <|>
  try(reservedOp "/") <|>
  reservedOp "%";
  return(Infixop)
}

expression3 = try(do {
  x <- prefixOp;
  y <- expression3;
  return(Expression3A (filt[x,y]))
}) <|> try (do {
  x <- parens(joosType);
  y <- expression3;
  return(Expression3B (filt[x,y]))
}) <|> do {
  x <- primary;
  y <- many selector;
  z <- many postfixOp;
  return(Expression3C (filt ([x] ++ y ++ z)))
}
-- The grammar refers to "Expr" here, which is not a real production. I've just 
-- guessed at their true intentions.
 
primary = try(do {
  x <- parens expression;
  return(Primary (filt[x]))
}) <|> try(do {
  reserved "this";
  x <- opt arguments;
  return(Primary (filt[x]))
}) <|> try(do {
  reserved "super";
  x <- superSuffix;
  return(Primary (filt[x]))
}) <|> try(do {
  x <- literal;
  return(Primary (filt[x]))
}) <|> try(do {
  reserved "new";
  x <- creator;
  return(Primary (filt[x]))
}) <|> try(do {
-- This is kind of a huge hack. Basically, the original implementation was just
-- sepBy1 identifier dot, but this was preventing the non-identifier keywords like
-- .class from being parsed correctly. So now you can interchange those with 
-- identifiers at will, and presumably it'll all get sorted out later on.
-- TODO(Thor): Make this less of a giant hack?
  x <- sepBy1 (do {try(identifierSuffixKeyword) <|> identifier}) dot;
  y <- opt identifierSuffix;
  return(Primary (filt(x ++ [y])))
}) <|> try(do {
  x <- basicType;
  y <- bracketsOpt;
  dot;
  reserved "class";
  return(Primary (filt[x,y]))
}) <|> do {
  reserved "void";
 dot;
  reserved "class";
  return(VoidDotClass)
}
 
identifierSuffix = try(do {
  x <- identifierSuffixArray;
  return (IdentifierSuffix (filt[x]))
}) <|> try(do {
  dot;
  x <- identifierSuffixKeyword;
  return (IdentifierSuffix (filt[x]))
}) <|> do {
  x <- arguments;
  return(IdentifierSuffix (filt[x]))
}

-- These two helper productions are not in the grammar, they're all from 
-- identifierSuffix.
identifierSuffixArray = do {
  symbol "[";
  x <- (do { symbol "]";
             a <- bracketsOpt;
             dot;
             reserved "class";
             return (a)
           }) <|>
       (do { a <- expression;
             symbol "]";
             return (a)});
  return(IdentifierSuffix (filt[x]))
}

identifierSuffixKeyword = do {
  x <- do { reserved "class"; return IdentifierSuffixClass;} <|>
       do { reserved "this"; return IdentifierSuffixThis;} <|>
       do { reserved "super"; x <- arguments; return x} <|>
       do { reserved "new"; x <- innerCreator; return x};
  return(IdentifierSuffix (filt[x]))
}

prefixOp = try(do {
                  reservedOp "++";
                  return (PrefixOp "++")
    }) <|> try(do {
                  reservedOp "--";
                  return (PrefixOp "--")
    }) <|> try(do {
                  reservedOp "!";
                  return (PrefixOp "!")
    }) <|> try(do {
                  reservedOp "~";
                  return (PrefixOp "~")
    }) <|> try(do {
                  reservedOp "+";
                  return (PrefixOp "+")
    }) <|> try(do {
                  reservedOp "-";
                  return (PrefixOp "-")
    })
                  
postfixOp = do {
  try (reservedOp "++") <|>
  reservedOp "--";
  return(PostfixOp)
}

selector = try(do {
  dot;
  x <- try (do { reserved "this"; return ThisSelector}) <|>
       try (do { reserved "super"; a <- superSuffix; return (Selector (filt[a]))}) <|>
       try (do { reserved "new"; a <- innerCreator; return (Selector (filt[a]))}) <|>
       (do { a <- identifier; b <- opt(arguments); return (Selector (filt [a,b]))});
  return(Selector (filt[x]))
}) <|> do {
  symbol "[";
  x <- expression;
  symbol "]";
  return(Selector (filt[x]))
}
  
superSuffix = try(do {
  x <- arguments;
  return(SuperSuffix (filt[x]))
}) <|> do {
  dot;
  x <- identifier;
  y <- opt arguments;
  return(SuperSuffix (filt[x,y]))
}
 
basicType = do {
  reserved "byte" <|>
  reserved "short" <|>
  reserved "char" <|>
  reserved "int" <|>
  reserved "long" <|>
  reserved "float" <|>
  reserved "double" <|>
  reserved "boolean";
  return(BasicType)
}
 
argumentsOpt = do {
  x <- opt arguments;
  return(ArgumentsOpt (filt[x]))
}
 
arguments = do {
  x <- parens(sepBy expression comma);
  return(Arguments (filt x))
}
 
bracketsOpt = do {
  x <- many(do { symbol "[";
                            symbol "]";
                            return BracketsOptTok
                          });
  return (BracketsOpt (filt x))
}
 
creator = try(do {
  x <- qualifiedIdentifier;
  y <- try arrayCreatorRest <|> classCreatorRest;
  return(Creator (filt[x,y]))
}) <|> do { -- NOT IN GRAMMAR. Needed for basic-type arrays!
  x <- basicType;
  y <- arrayCreatorRest;
  return(Creator (filt[x,y]))
}
 
innerCreator = do {
  x <- identifier;
  y <- classCreatorRest;
  return(InnerCreator (filt[x]))
}
 
arrayCreatorRest = do {
  symbol "[";
  x <- try(do { 
    a <- expression;
    symbol "]";
    b <- many (do {
      symbol "[";
      try(do {
        x <- expression;
        symbol "]";
        return x
      }) <|> do {
        symbol "]";
        return None
      }
    });
    c <- bracketsOpt;
    return ([a] ++ b ++ [c])
  }) <|> do {     
    symbol "]";
    a <- bracketsOpt;
    b <- arrayInitializer;
    return [a,b]
  };
  return(ArrayCreatorRest (filt x))
}
 
classCreatorRest = do {
  x <- arguments;
  y <- opt classBody;
  return(ClassCreatorRest (filt[x,y]))
}
 
arrayInitializer = do {
  x <- braces (sepEndBy variableInitializer comma);
  return(ArrayInitializer (filt x))
}
 
variableInitializer = do {
  x <- try arrayInitializer <|> expression;
  return(VariableInitializer (filt[x]))
}
 
parExpression = do {
  x <- parens expression;
  return(ParExpression (filt[x]))
}
 
block = do {
  x <- braces blockStatements;
  return(Block (filt[x]))
}
 
blockStatements = do {
  x <- many blockStatement;
  return(BlockStatements (filt x))
}

-- No labeled blocks atm 
blockStatement = try(do {
  x <- localVariableDeclarationStatement;
  return (BlockStatement (filt [x]))
}) <|> try(do {
  x <- classOrInterfaceDeclaration;
  return (BlockStatement (filt [x]))
}) <|> do {
  {-x <- opt(do { a <- identifier; symbol ":"; return a});-}
  y <- statement;
  return (BlockStatement (filt [y]))
}
  
localVariableDeclarationStatement = do {
  optional (reserved "final");
  x <- joosType;
  y <- variableDeclarators;
  semi;
  return(LocalVariableDeclarationStatement (filt[x,y]))
}

statement = try(do {
  x <- block;
  return(Statement (filt[x]))
}) <|> try(do {
  reserved "if";
  x <- parExpression;
  y <- statement;
  z <- opt(do { reserved "else";
                a <- statement;
                return a
              });
  return(Statement (filt[x,y,z]))
}) <|> try(do {
  reserved "for";
  symbol "(";
  x <- opt forInit;
  semi;
  y <- opt expression;
  semi;
  z <- opt forUpdate;
  symbol ")";
  za <- statement;
  return(Statement (filt[x,y,z,za]))
}) <|> try(do {
  reserved "while";
  x <- parExpression;
  y <- statement;
  return(Statement (filt[x,y]))
}) <|> try(do {
  reserved "do";
  x <- statement;
  reserved "while";
  y <- parExpression;
  semi;
  return(Statement (filt[x,y]))
}) <|> try(do {
  reserved "try";
  x <- block;
  y <- try ( 
       do { a <- opt catches;
            reserved "finally";
            b <- block;
            return (FinallyBlock (filt[a,b]))
          }) <|> catches;
  return(Statement (filt[x,y]))
}) <|> try (do {
  reserved "switch";
  x <- parExpression;
  y <- braces switchBlockStatementGroups;
  return(Statement (filt[x,y]))
}) <|> try (do {
  reserved "synchronized";
  x <- parExpression;
  y <- block;
  return(Statement (filt[x,y]))
}) <|>  try (do {
  reserved "return";
  x <- opt expression;
  semi;
  return(Statement (filt[x]))
}) <|> try (do {
  reserved "throw";
  x <- expression;
  semi;
  return(Statement (filt[x]))
}) <|> try (do {
  reserved "break";
  x <- opt identifier;
  semi; -- NOT IN GRAMMAR. I think a semicolon is mandatory here.
  return(Statement (filt[x]))
}) <|> try (do {
  reserved "continue";
  x <- opt identifier;
  semi; -- NOT IN GRAMMAR. I think a semicolon is mandatory here.
  return(Statement (filt[x]))
}) <|> try (do {
  semi;
  return EmptyStatement
}) <|> try (do {
  x <- identifier;
  symbol ":";
  y <- statement;
  return(Statement (filt[x,y]))
}) <|> do {
  -- THEORY: expressionStatement is statementExpression with 
  -- a semicolon after it?
  x <- statementExpression;
  semi;
  return (Statement (filt[x]))
}
-- No ExpressionStatement

catches = do {
  x <- many1 catchClause;
  return(Catches (filt x))
}

catchClause = do {
  reserved "catch";
  symbol "(";
  x <- formalParameter;
  symbol ")";
  y <- block;
  return(CatchClause (filt[x,y]))
}
 
switchBlockStatementGroups = do {
  x <- many switchBlockStatementGroup;
  return(SwitchBlockStatementGroups (filt x))
}
 
switchBlockStatementGroup = do {
  x <- switchLabel;
  y <- blockStatements;
  return(SwitchBlockStatementGroup (filt[x,y]))
}
 
switchLabel = try(do {
  reserved "case";
  x <- constantExpression;
  symbol ":";
  return(SwitchLabel (filt[x]))
}) <|> do {
  reserved "default";
  symbol ":";
  return DefaultSwitchLabel
}

-- Dropped "moreStatementExpressions in favor of using sepBy1"
 
forInit = try(do {
  optional (reserved "final");
  x <- joosType;
  y <- variableDeclarators;
  return(ForInit (filt[x,y]))
}) <|> do {
  x <- sepBy1 statementExpression comma;
  return(ForInit (filt x))
}
 
forUpdate = do {
  x <- sepBy1 statementExpression comma;
  return(ForUpdate (filt x))
}
 
modifiersOpt = do {
  x <- many modifier;
  return(ModifiersOpt (filt x))
}
 
modifier = do {
  reserved "public" <|>
  reserved "protected" <|>
  reserved "private" <|>
  reserved "static" <|>
  reserved "abstract" <|>
  reserved "final" <|>
  reserved "native" <|>
  reserved "synchronized" <|>
  reserved "transient" <|>
  reserved "volatile" <|>
  reserved "strictfp";
  return(Modifier)
}
 
variableDeclarators = do {
  x <- sepBy1 variableDeclarator comma;
  return(VariableDeclarators (filt x))
}
 
variableDeclaratorsRest = do {
  x <- variableDeclaratorRest;
  y <- many (do { comma; a <- variableDeclarator; return a});
  return(VariableDeclaratorsRest (filt ([x] ++ y)))
}
 
constantDeclaratorsRest = do {
  x <- constantDeclaratorRest;
  y <- many (do { comma; a <- constantDeclarator; return a});
  return(ConstantDeclaratorsRest (filt ([x] ++ y)))
}

variableDeclarator = do {
  x <- identifier;
  y <- variableDeclaratorRest;
  return(VariableDeclarator (filt[x,y]))
}
 
constantDeclarator = do {
  x <- identifier;
  y <- constantDeclaratorRest;
  return(ConstantDeclarator (filt[x,y]))
}
 
variableDeclaratorRest = do {
  x <- bracketsOpt;
  y <- opt (do { symbol "=";
                 x <- variableInitializer;
                 return x
               });
  return(VariableDeclaratorRest (filt[x,y]))
}
 
constantDeclaratorRest = do {
  x <- bracketsOpt;
  symbol "=";
  x <- variableInitializer;
  return(ConstantDeclaratorRest (filt[x]))
}
 
variableDeclaratorId = do {
  x <- identifier;
  y <- bracketsOpt;
  return(VariableDeclaratorId (filt[x,y]))
}
 
compilationUnit = do {
  x <- opt (do { reserved "package";
                 a <- qualifiedIdentifier;
                 semi;
                 return (PackageDeclaration (filt [a]))
               });
  y <- many importDeclaration;
  z <- many typeDeclaration;
  return(CompilationUnit (filt [x] ++ y ++ z))
}

importDeclaration = do {
  reserved "import";
  x <- identifier;
  y <- many(try(importDotStar) <|> importQualifier);
  semi;
  return(ImportDeclaration (filt [x] ++ y))
}

-- New productions to simplify imports
importQualifier = do {
  dot;
  x <- identifier;
  return(ImportQualifier (filt [x]))
}

importDotStar = do {
  dot;
  symbol "*";
  return ImportDotStar
}
 
typeDeclaration = do {
  x <- classOrInterfaceDeclaration <|> do {semi; return None;};
  return(TypeDeclaration (filt[x]))
}
 
classOrInterfaceDeclaration = do {
  x <- modifiersOpt;
  y <- try(classDeclaration) <|> interfaceDeclaration;
  return(ClassOrInterfaceDeclaration (filt[x,y]))
}
 
classDeclaration = do {
  reserved "class";
  x <- identifier;
  y <- opt (do { reserved "extends";
                 a <- joosType;
                 return a
               });
  z <- opt (do { reserved "implements";
                 a <- typeList;
                 return a
               });          -- Added below for empty class bodies
  za <- try (classBody) <|> do {symbol "{"; symbol "}"; return None};
  return(ClassDeclaration (filt[x,y,z,za]))
}
 
interfaceDeclaration = do {
  reserved "interface";
  x <- identifier;
  y <- opt (do { reserved "extends";
                 a <- typeList;
                 return a
               });
  z <- interfaceBody;
  return(InterfaceDeclaration (filt[x,y,z]))
}
 
typeList = do {
  x <- sepBy1 joosType comma;
  return(TypeList (filt x))
}
 
classBody = do {
  x <- braces (many classBodyDeclaration);
  return(ClassBody (filt x))
}
 
interfaceBody = do {
  x <- braces (many interfaceBodyDeclaration);
  return(InterfaceBody (filt x))
}
 
classBodyDeclaration = try(do {
  semi;
  return EmptyClassBodyDeclaration
}) <|> try(do {
  x <- modifiersOpt;
  y <- memberDecl;
  return (ClassBodyDeclaration (filt[x,y]))
}) <|> do { -- static conflicts with modifiersOpt above?
  optional(reserved "static");
  x <- block;
  return (ClassBodyDeclaration (filt[x]))
}

memberDecl = try(do {
  x <- methodOrFieldDecl;
  return(MemberDecl (filt[x]))
}) <|> try(do {
  reserved "void";
  x <- identifier;
  y <- methodDeclaratorRest;
  return(MemberDecl (filt[x,y]))
}) <|> try(do {
  x <- identifier;
  y <- constructorDeclaratorRest;
  return(MemberDecl (filt[x,y]))
}) <|> do {
  x <- classOrInterfaceDeclaration;
  return(MemberDecl (filt[x]))
}

methodOrFieldDecl = do {
  x <- joosType;
  y <- identifier;
  z <- methodOrFieldRest;
  return(MethodOrFieldDecl (filt[x,y,z]))
}

methodOrFieldRest = try(do {
  x <- methodDeclaratorRest;
  return(MethodOrFieldRest (filt[x]))
}) <|> do { -- NOT IN SPEC: DeclaratorS, not Declarator
  x <- variableDeclaratorsRest;
  semi; 
  return(MethodOrFieldRest (filt[x]))
}

interfaceBodyDeclaration = try(do {
  semi;
  return EmptyInterfaceBodyDeclaration
}) <|> do {
  x <- modifiersOpt;
  y <- interfaceMemberDecl;
  return(InterfaceBodyDeclaration (filt[x,y]))
}

interfaceMemberDecl = try(do {
  x <- interfaceMethodOrFieldDecl;
  return(InterfaceMemberDecl (filt[x]))
}) <|> try(do {
  reserved "void";
  x <- identifier;
  y <- voidInterfaceMethodDeclaratorRest;
  return(InterfaceMemberDecl (filt[x,y]))
}) <|> do {
  x <- classOrInterfaceDeclaration;
  return(InterfaceMemberDecl (filt[x]))
}

interfaceMethodOrFieldDecl = do {
  x <- joosType;
  y <- identifier;
  z <- interfaceMethodOrFieldRest;
  return(InterfaceMethodOrFieldDecl (filt[x,y,z]))
}

interfaceMethodOrFieldRest = try(do {
  x <- constantDeclaratorsRest;
  semi;
  return(InterfaceMethodOrFieldRest (filt[x]))
}) <|> do {
  x <- interfaceMethodDeclaratorRest;
  return(InterfaceMethodOrFieldRest (filt[x]))
}

methodDeclaratorRest = do {
  x <- formalParameters;
  y <- bracketsOpt;
  z <- opt( do { reserved "throws";
                 a <- qualifiedIdentifierList;
                 return a
               });
  za <- try(methodBody) <|> do { semi; return None};
  return(MethodDeclaratorRest (filt[x,y,z,za]))
}

voidMethodDeclaratorRest = do {
  x <- formalParameters;
  y <- opt( do { reserved "throws";
                 a <- qualifiedIdentifierList;
                 return a
               });
  z <- try(methodBody) <|> do { semi; return None};
  return(VoidMethodDeclaratorRest (filt[x,y,z]))
}

interfaceMethodDeclaratorRest = do {
  x <- formalParameters;
  y <- bracketsOpt;
  z <- opt( do { reserved "throws";
                 a <- qualifiedIdentifierList;
                 return a
               });
  semi;
  return(InterfaceMethodDeclaratorRest (filt[x,y,z]))
}

voidInterfaceMethodDeclaratorRest = do {
  x <- formalParameters;
  y <- opt( do { reserved "throws";
                 a <- qualifiedIdentifierList;
                 return a
               });
  semi;
  return(VoidInterfaceMethodDeclaratorRest (filt[x,y]))
}

constructorDeclaratorRest = do {
  x <- formalParameters;
  y <- opt( do { reserved "throws";
                 a <- qualifiedIdentifierList;
                 return a
               });
  z <- methodBody;
  return(ConstructorDeclaratorRest (filt[x,y,z]))
}

qualifiedIdentifierList = do {
  x <- sepBy1 qualifiedIdentifier comma;
  return(QualifiedIdentifierList (filt x))
}
 
formalParameters = do {
  x <- parens(sepBy formalParameter comma);
  return(FormalParameters (filt x))
}
 
formalParameter = do {
  optional (reserved "final");
  x <- joosType;
  y <- variableDeclaratorId;
  return(FormalParameter (filt[x,y]))
}
 
methodBody = do {
  x <- block;
  return(MethodBody (filt[x]))
}
