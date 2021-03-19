grammar Xpath;

VARNAME : [a-zA-Z0-9_-]+;

FILENAME : [a-zA-Z0-9._-]+;

STRING: ["][ a-zA-Z0-9]*["];

TEXT : 'text()';

WS  : [ \t\r\n]+ -> skip;

filename : FILENAME;

doc : 'doc("' filename '")';

ap
    : doc '/' rp            #ap_children
    | doc '//' rp           #ap_all
    ;

rp
    : VARNAME               #rp_tagName
    | '*'                   #rp_descendant
    | '.'                   #rp_sibling
    | '..'                  #rp_parent
    | TEXT                  #rp_txt
    | '@' VARNAME           #rp_attName
    | '(' rp ')'            #rp_self
    | rp '/' rp             #rp_children
    | rp '//' rp            #rp_all
    | rp '[' filter ']'     #rp_filter
    | rp ',' rp             #rp_merge
    ;

filter
    :   rp                  #filter_rp
    |   rp '=' rp           #filter_equal
    |   rp 'eq' rp          #filter_equal
    |   rp '==' rp          #filter_is
    |   rp 'is' rp          #filter_is
    |   '(' filter ')'      #filter_parent
    |   rp '=' STRING       #filter_txt
    |   filter 'and' filter #filter_and
    |   filter 'or' filter  #filter_or
    |   'not' filter        #filter_not
    ;
