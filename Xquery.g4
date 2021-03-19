grammar Xquery;
import Xpath;

forClause :'for' var 'in' xq (',' var 'in' xq)*;

letClause :'let' var ':=' xq (',' var ':=' xq)*;

whereClause : 'where' cond;

returnClause : 'return' xq;

var : '$' VARNAME;

list : '[' VARNAME (',' VARNAME)* ']';

Stringconstant : '"'+[a-zA-Z0-9,.?;! -]+'"';

xq
    :   var                                                         #xq_variable
    |   Stringconstant                                              #xq_Stringconstant
    |   ap                                                          #xq_ap
    |   '(' xq ')'                                                  #xq_self
    |   xq '/' rp                                                   #xq_rpchildren
    |   xq '//' rp                                                  #xq_rpall
    |   xq ',' xq                                                   #xq_merge
    |   '<' VARNAME '>' '{' xq '}' '<' '/' VARNAME '>'              #xq_tag
    |   forClause letClause? whereClause? returnClause              #xq_flwer
    |   letClause xq                                                #xq_let
    |   'join' '(' xq ',' xq ',' list ',' list ')'                  #xq_join
    ;

cond
    :   xq '=' xq                                                   #cond_equal
    |   xq 'eq' xq                                                  #cond_equal
    |   xq '==' xq                                                  #cond_is
    |   xq 'is' xq                                                  #cond_is
    |   'empty' '(' xq ')'                                          #cond_empty
    |   'some' var 'in' xq (',' var 'in' xq)* 'satisfies' cond      #cond_satisfy
    |   '(' cond ')'                                                #cond_self
    |   cond 'and' cond                                             #cond_and
    |   cond 'or' cond                                              #cond_or
    |   'not' cond                                                  #cond_not
    ;

